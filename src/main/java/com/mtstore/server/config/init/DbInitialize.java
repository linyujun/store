package com.mtstore.server.config.init;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author songsir
 */
@Order(1)
@Component
@Slf4j
public class DbInitialize implements Initialize {

    @Override
    public void execute() throws Exception {
        log.info("db init");
    }
}
