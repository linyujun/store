package com.mtstore.server.service;

import com.mtstore.server.beans.entity.PrintTemplateEntity;
import com.mtstore.server.beans.dto.printer.template.PrintTemplateDto;

/**
* @author songsir
* 打印模板
*/
public interface PrintTemplateService extends IKService<PrintTemplateEntity, PrintTemplateDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
