package com.mtstore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtstore.server.beans.dto.system.PropertyDto;
import com.mtstore.server.beans.dto.system.PropertyMapDto;
import com.mtstore.server.beans.entity.sys.SysPropertyEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统配置处理
 * </p>
 *
 * @author songsir
 * @since 2021-11-18
 */
public interface SysPropertyService extends IService<SysPropertyEntity> {

    Boolean isDev();


    /**
     * 获取配置Map
     * @return
     */
    Map getMap();

    /**
     * 保存
     */
    void save(Map dto);


    void save(String key, String value);

    /**
     * 获取对应的配置值
     * @param configKey
     * @return
     */
    String getValue(String configKey);


    /**
     * 查找多个键值
     * @param keys
     * @return
     */
    Map getValues(List<String> keys);

    /**
     * 带默认值的获取对应的配置值
     * @param key
     * @param defaultValue
     * @return
     */
    String getValue(String key, String defaultValue);

    /**
     *
     * @param configKey
     * @return
     */
    Integer getIntValue(String configKey);

    /**
     * 获取布尔值
     * @param configKey
     * @return
     */
    Boolean getBoolValue(String configKey);

    /**
     * 通过keys列表查找
     * @param keys
     * @return
     */
    List<SysPropertyEntity> getList(List<String> keys);

    /**
     * 通过前缀查找相关配置
     * @param group
     * @return
     */
    Map findAllByGroup(String group);


    /**
     * 获取所有开放数据
     * @return
     */
    Map findAllPublic();

    /**
     * 获取配置字典
     * @param group
     * @return
     */
    Map getConfigMap(String group);

    /**
     * 获取结果字典
     * @param group
     * @return
     */
    Map getFormData(String group);

    /**
     * 获取配置字典
     * @param keys
     * @return
     */
    Map getConfigMap(List<String> keys);

    /**
     * 获取结果字典
     * @param keys
     * @return
     */
    Map getFormValue(List<String> keys);

    List<SysPropertyEntity> findChildren(Integer parentId);

    /**
     * 查找所有根属性
     * @return
     */
    List<SysPropertyEntity> findAllRoot();

    /**
     * 查找所有待同步数据
     * @return
     */
    List<SysPropertyEntity> findAll();

    /**
     * 更新属性
     * @param dto
     */
    void update(PropertyMapDto dto);

    /**
     * 新增属性
     * @param dto
     */
    SysPropertyEntity save(PropertyDto dto);

    /**
     * 将配置同步到系统属性中
     * @param propertyList
     */
    void sync(List<SysPropertyEntity> propertyList);

    /**
     * 启动系统将配置加载到默认属性中
     */
    void syncFromDatabase();

    /**
     * 刷新支付配置
     */
    void refreshWxPay();
}
