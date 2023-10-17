package com.mtstore.server.schedule;

import com.mtstore.server.beans.entity.StoreCombinationLogEntity;
import com.mtstore.server.service.StoreCombinationLogService;
import com.mtstore.server.service.StoreCombinationService;
import com.mtstore.server.service.StoreOrderService;
import com.mtstore.server.service.WxOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CombinationTask {

    final StoreCombinationService combinationService;

    final StoreCombinationLogService combinationLogService;

    final WxOrderService wxOrderService;

    final StoreOrderService storeOrderService;

    /**
     * 检查拼团活动状态
     */
    public void run() {
        log.info("------------------------检查拼团活动状态-------------------------");
        combinationService.check();
    }

    /**
     * 成团和过期检测
     */
    @Transactional
    public void checkLog() {
        List<StoreCombinationLogEntity> todoList = combinationLogService.lambdaQuery()
                .eq(StoreCombinationLogEntity::getStatus, 0)
                .eq(StoreCombinationLogEntity::getIsLeader, true)
                .list();
        if (CollectionUtils.isEmpty(todoList)) return;
        todoList.parallelStream().forEach(item -> {
            Long joinedNum = combinationLogService.lambdaQuery().eq(StoreCombinationLogEntity::getGroupId,item.getGroupId()).count();
            //拼团成功检测
            if (item.getGroupNum().equals(joinedNum.intValue())) {
                combinationLogService.lambdaUpdate()
                        .eq(StoreCombinationLogEntity::getGroupId, item.getGroupId())
                        .set(StoreCombinationLogEntity::getStatus, 1)
                        .set(StoreCombinationLogEntity::getStatusDesc, "成功")
                        .set(StoreCombinationLogEntity::getIsSuccess, true)
                        .update();
                return;
            }
            //超时退款，拼团失败
            if (item.getExpiredTime().isBefore(LocalDateTime.now())) {
                //TODO 执行退款操作
                wxOrderService.refundOrder(item.getOrderId());
                combinationLogService.lambdaUpdate()
                        .eq(StoreCombinationLogEntity::getGroupId, item.getGroupId())
                        .set(StoreCombinationLogEntity::getStatus, 2)
                        .set(StoreCombinationLogEntity::getStatusDesc, "失败")
                        .update();
                storeOrderService.refund(item.getOrderId());
                return;
            }
        });
    }
}
