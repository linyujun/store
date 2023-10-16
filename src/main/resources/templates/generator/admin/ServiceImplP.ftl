package ${package}.service.impl;

import lombok.RequiredArgsConstructor;
import ${package}.beans.entity.${className}Entity;
import ${package}.service.${className}Service;
import ${package}.beans.mapper.${className}Mapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author ${author}
* @date ${date}
*/
@Service
@RequiredArgsConstructor
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}Entity> implements ${className}Service {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
