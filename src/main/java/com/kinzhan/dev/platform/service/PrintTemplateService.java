package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.PrintTemplateEntity;
import com.kinzhan.dev.platform.beans.dto.printer.template.PrintTemplateDto;

/**
* @author songsir
* @date 2023-06-15
*/
public interface PrintTemplateService extends IKService<PrintTemplateEntity, PrintTemplateDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
