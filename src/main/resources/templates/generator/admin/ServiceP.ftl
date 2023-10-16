package ${package}.service;

import ${package}.beans.entity.${className}Entity;
import ${package}.beans.dto.${lowerCaseClassName}.${className}Dto;
import java.util.Map;
import java.util.List;
import java.io.IOException;
/**
* @author ${author}
* @date ${date}
*/
public interface ${className}Service extends IKService<${className}Entity, ${className}Dto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
