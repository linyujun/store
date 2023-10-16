package com.kinzhan.dev.platform.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.dto.filter.Filter;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.filter.Sort;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
public class FilterUtil {

    public static void main(String[] args) {
    }

    public static QueryWrapper convertFilterDtoToWrapper(PageDto pageDto, QueryWrapper queryWrapper) {
        if (null == pageDto) return queryWrapper;
        QueryWrapper wrapper = Optional.ofNullable(queryWrapper).orElse(new QueryWrapper());
        List<Filter> filterList = pageDto.getFilter();
        List<Sort> sortList = pageDto.getSort();
        wrapper.orderByDesc("id");
        if (null != sortList && sortList.size() > 0) {
            sortList.forEach(sort -> {
                String sortKey = sort.getKey();
                String direction = sort.getValue();
                if (null == sort.getValue()) {
                    return;
                }
                if ("descending".equalsIgnoreCase(direction)) {
                    wrapper.orderByDesc(sortKey);
                }
                if ("ascending".equalsIgnoreCase(direction)) {
                    wrapper.orderByAsc(sortKey);
                }
            });
        }
        if (CollectionUtils.isEmpty(filterList)) {
            return wrapper;
        }

        filterList.forEach(filter -> {
            String filedMixed = filter.getField();
            if (null == filter.getField() || null == filter.getValue()) return;
            String[] filedList = filedMixed.split("_");
            String filed = StringUtils.toUnderlineCase(filedList[0]);
            if (filter.getLink().equalsIgnoreCase("or")) {
                wrapper.or();
            }
            if (filedMixed.contains("_eq")) {
                wrapper.eq(filed, filter.getValue());
            }
            if (filedMixed.contains("_in") &&  null != filter.getValue()) {
                wrapper.in(filed, (List) filter.getValue());
            }
            if (filedMixed.contains("_gt")) {
                wrapper.gt(filed, filter.getValue());
            }
            if (filedMixed.contains("_ge")) {
                wrapper.ge(filed, filter.getValue());
            }
            if (filedMixed.contains("_lt")) {
                wrapper.lt(filed, filter.getValue());
            }
            if (filedMixed.contains("_le")) {
                wrapper.le(filed, filter.getValue());
            }
            if (filedMixed.contains("_like")) {
                wrapper.like(filed, filter.getValue());
            }
            if (filedMixed.contains("_between")) {
                List values = (List) filter.getValue();
                wrapper.between(filed, values.get(0), values.get(1));
            }
        });

        return wrapper;
    }

    public static QueryWrapper convertFilterDtoToWrapper(List<Filter> filterList, QueryWrapper queryWrapper) {
        QueryWrapper wrapper = Optional.ofNullable(queryWrapper).orElse(new QueryWrapper());
//        wrapper.orderByDesc("id");
        if (null != filterList && filterList.size() > 0) {
            filterList.forEach(filter -> {
                String filedMixed = filter.getField();
                String[] filedList = filedMixed.split("_");
                String filed = StringUtils.toUnderlineCase(filedList[0]);
                if (filter.getLink().equalsIgnoreCase("or")) {
                    wrapper.or();
                }
                if (null == filter.getValue()) {
                    return;
                }
                if (filedMixed.contains("_eq")) {
                    wrapper.eq(filed, filter.getValue());
                }
                if (filedMixed.contains("_gt")) {
                    wrapper.gt(filed, filter.getValue());
                }
                if (filedMixed.contains("_in")) {
                    wrapper.in(filed, filter.getValue());
                }
                if (filedMixed.contains("_ge")) {
                    wrapper.ge(filed, filter.getValue());
                }
                if (filedMixed.contains("_lt")) {
                    wrapper.lt(filed, filter.getValue());
                }
                if (filedMixed.contains("_le")) {
                    wrapper.le(filed, filter.getValue());
                }
                if (filedMixed.contains("_like")) {
                    wrapper.like(filed, filter.getValue());
                }
            });
        }

        return wrapper;
    }
}
