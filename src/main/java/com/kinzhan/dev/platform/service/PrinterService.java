package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.PrinterEntity;
import com.kinzhan.dev.platform.beans.dto.printer.PrinterDto;
import java.util.Map;
import java.util.List;
import java.io.IOException;
/**
* @author songsir
* @date 2023-06-15
*/
public interface PrinterService extends IKService<PrinterEntity, PrinterDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 打印测试
     * @param id
     */
    String test(Integer id);
}
