package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.mall.product.ProductDetailDto;
import com.mtstore.server.beans.dto.mall.product.ProductDto;
import com.mtstore.server.beans.dto.mall.product.ActivityProductDto;
import com.mtstore.server.beans.entity.ProductDetailEntity;
import com.mtstore.server.beans.entity.ProductEntity;
import com.mtstore.server.beans.mapper.StoreProductMapper;
import com.mtstore.server.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author songsir
* spu
*/
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<StoreProductMapper, ProductEntity> implements ProductService {

    final CategoryService categoryService;

    final ProductDetailService productDetailService;

    final CouponService couponService;

    final StoreServiceService storeServiceService;

    final SysPropertyService propertyService;

    @Override
    public ProductEntity getDetail(Serializable id) {
        //TODO，增加足迹功能逻辑
        ProductEntity entity = baseMapper.selectById(id);
        Optional.ofNullable(entity).ifPresent(v -> {
            entity.setDetails(productDetailService.findAllByProductId(v.getId()));
            if (CollectionUtils.isNotEmpty(v.getServiceIds())) {
                entity.setServices(storeServiceService.listByIds(v.getServiceIds()));
            }
            setCoupons(entity);
            lambdaUpdate().eq(ProductEntity::getId, id).setSql("visited=visited+" + 1).update();
        });

        return entity;
    }


    /**
     * 匹配可用的优惠券给前端
     * @param entity
     */
    private void setCoupons(ProductEntity entity) {
        List<Integer> resultList = new ArrayList<>();
        Optional.ofNullable(couponService.findCouponsByCategoryId(entity.getCategoryId())).ifPresent(resultList::addAll);
        Optional.ofNullable(couponService.findCouponsByProductId(entity.getId())).ifPresent(resultList::addAll);
        Optional.ofNullable(couponService.findCouponsByPublic()).ifPresent(resultList::addAll);

        entity.setCoupons(couponService.listByIds(resultList));
    }


    @Override
    @Transactional
    public ProductEntity save(ProductDto dto) {
        ProductEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElseGet(() -> new ProductEntity());
        BeanUtils.copyProperties(dto, entity);
        saveOrUpdate(entity);
        if (null != dto.getDetails()) {
            productDetailService.save(dto.getDetails(), entity.getId());
            BigDecimal minPrice = dto.getDetails()
                    .stream()
                    .map(ProductDetailDto::getPrice)
                    .min(Comparator.naturalOrder())
                    .orElse(BigDecimal.ZERO);
            Integer totalStock = dto.getDetails()
                    .stream()
                    .mapToInt(ProductDetailDto::getStockNum).sum();
            entity.setPrice(minPrice);
            entity.setStockNum(totalStock);
        } else {
            productDetailService.save(entity, entity.getId());
        }

        saveOrUpdate(entity);
        return entity;
    }

    /**
     * 配置积分商品信息
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public ProductEntity saveCreditProduct(ActivityProductDto dto) {
        ProductEntity entity = Optional
                .ofNullable(dto.getProductId())
                .map(this::getById)
                .orElseThrow(() -> new RuntimeException("商品不存在~"));
        if (null == dto.getDetails()) throw new RuntimeException("积分兑换详情未设置");
        List<String> activityList = Optional.ofNullable(entity.getActivity()).orElse(new ArrayList());
        activityList.add("CREDIT");
        activityList = activityList.stream().distinct().collect(Collectors.toList());
        //存储最低积分到主商品
        Optional<BigDecimal> minimumPrice = dto.getDetails().stream()
                .map(ProductDetailDto::getCredit)
                .min(BigDecimal::compareTo);
        entity.setActivity(activityList);
        entity.setCredit(minimumPrice.orElse(BigDecimal.ZERO));
        saveOrUpdate(entity);
        dto.getDetails().forEach((item) -> {
            productDetailService.lambdaUpdate()
                    .eq(ProductDetailEntity::getId, item.getId())
                    .set(ProductDetailEntity::getCredit, item.getCredit())
                    .set(ProductDetailEntity::getCreditPrice, item.getCreditPrice())
                    .set(ProductDetailEntity::getCreditNum, item.getCreditNum())
                    .update();
        });

        return entity;
    }

    /**
     * 活动商品移除
     * @param activity
     * @param id
     */
    @Override
    public void removeActivity(String activity, Integer id) {
        //TODO 约束性检查，需要将商品移除活动
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            List<String> todoList = entity.getActivity();
            todoList.remove(activity);
            entity.setActivity(todoList);

            saveOrUpdate(entity);
        });
    }

    /**
     * 切换状态
     * @param id
     */
    @Override
    public void enabled(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());

            saveOrUpdate(entity);
        });
    }

    /**
     * 上架
     * @param id
     */
    @Override
    public void online(Integer id) {
        lambdaUpdate()
                .eq(ProductEntity::getId, id)
                .set(ProductEntity::getEnabled, true).update();
    }

    /**
     * 下架
     * @param id
     */
    @Override
    public void offline(Integer id) {
        lambdaUpdate()
                .eq(ProductEntity::getId, id)
                .set(ProductEntity::getEnabled, false).update();
    }

    /**
     * 查找分类下的所有商品，包含子孙
     * @param categoryId
     * @return
     */
    @Override
    public List<ProductEntity> findAllByCategoryId(Integer categoryId) {
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper();
        wrapper.lambda().eq(ProductEntity::getEnabled, true);
        wrapper.lambda().eq(ProductEntity::getIsDelete, false);
        List<Integer> cateIds = categoryService.findAllIdByParentId(categoryId);
        if (cateIds.size() > 1) {
            wrapper.lambda().in(ProductEntity::getCategoryId, cateIds);
        } else {
            wrapper.lambda().eq(ProductEntity::getCategoryId, categoryId);
        }

        return list(wrapper);
    }

    /**
     * 添加购物车，扣减库存
     * @param productId
     * @param cartNum
     * @return
     */
    @Override
    @Transactional
    public Boolean stock(Integer productId, Integer detailId, Integer cartNum) {
        //扣减商品详情表
        Boolean result1 = productDetailService.stock(detailId, cartNum);
        ProductEntity entity = Optional.ofNullable(getById(productId)).orElseThrow(() -> new RuntimeException("商品不存在，或已下架"));
        Integer resultNum =  entity.getStockNum() - cartNum;
        if (resultNum < 0) {
            throw new RuntimeException("库存不足");
        }
        entity.setStockNum(resultNum);
        Boolean result2 = updateById(entity);

        return result2 && result1;
    }

    /**
     * 销量，购买成功增加销量
     * @param productId
     * @param cartNum
     * @return
     */
    @Override
    @Transactional
    public void sales(Integer productId, Integer detailId, Integer cartNum) {
        productDetailService.sales(detailId, cartNum);
        Optional.ofNullable(getById(productId)).ifPresent(entity -> {
            entity.setSales(entity.getSales() + cartNum);
            updateById(entity);
        });
    }

    @Override
    public BigDecimal getTotalVisits() {
        return Optional.ofNullable(baseMapper.getTotalVisited()).orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getTotalSales() {

        return Optional.ofNullable(baseMapper.getTotalSales()).orElse(BigDecimal.ZERO);
    }

}
