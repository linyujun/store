package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.mtstore.server.beans.dto.user.UserPromoterDto;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.beans.mapper.UserMapper;
import com.mtstore.server.service.PromoterService;
import com.mtstore.server.service.SysPropertyService;
import com.mtstore.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromoterServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements PromoterService {

    final UserService userService;

    final SysPropertyService sysPropertyService;

    /**
     * 查找用户的一级成员
     * @param userId
     * @return
     */
    @Override
    public List<UserEntity> findFirstChildren(Integer userId) {
        return lambdaQuery()
                .eq(UserEntity::getInviteId, userId)
                .eq(UserEntity::getEnabled, true)
                .list();
    }

    /**
     * 查找用户的二级成员
     * @param userId
     * @return
     */
    @Override
    public List<UserEntity> findSecondChildren(Integer userId) {
        List<UserEntity> firstList = findFirstChildren(userId);
        if (CollectionUtils.isNotEmpty(firstList)) {
            List<Integer> userIds = firstList.stream().map(UserEntity::getId).distinct().collect(Collectors.toList());
            return lambdaQuery()
                    .in(UserEntity::getInviteId, userIds)
                    .eq(UserEntity::getEnabled, true)
                    .list();
        }

        return null;
    }

    /**
     *  取消分销资格
      * @param userId
     */
    @Override
    public void cancel(Integer userId) {

        lambdaUpdate()
                .eq(UserEntity::getId, userId)
                .set(UserEntity::getIsPromoter, false)
                .update();
    }

    /**
     * 统计下级数量
     * @param userId
     * @return
     */
    @Override
    public Long countChild(Integer userId) {
        Long secondCount = 0L;
        Long firstCount = lambdaQuery()
                .eq(UserEntity::getInviteId, userId)
                .eq(UserEntity::getEnabled, true)
                .count();
        List<UserEntity> firstList = findFirstChildren(userId);
        if (CollectionUtils.isNotEmpty(firstList)) {
            List<Integer> userIds = firstList.stream().map(UserEntity::getId).distinct().collect(Collectors.toList());
            secondCount = lambdaQuery()
                    .in(UserEntity::getInviteId, userIds)
                    .eq(UserEntity::getEnabled, true)
                    .count();
        }

        return secondCount + firstCount;
    }

    @Override
    public List<UserPromoterDto> findParentPromoters(Integer userId) {
        //TODO 处理分销开关，处理分销层级
        log.info("promoteEnabled {}", sysPropertyService.getBoolValue("promoteEnabled"));
        if (!sysPropertyService.getBoolValue("promoteEnabled")) return null;
        Map<String, Integer> rateMap = sysPropertyService.getValues(Arrays.asList(new String[]{"firstRate", "secondRate"}));
        Optional<UserEntity> firstUser = findParent(userId);
        if (!firstUser.isPresent()) return null;
        log.info("rateMap {}",rateMap);
        List<UserPromoterDto> resultList = new ArrayList<>();
        Integer firstUid = firstUser.map(UserEntity::getId).orElse(null);
        if (firstUid != null && firstUid > 0) {
            resultList.add(new UserPromoterDto().setUserId(firstUid).setFeeRate(BigDecimal.valueOf(rateMap.get("firstRate")/100d)));
        }
        //获取分销层级，返回分销方案
        if (sysPropertyService.getIntValue("promoteEnabled").equals(2)) {
            Integer secondUid = findParent(firstUid).map(UserEntity::getId).orElse(null);
            if (secondUid != null && secondUid > 0) {
                resultList.add(new UserPromoterDto().setUserId(secondUid).setFeeRate(BigDecimal.valueOf(rateMap.get("secondRate")/100d)));
            }
        }

        log.info("resultList {}",resultList);
        return resultList;
    }

    /**
     * 查找父级分销员
     * @param userId
     * @return
     */
    private Optional<UserEntity> findParent(Integer userId) {
        return Optional.ofNullable(getById(userId)).map(UserEntity::getInviteId).map(this::getById);
    }

}
