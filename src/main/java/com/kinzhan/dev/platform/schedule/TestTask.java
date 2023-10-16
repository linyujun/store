package com.kinzhan.dev.platform.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试用
 * @author smallsong
 * @date 2022/05/15
 */
@Slf4j
@Component
public class TestTask {

    public void run(){
        log.info("do run ");
    }

    public void run1(String str){
        log.info("do run1 " + str);
    }
}
