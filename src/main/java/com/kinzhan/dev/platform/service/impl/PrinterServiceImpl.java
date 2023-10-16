package com.kinzhan.dev.platform.service.impl;

import com.kinzhan.dev.platform.service.FeiEPrinterService;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.PrinterEntity;
import com.kinzhan.dev.platform.service.PrinterService;
import com.kinzhan.dev.platform.beans.mapper.PrinterMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* @date 2023-06-15
*/
@Service
@RequiredArgsConstructor
public class PrinterServiceImpl extends ServiceImpl<PrinterMapper, PrinterEntity> implements PrinterService {

    final FeiEPrinterService feiEPrinterService;

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public String test(Integer id) {
        String content = "<CB>测试打印</CB><BR>";
        content += "名称　　　　　 单价  数量 金额<BR>";
        content += "--------------------------------<BR>";
        content += "饭　　　　　　 1.0    1   1.0<BR>";
        content += "炒饭　　　　　 10.0   10  10.0<BR>";
        content += "蛋炒饭　　　　 10.0   10  100.0<BR>";
        content += "鸡蛋炒饭　　　 100.0  1   100.0<BR>";
        content += "番茄蛋炒饭　　 1000.0 1   100.0<BR>";
        content += "西红柿蛋炒饭　 1000.0 1   100.0<BR>";
        content += "西红柿鸡蛋炒饭 100.0  10  100.0<BR>";
        content += "备注：加辣<BR>";
        content += "--------------------------------<BR>";
        content += "合计：xx.0元<BR>";
        content += "送货地点：广州市南沙区xx路xx号<BR>";
        content += "联系电话：13888888888888<BR>";
        content += "订餐时间：2016-08-08 08:08:08<BR>";
        content += "<QR>http://www.dzist.com</QR>";
        final String template = content;
        PrinterEntity entity = getById(id);
        if (null != entity) {
            return feiEPrinterService.print(entity.getSnId(), entity.getAppKey(), entity.getAppSecret(), template);
        }

        return null;
    }
}
