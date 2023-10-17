package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.student.StudentDto;
import com.mtstore.server.beans.dto.student.StudentImportRequestDto;
import com.mtstore.server.beans.entity.StudentEntity;

import java.util.List;

/**
 * <p>
 * 用户绑定的机构 服务类
 * </p>
 *
 * @author songsir
 * @since 2023-01-03
 */
public interface StudentService extends IKService<StudentEntity, StudentDto> {

    Integer getSid();

    StudentEntity findByUid(Integer uid);

    StudentEntity findUserByPhone(String phone);

    void importExcel(StudentImportRequestDto requestDto);

    Page<StudentEntity> findStudentsByGradeId(PageDto pageDto, QueryWrapper<StudentEntity> wrapper);

    Page<StudentEntity> findStudentsByStoreId(PageDto pageDto, QueryWrapper<StudentEntity> wrapper);

    List<StudentEntity> searchByNickname(String nickname);

    List<Integer> searchSidsByNickname(String nickname);

    void forceDelete(Integer id);
}
