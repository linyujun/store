package com.mtstore.server.config.init;

import com.mtstore.server.service.SysPropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author songsir
 */
@Order(1)
@Component
@Slf4j
@RequiredArgsConstructor
public class LoadPropertiesInitialize implements Initialize {

    private final SysPropertyService syncService;

    @Override
    public void execute() throws Exception {
        log.info("Load property from database");
        syncService.syncFromDatabase();
        log.info("Load wx.pay property from database");
        syncService.refreshWxPay();
    }
}
