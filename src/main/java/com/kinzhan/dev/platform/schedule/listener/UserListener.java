package com.kinzhan.dev.platform.schedule.listener;

import com.kinzhan.dev.platform.beans.enums.BillEnum;
import com.kinzhan.dev.platform.schedule.event.user.UserCheckInEvent;
import com.kinzhan.dev.platform.schedule.event.user.UserInvitedEvent;
import com.kinzhan.dev.platform.schedule.event.user.UserRegisterEvent;
import com.kinzhan.dev.platform.service.UserBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserListener {

    final UserBillService userBillService;

    /**
     * 注册监听实现方法
     * @param event 用户注册事件
     */
    @EventListener
    public void invite(UserInvitedEvent event)
    {
        log.info("邀请用户赠送积分！{}", event.getInviteId());
        userBillService.incomeCredit(event.getInviteId(), BillEnum.BILL_ACTION_INVITE_USER);
    }

    @EventListener
    public void register(UserRegisterEvent event)
    {
        log.info("用户注册赠送积分！{}", event.getUid());
        userBillService.incomeCredit(event.getUid(), BillEnum.BILL_ACTION_FIRST_LOGIN);
    }

    @EventListener
    public void checkIn(UserCheckInEvent event)
    {
        log.info("用户签到赠送积分！{}", event.getUid());
        userBillService.incomeCredit(event.getUid(), BillEnum.BILL_ACTION_CHECK_IN);
    }
}
