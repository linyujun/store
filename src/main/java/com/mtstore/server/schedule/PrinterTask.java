package com.mtstore.server.schedule;

import com.alibaba.fastjson.JSONObject;
import com.mtstore.server.beans.entity.PrinterEntity;
import com.mtstore.server.service.FeiEPrinterService;
import com.mtstore.server.service.PrinterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试用
 * @author smallsong
 * @date 2022/05/15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PrinterTask {

    final PrinterService printerService;
    final FeiEPrinterService ePrinterService;

    public void run(){
        log.info("同步打印机状态任务~");
        List<PrinterEntity> todoList = printerService.lambdaQuery().eq(PrinterEntity::getEnabled, true).list();
        todoList.parallelStream().forEach(printer -> {
            String payload = ePrinterService.queryPrinterStatus(printer.getSnId(), printer.getAppKey(), printer.getAppSecret());
            JSONObject result = JSONObject.parseObject(payload);
            printer.setIsOnline(false);
            if (null != result && result.containsKey("data") && result.getString("data").contains("在线")) {
                printer.setIsOnline(true);
            }
            printer.setLastCheckTime(LocalDateTime.now());
            printerService.saveOrUpdate(printer);
            log.info("result {}", result);
        });
    }
}
