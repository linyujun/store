package com.mtstore.server.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.push.PushDto;
import com.mtstore.server.beans.entity.sys.SysPushEntity;
import com.mtstore.server.beans.mapper.SysPushMapper;
import com.mtstore.server.service.SysPushService;
import com.mtstore.server.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songsir
 * @since 2023-02-02
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysPushServiceImp extends ServiceImpl<SysPushMapper, SysPushEntity> implements SysPushService {

    private ExecutorService executor = ThreadUtil.newExecutor(5);

    @Override
    public Boolean save(PushDto dto) {
        SysPushEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElse(new SysPushEntity());
        BeanUtils.copyProperties(dto, entity);
        Boolean result = saveOrUpdate(entity);
        sendMessage(entity);

        return result;
    }

    private void sendMessage(SysPushEntity entity) {
        QueryWrapper queryWrapper = FilterUtil.convertFilterDtoToWrapper(entity.getFilters(), null);
        List<String> pushList = new ArrayList<>();
        log.info("pushList {}", pushList);
        for (int i = 0; i < pushList.size(); i++) {
            if (!executor.isShutdown()) {
                PushPayload pushPayload = buildPayload(pushList.get(i), entity.getTitle(), entity.getContent());
                executor.execute(() -> doPush(pushPayload));
            }
            executor.shutdown();
        }
    }

    private void doPush(PushPayload payload) {
        JPushClient jpushClient = new JPushClient("dc9191acb2da4da61d900ffc", "32ad7d61146a5f6f2b022a1e", null, ClientConfig.getInstance());
        // For push, all you need do is to build PushPayload object.
        try {
            PushResult result = jpushClient.sendPush(payload);
            log.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    private PushPayload buildPayload(String pushId, String title, String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(pushId))
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title).build())
                        .addPlatformNotification(IosNotification.newBuilder().build())
                        .build())
                .build();
    }
}
