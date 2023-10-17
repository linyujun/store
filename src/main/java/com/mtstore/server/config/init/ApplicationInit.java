package com.mtstore.server.config.init;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.open.api.WxOpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author songsir
 */
@Component
@Slf4j
public class ApplicationInit implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    List<Initialize> initializes;

    @Autowired
    WxOpenService wxOpenService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            initializes.forEach(initialize -> {
                try {
                    initialize.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ApplicationReadyEvent : Fail to Execute ApplicationInit!");
            System.exit(0);
        }
        log.info("ApplicationReadyEvent : ApplicationInit successful !");
    }
}
