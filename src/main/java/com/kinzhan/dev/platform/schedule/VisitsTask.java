package com.kinzhan.dev.platform.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Zheng Jie
 * @date 2018-12-25
 */
@Slf4j
@Component
public class VisitsTask {

    public void run(){
        log.info("run success");
    }
}
