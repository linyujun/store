package com.kinzhan.dev.platform.service.impl;

import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.mall.bargain.BargainCreateDto;
import com.kinzhan.dev.platform.beans.dto.mall.bargain.BargainHelpDto;
import com.kinzhan.dev.platform.beans.dto.mall.bargain.StoreBargainDto;
import com.kinzhan.dev.platform.beans.dto.mall.combination.StoreCombinationDto;
import com.kinzhan.dev.platform.beans.entity.*;
import com.kinzhan.dev.platform.beans.enums.ActivityStatusEnum;
import com.kinzhan.dev.platform.service.ProductDetailService;
import com.kinzhan.dev.platform.service.ProductService;
import com.kinzhan.dev.platform.service.StoreBargainLogService;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.service.StoreBargainService;
import com.kinzhan.dev.platform.beans.mapper.StoreBargainMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.io.IOException;

/**
* @author songsir
* @date 2023-06-07
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class StoreBargainServiceImpl extends ServiceImpl<StoreBargainMapper, StoreBargainEntity> implements StoreBargainService {

    final ProductService productService;

    final ProductDetailService productDetailService;

    final StoreBargainLogService bargainLogService;

    @Override
    public StoreBargainEntity saveOrUpdate(StoreBargainDto dto) {
        StoreBargainEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElse(new StoreBargainEntity());
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(ActivityStatusEnum.PENDING);
        entity.setStatusDesc(ActivityStatusEnum.PENDING.getDesc());
        saveOrUpdate(entity);

        return entity;
    }

    @Override
    public StoreBargainLogEntity create(BargainCreateDto dto) {
        StoreBargainEntity entity = Optional
                .ofNullable(getById(dto.getBargainId()))
                .orElseThrow(() -> new RuntimeException("砍价活动不存在，或已下线~"));
        if (!entity.getStatus().equals(ActivityStatusEnum.RUNNING)) {
            throw new RuntimeException("砍价活动不存在，或已下线~");
        }
        ProductEntity product = Optional
                .ofNullable(productService.getById(dto.getProductId()))
                .orElseThrow(() -> new RuntimeException("砍价商品不存在！"));
        ProductDetailEntity productDetailEntity = productDetailService.getById(dto.getProductDetailId());

        StoreBargainLogEntity longEntity = new StoreBargainLogEntity()
                .setBargainId(dto.getBargainId())
                .setParentId(0)
                .setProductId(dto.getProductId())
                .setCutPrice(BigDecimal.ZERO)
                .setProductDetailId(dto.getProductDetailId())
                .setImgUrl(product.getImgUrl())
                .setPrice(productDetailEntity.getPrice())
                .setPayPrice(productDetailEntity.getPrice())
                .setBargainPrice(productDetailEntity.getBargainPrice())
                .setProductName(product.getProductName())
                .setPriceRange(entity.getPriceRange())
                .setStartTime(entity.getStartTime())
                .setEndTime(entity.getEndTime())
                .setExpiredTime(LocalDateTime.now().plusMinutes(entity.getExpireMinute()))
                .setStatus(ActivityStatusEnum.RUNNING)
                .setStatusDesc(ActivityStatusEnum.RUNNING.getDesc());
        bargainLogService.saveOrUpdate(longEntity);

        return longEntity;
    }

    @Override
    @Transactional
    public StoreBargainLogEntity help(BargainHelpDto dto) {
        //TODO 砍过的人，不可以再砍，自己不可以帮自己砍
        StoreBargainLogEntity entity = Optional
                .ofNullable(bargainLogService.getById(dto.getBargainLogId()))
                .orElseThrow(() -> new RuntimeException("砍价活动不存在，或已下线~"));
        if (!entity.getStatus().equals(ActivityStatusEnum.RUNNING)) {
            throw new RuntimeException("砍价活动不存在，或已下线~");
        }
        Boolean isBargain = bargainLogService.lambdaQuery()
                .eq(StoreBargainLogEntity::getUid, LoggedUser.get().getUserId())
                .eq(StoreBargainLogEntity::getParentId, dto.getBargainLogId())
                .exists();
        if (isBargain) throw new RuntimeException("您已经帮他砍过了，下次再来吧~");
        //修改价格
        BigDecimal cutPrice = getCutPrice(entity.getPriceRange(), entity.getPayPrice(), entity.getBargainPrice());
        BigDecimal resultPrice = entity.getPayPrice().subtract(cutPrice);
//        log.info("reusltPrice {}", resultPrice);
        entity.setPayPrice(resultPrice);
        bargainLogService.updateById(entity);

        //创建一条砍价记录
        entity.setId(null);
        entity.setParentId(dto.getBargainLogId());
        entity.setUid(LoggedUser.get().getUserId());
        entity.setCutPrice(cutPrice);
        bargainLogService.save(entity);

        return entity;
    }


    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void check() {
        //开启
        lambdaUpdate()
                .eq(StoreBargainEntity::getEnabled, true)
                .eq(StoreBargainEntity::getStatus, 0)
                .le(StoreBargainEntity::getStartTime, LocalDateTime.now())
                .set(StoreBargainEntity::getStatus, 1)
                .set(StoreBargainEntity::getStatusDesc, "进行中")
                .update();

        //结束
        lambdaUpdate()
                .eq(StoreBargainEntity::getEnabled, true)
                .eq(StoreBargainEntity::getStatus, 1)
                .lt(StoreBargainEntity::getEndTime, LocalDateTime.now())
                .set(StoreBargainEntity::getStatus, 2)
                .set(StoreBargainEntity::getStatusDesc, "已结束")
                .update();
    }


    /**
     * 这段代码来自 CHATGPT，生成砍价区间
     * @param priceRange
     * @return
     */
    private BigDecimal getCutPrice(List<BigDecimal> priceRange, BigDecimal oldPrice, BigDecimal minPrice) {
        BigDecimal minValue = new BigDecimal(String.valueOf(priceRange.get(0)));
        BigDecimal maxValue = new BigDecimal(String.valueOf(priceRange.get(1)));
        Random random = new Random();
        BigDecimal randomValue = minValue.add(new BigDecimal(String.valueOf(random.nextDouble()))
                .multiply(maxValue.subtract(minValue)));
        randomValue = randomValue.setScale(2, RoundingMode.HALF_UP);
        if (oldPrice.subtract(randomValue).compareTo(minPrice) > 0) {
            return randomValue;
        } else {
            return oldPrice.subtract(minPrice);
        }
    }
}
