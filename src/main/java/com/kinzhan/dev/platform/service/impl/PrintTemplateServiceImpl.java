package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.PrintTemplateEntity;
import com.kinzhan.dev.platform.service.PrintTemplateService;
import com.kinzhan.dev.platform.beans.mapper.PrintTemplateMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-06-15
*/
@Service
@RequiredArgsConstructor
public class PrintTemplateServiceImpl extends ServiceImpl<PrintTemplateMapper, PrintTemplateEntity> implements PrintTemplateService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
