package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.student.StudentDto;
import com.mtstore.server.beans.dto.student.StudentImportDto;
import com.mtstore.server.beans.dto.student.StudentImportRequestDto;
import com.mtstore.server.beans.entity.StudentEntity;
import com.mtstore.server.beans.mapper.StudentMapper;
import com.mtstore.server.service.StudentService;
import com.mtstore.server.service.SysImportLogService;
import com.mtstore.server.service.UserService;
import com.mtstore.server.util.FilterUtil;
import com.mtstore.server.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户绑定的机构 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2023-01-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImp extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    final UserService userService;

    final SysImportLogService importLogService;


    @Override
    @Transactional
    public StudentEntity saveOrUpdate(StudentDto dto) {
        StudentEntity entity = Optional
                .ofNullable(getById(dto.getId()))
                .orElseGet(() -> new StudentEntity());
        if (null == dto.getAvatarUrl()) {
            dto.setAvatarUrl("https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/dance/default/student.jpg");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setUid(0);
        entity.setUuid(String.format("O%d-S%d", LoggedUser.get().getTenantId(), 0));
        saveOrUpdate(entity);

        return entity;
    }

    @Override
    public Integer getSid() {

        return Optional
                .ofNullable(findByUid(LoggedUser.get().getUserId()))
                .map(StudentEntity::getId)
                .orElse(null);
    }

    @Override
    public StudentEntity findByUid(Integer uid) {
        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StudentEntity::getUid, uid);

        return getOne(queryWrapper, false);
    }

    @Override
    public StudentEntity findUserByPhone(String phone) {
        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StudentEntity::getPhone, phone);

        return getOne(queryWrapper, false);
    }

    @Override
    public void importExcel(StudentImportRequestDto requestDto) {
        final StringBuilder sb = new StringBuilder();
        final AtomicInteger indexHolder = new AtomicInteger();
        List<StudentEntity> addList = new ArrayList<>();
        List<StudentImportDto> studentList = EasyExcel.read(requestDto.getFile())
                .head(StudentImportDto.class)
                .sheet()
                .doReadSync();

        if (CollectionUtils.isEmpty(studentList)) {
            throw new RuntimeException("导入失败，Excel解析失败，请上传合法的Excel文件");
        }
        //移除头部两个,尾部一个
        studentList = studentList.subList(2, studentList.size() - 1);
        studentList = studentList.stream().filter(studentLi -> !studentLi.equals(new StudentImportDto())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(studentList)) {
            throw new RuntimeException("请在Excel中填充数据后上传");
        }
        studentList.forEach(dto -> {
            final int index = indexHolder.getAndIncrement();
            List validList = ValidateUtil.valid(dto);
            if (!CollectionUtils.isEmpty(validList)) {
                sb.append(String.format("第 %d 行，%s\n", index + 4, validList));
            }
            Optional.ofNullable(findUserByPhone(dto.getPhone())).ifPresent(s -> {
                sb.append(String.format("第 %d 行，%s， 手机号重复\n", index + 4, dto.getPhone()));
            });
        });
        log.info("studentList {}", studentList);
        if (sb.length() > 0) {
            importLogService.saveLog("失败", "STUDENT", requestDto.getFile(), studentList.size(), sb.toString());
            throw new RuntimeException("导入失败，详情请查看导入记录");
        }
        List<String> collect = studentList.stream().map(v -> v.getPhone())
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            sb.append(String.format("导入失败，Excel存在重复手机号，请检查, %s\n", collect));
            importLogService.saveLog("失败", "STUDENT", requestDto.getFile(), studentList.size(), sb.toString());
            throw new RuntimeException("导入失败，详情请查看导入记录");
        }
        studentList.forEach(dto -> {
            if (BeanUtil.isEmpty(dto)) return;
            StudentDto studentDto = BeanUtil.copyProperties(dto, StudentDto.class);
            studentDto.setEnabled("启用".equals(dto.getEnabled()));
            saveOrUpdate(studentDto);
        });

        importLogService.saveLog("成功", "STUDENT", requestDto.getFile(), studentList.size(), sb.toString());
    }

    @Override
    @SneakyThrows
    public Page<StudentEntity> findStudentsByGradeId(PageDto pageDto, QueryWrapper<StudentEntity>  wrapper) {
        Integer gradeId = Optional
                .ofNullable(pageDto.getExtra())
                .map(extra -> extra.get("gradeId"))
                .map(id -> Convert.convert(Integer.TYPE, id)).orElseThrow(() -> {
            throw new RuntimeException("班级id必传");
            }
        );
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto, wrapper);
        Page page = new Page<StudentEntity>(pageDto.getPage(),pageDto.getSize());

        return baseMapper.findStudentsByGradeId(gradeId , page, wrapper);
    }

    @Override
    @SneakyThrows
    public Page<StudentEntity> findStudentsByStoreId(PageDto pageDto, QueryWrapper<StudentEntity>  wrapper) {
        Integer gradeId = Optional
                .ofNullable(pageDto.getExtra())
                .map(extra -> extra.get("gradeId"))
                .map(id -> Convert.convert(Integer.TYPE, id)).orElseThrow(() -> {
                            throw new RuntimeException("班级id必传");
                        }
                );
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto, wrapper);
        Page page = new Page<StudentEntity>(pageDto.getPage(),pageDto.getSize());

        return baseMapper.findStudentsByGradeId(gradeId , page, wrapper);
    }

    @Override
    public List<StudentEntity> searchByNickname(String nickname) {
        return lambdaQuery()
                .eq(StudentEntity::getEnabled, true)
                .like(StudentEntity::getNickName, nickname).list();
    }

    @Override
    public List<Integer> searchSidsByNickname(String nickname) {
        List<StudentEntity> results = searchByNickname(nickname);
        return Optional
                .ofNullable(results)
                .map(result -> result.stream()
                        .map(StudentEntity::getId)
                        .collect(Collectors.toList())
                )
                .orElse(null);
    }


    @Override
    public void forceDelete(Integer id) {
        baseMapper.forceDelete(id);
    }
}
