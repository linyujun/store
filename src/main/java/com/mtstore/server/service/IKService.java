package com.mtstore.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtstore.server.beans.dto.BaseDto;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.filter.Sort;
import com.mtstore.server.util.FilterUtil;
import com.mtstore.server.util.filter.QueryHelpPlus;
import com.mtstore.server.util.helper.LocalDateConverter;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IKService<T, D extends BaseDto> extends IService<T> {
    /**
     * 简化添加和更新逻辑
     * @param dto
     * @return
     */
    default T saveOrUpdate(D dto){
        T entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElseGet(() -> {
                    try {
                        return this.getEntityClass().newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
        BeanUtils.copyProperties(dto, entity);
        saveOrUpdate(entity);

        return entity;
    }


//    @Override
//    default T getById(Serializable id) {
//        T entity = this.getById(id);
//        Optional
//                .ofNullable(entity)
//                .ifPresent(this::converter);
//        return entity;
//    }

    /**
     * 配合前端二次封装分页查询
     * @param pageDto
     * @param wrapper
     * @return
     */
    default Page getPageList(PageDto pageDto, QueryWrapper wrapper) {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto, wrapper);
        Page page = new Page<T>(pageDto.getPage(),pageDto.getSize());
        Page result = page(page, wrapper);

        return result;
    }


    /**
     * 新型分页，前端采用
     * @param query
     * @param wrapper
     * @return
     */
    default Page pageList(QueryDto query, QueryWrapper wrapper) {
        QueryWrapper finalWrapper = QueryHelpPlus.getPredicate(this.getEntityClass(), query.getFilter(), wrapper);
        Page pageable = new Page<T>(query.getPage(),query.getSize());
        List<Sort> sortList = query.getSort();
        if (null != sortList && sortList.size() > 0) {
            sortList.forEach(sort -> {
                String sortKey = sort.getKey();
                String direction = sort.getValue();
                if (null == sort.getValue()) {
                    return;
                }
                if ("desc".equalsIgnoreCase(direction)) {
                    finalWrapper.orderByDesc(sortKey);
                }
                if ("asc".equalsIgnoreCase(direction)) {
                    finalWrapper.orderByAsc(sortKey);
                }
            });
        } else {
            finalWrapper.orderByDesc("id");
        }

        return page(pageable, finalWrapper);
    }

    /**
     * 下载
     * @param pageDto
     * @param wrapper
     */
    default void download(HttpServletResponse response, Class clazz, PageDto pageDto, QueryWrapper wrapper) throws IOException {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto.getFilter(), wrapper);
        List<T> todoList = list(wrapper);
        List resultList = new ArrayList();
        for(T fromBean: todoList) {
            resultList.add(BeanUtil.copyProperties(fromBean, clazz));
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = IdUtil.fastSimpleUUID();
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), clazz).sheet("数据").registerConverter(new LocalDateConverter()).doWrite(resultList);
    }
}
