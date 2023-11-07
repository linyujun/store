package com.mtstore.server.service;

import com.mtstore.server.beans.entity.PrinterEntity;
import com.mtstore.server.beans.dto.printer.PrinterDto;

/**
* @author songsir
* 打印机管理
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
