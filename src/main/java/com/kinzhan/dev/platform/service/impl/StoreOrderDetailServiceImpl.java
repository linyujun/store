package com.kinzhan.dev.platform.service.impl;

import com.kinzhan.dev.platform.beans.dto.user.UserTotalDateVo;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.StoreOrderDetailEntity;
import com.kinzhan.dev.platform.service.StoreOrderDetailService;
import com.kinzhan.dev.platform.beans.mapper.StoreOrderDetailMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
* @author songsir
* @date 2023-04-20
*/
@Service
@RequiredArgsConstructor
public class StoreOrderDetailServiceImpl extends ServiceImpl<StoreOrderDetailMapper, StoreOrderDetailEntity> implements StoreOrderDetailService {


    @Override
    public List<StoreOrderDetailEntity> findAllByOrderId(String orderId) {

        return lambdaQuery().eq(StoreOrderDetailEntity::getOrderId, orderId).list();
    }

    @Override
    public Map<LocalDate, Long> getLast30daysTotal() {
        Map<LocalDate, Long> result = new LinkedHashMap();
        result.putAll(defaultLast30days());
        List<UserTotalDateVo> records = baseMapper.groupByLast30days();
        if (CollectionUtils.isNotEmpty(records)) {
            Map<LocalDate, Long> existMap = records.stream()
                    .collect(Collectors.toMap(UserTotalDateVo::getDate, UserTotalDateVo::getTotal));
            result.putAll(existMap);
        }

        return result;
    }


    private Map<LocalDate, Long> defaultLast30days() {
        LocalDate today = LocalDate.now();
        Map<LocalDate, Long> last30DaysList = new LinkedHashMap<>();
        for (int i = 30; i > 0; i--) {
            LocalDate date = today.minusDays(i);
            last30DaysList.put(date, 0L);
        }

        return last30DaysList;
    }
}
