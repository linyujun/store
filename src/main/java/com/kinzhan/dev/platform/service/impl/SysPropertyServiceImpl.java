package com.kinzhan.dev.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.kinzhan.dev.platform.beans.dto.system.PropertyDto;
import com.kinzhan.dev.platform.beans.dto.system.PropertyMapDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysPropertyEntity;
import com.kinzhan.dev.platform.beans.mapper.SysPropertyMapper;
import com.kinzhan.dev.platform.service.SysPropertyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class SysPropertyServiceImpl extends ServiceImpl<SysPropertyMapper, SysPropertyEntity>
        implements SysPropertyService {

    private final ContextRefresher contextRefresher;

    private final ConfigurableEnvironment environment;

    private final WxPayService wxPayService;

    @Override
    public Boolean isDev() {

        return false;
    }

    /**
     * 获取配置信息
     * @return
     */
    @Override
    public Map getMap() {
        List<SysPropertyEntity> todoList = lambdaQuery()
                .eq(SysPropertyEntity::getIsDelete, 0)
                .eq(SysPropertyEntity::getIsHidden, 0).list();
        if (null == todoList) {
            return null;
        }
        return todoList
                .stream()
                .collect(Collectors.toMap(SysPropertyEntity::getK, Function.identity()));
    }

    @Override
    @Transactional
    public void save(Map dto) {
        if (null != dto) {
            dto.forEach((k, v) -> {
                lambdaUpdate().eq(SysPropertyEntity::getK, k).set(SysPropertyEntity::getV, v).update();
            });
        }
    }

    /**
     * 存值
     * @param key
     * @param value
     */
    @Override
    public void save(String key, String value) {
        lambdaUpdate().eq(SysPropertyEntity::getK, key).set(SysPropertyEntity::getV, value).update();
    }


    /**
     * String isAudit = configService.getValue("isAudit");
     * 获取全局配置信息
     * @param configKey
     * @return
     */
    @Override
    public String getValue(String configKey) {
        LambdaQueryWrapper<SysPropertyEntity> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysPropertyEntity::getK, configKey.toLowerCase());
        return Optional
                .ofNullable(getOne(queryWrapper, false))
                .map(SysPropertyEntity::getV)
                .orElse(null);
    }


    @Override
    public String getValue(String key, String defaultValue) {
        return Optional.ofNullable(getValue(key))
                .orElse(defaultValue);
    }

    /**
     * 取出整形值
     * @param configKey
     * @return
     */
    @Override
    public Integer getIntValue(String configKey) {

        return Integer.parseInt(getValue(configKey));
    }

    /**
     * 获取布尔值
     * @param configKey
     * @return
     */
    @Override
    public Boolean getBoolValue(String configKey) {
        String value = getValue(configKey);
        if (value.equals("true") || value.equals("1")) {
            return Boolean.TRUE;
        };
        return Boolean.FALSE;
    }

    /**
     * 取出字典
     * @param keys
     * @return
     */
    @Override
    public Map<String, Object> getValues(List<String> keys) {
        List<SysPropertyEntity> result = getList(keys);
        if (CollectionUtils.isEmpty(result)) return null;
        return result.stream().collect(Collectors.toMap(prop -> prop.getK(), prop -> {
            if (prop.getType().equals("number")) {
                return Integer.parseInt(prop.getV());
            }
            return prop.getV();
        }));
    }

    /**
     * 通过keys查询结果集
     * @param keys
     * @return
     */
    @Override
    public List<SysPropertyEntity> getList(List<String> keys) {
        return lambdaQuery()
                .eq(SysPropertyEntity::getEnabled, true)
                .eq(SysPropertyEntity::getIsDelete, false)
                .in(SysPropertyEntity::getK, keys)
                .list();
    }


    /**
     * 通过前缀查找相关配置
     * @param group
     * @return
     */
    @Override
    public Map findAllByGroup(String group) {
        HashMap property = new HashMap<>();
        List<SysPropertyEntity> propertyList = findListByGroup(group);
        propertyList.forEach(entity -> {
            property.put("group", entity.getGroups());
            property.put(entity.getK(), entity.getV());
        });

        return property;
    }

    @Override
    public Map findAllPublic() {
        List<SysPropertyEntity> propertyList =  lambdaQuery()
                .eq(SysPropertyEntity::getEnabled, true)
                .eq(SysPropertyEntity::getIsDelete, false)
                .in(SysPropertyEntity::getIsPublic, true)
                .list();

        return covertToMap(propertyList);
    }

    /**
     * 查找列表
     * @param groups
     * @return
     */
    @Override
    public LinkedHashMap getConfigMap(String groups) {
        List<SysPropertyEntity> result = lambdaQuery()
                .eq(SysPropertyEntity::getGroups, groups)
                .gt(SysPropertyEntity::getParentId, 0)
                .orderByAsc(SysPropertyEntity::getSort)
                .orderByAsc(SysPropertyEntity::getId)
                .list();
        return Optional
                .ofNullable(result)
                .map(list -> list
                        .stream()
                        .collect(Collectors.toMap(SysPropertyEntity::getK, Function.identity(), (x, y) -> y, LinkedHashMap::new))
                )
                .orElse(null);
    }

    @Override
    public Map<String, Object> getFormData(String groups) {
        List<SysPropertyEntity> result =
                lambdaQuery()
                        .eq(SysPropertyEntity::getGroups, groups)
                        .gt(SysPropertyEntity::getParentId, 0)
                        .orderByAsc(SysPropertyEntity::getSort)
                        .list();

        return covertToMap(result);
    }


    private Map<String, Object> covertToMap(List<SysPropertyEntity> todoList) {
        Map<String, Object> resultMap = new HashMap<>();
        if(CollectionUtils.isEmpty(todoList)) return null;
        todoList.forEach(entity -> {
            if (entity.getType().equals("switch")) {
                resultMap.put(entity.getK(), null != entity.getV() && entity.getV().equals("1") ? true : false);
            } else if (entity.getType().equals("number")) {
                resultMap.put(entity.getK(), Integer.parseInt(entity.getV()));
            } else {
                resultMap.put(entity.getK(), entity.getV());
            }
            if (entity.getMultiple()) {
                resultMap.put(entity.getK(), JSONObject.parse(entity.getV()));
            }
        });

        return resultMap;
    }

    /**
     * 查找列表
     * @param keys
     * @return
     */
    @Override
    public Map getConfigMap(List<String> keys) {
        List<SysPropertyEntity> result = getList(keys);
        return Optional
                .ofNullable(result)
                .map(list -> list
                        .stream()
                        .collect(Collectors.toMap(SysPropertyEntity::getK, Function.identity()))
                )
                .orElse(null);
    }

    @Override
    public Map getFormValue(List<String> keys) {
        List<SysPropertyEntity> result = getList(keys);

        return covertToMap(result);
    }


    @Override
    public List<SysPropertyEntity> findChildren(Integer parentId) {
        List<SysPropertyEntity> propertyList = lambdaQuery()
                .eq(SysPropertyEntity::getEnabled, true)
                .eq(SysPropertyEntity::getIsDelete, false)
                .eq(SysPropertyEntity::getParentId, parentId).list();

        return propertyList;
    }

    @Override
    public List<SysPropertyEntity> findAllRoot() {
        List<SysPropertyEntity> propertyList = findChildren(0);

        return propertyList;
    }

    @Override
    public List<SysPropertyEntity> findAll() {
        List<SysPropertyEntity> propertyList = lambdaQuery()
                .eq(SysPropertyEntity::getEnabled, true)
                .eq(SysPropertyEntity::getIsDelete, false).list();

        return propertyList;
    }


    private List<SysPropertyEntity> findListByGroup(String group) {
        List<SysPropertyEntity> propertyList = lambdaQuery()
                .eq(SysPropertyEntity::getGroups, group)
                .eq(SysPropertyEntity::getEnabled, true)
                .eq(SysPropertyEntity::getIsDelete, false).list();;

        return propertyList;
    }

    @Override
    public void update(PropertyMapDto dto) {
        if (null != dto && null != dto.getProperty()) {
            dto.getProperty().forEach((k, v) -> {
                v = v instanceof ArrayList ? JSONObject.toJSONString(v) :  v;
                lambdaUpdate()
                        .eq(SysPropertyEntity::getK, k)
                        .eq(SysPropertyEntity::getGroups, dto.getGroup())
                        .set(SysPropertyEntity::getV, v).update();
            });
        }
        if (dto.getGroup().equals("wx.pay")) {
            refreshWxPay();
        }
    }

    private Boolean isSync(String group) {
        SysPropertyEntity sysPropertyEntity = lambdaQuery()
                .eq(SysPropertyEntity::getGroups, group)
                .eq(SysPropertyEntity::getParentId, 0).one();
        if (null != sysPropertyEntity) {
            return sysPropertyEntity.getIsSync();
        }

        return false;
    }

    @Override
    public SysPropertyEntity save(PropertyDto dto) {
        SysPropertyEntity entity = BeanUtil.copyProperties(dto, SysPropertyEntity.class);
        saveOrUpdate(entity);
        AtomicInteger index = new AtomicInteger();
        if (CollectionUtils.isNotEmpty(dto.getAttributes())) {
            List<SysPropertyEntity> childList = dto.getAttributes().stream().map(e -> {
                e.setParentId(entity.getId());
                e.setGroups(entity.getGroups());
                e.setSort(index.incrementAndGet());
                return BeanUtil.copyProperties(e, SysPropertyEntity.class);
            }).collect(Collectors.toList());

            saveOrUpdateBatch(childList);
        }

        return entity;
    }

    /**
     * 将数据库的配置覆盖到系统默认配置，并刷新
     * @param propertyList
     */
    @Override
    public void sync(List<SysPropertyEntity> propertyList) {
        log.warn("force update");
        if (CollectionUtils.isEmpty(propertyList)) return;
        Map<String, Object> propertyMap = new HashMap<>();
        propertyList.forEach(entity -> {
            propertyMap.put(entity.getPk(), entity.getV());
        });
        MapPropertySource propertySource = new MapPropertySource("dynamic", propertyMap);
        //将修改后的配置设置到environment中
        environment.getPropertySources().addFirst(propertySource);
        //异步调用refresh方法，避免阻塞一直等待无响应
        new Thread(() -> contextRefresher.refresh()).start();
    }

    @Override
    public void syncFromDatabase() {
        sync(findAll());
    }

    /**
     * 刷新微信支付配置
     */
    @Override
    public void refreshWxPay() {
        Map<String, Object> mapConfig = getFormData("wx.pay");
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(mapConfig.get("mAppId").toString());
        wxPayConfig.setMchId(mapConfig.get("mchId").toString());
        wxPayConfig.setMchKey(mapConfig.get("mchKey").toString());
        wxPayConfig.setKeyPath(mapConfig.get("keyPath").toString());
        wxPayConfig.setNotifyUrl(mapConfig.get("notifyUrl").toString());

        wxPayService.setConfig(wxPayConfig);
    }
}
