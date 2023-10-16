package com.kinzhan.dev.platform.config;

import com.aliyuncs.exceptions.ClientException;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.kinzhan.dev.platform.beans.common.R;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author ww
 * @date 2021/6/10
 **/
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    public Object validationExceptionHandler(ValidationException e) {
        e.printStackTrace();

        return new R<>(1005, e.getMessage(), false);
    }


    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Object globalRuntimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();

        return new R<>(1003, e.getMessage(), false);
    }

    @ResponseBody
    @ExceptionHandler(value = ClientException.class)
    public Object globalClientExceptionHandler(ClientException e) {
        e.printStackTrace();

        return new R<>(1003, e.getErrMsg(), false);
    }

    @ResponseBody
    @ExceptionHandler(value = NoSuchElementException.class)
    public Object globalNoSuchElementExceptionHandler(NoSuchElementException e) {
        e.printStackTrace();

        return new R<>(1008, "记录不存在请确认", false);
    }


    @ResponseBody
    @ExceptionHandler(value = WxErrorException.class)
    public Object globalWxErrorExceptionHandler(WxErrorException e) {
        e.printStackTrace();

        return new R<>(1007, e.getMessage(), false);
    }

    @ResponseBody
    @ExceptionHandler(value = WxPayException.class)
    public Object globalWxPayExceptionHandler(WxPayException e) {
        e.printStackTrace();

        return new R<>(1008, e.getMessage(), false);
    }

    @ResponseBody
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Object globalNoSuchElementExceptionHandler(DuplicateKeyException e) {
        e.printStackTrace();

        return new R<>(1006, "存在重复记录请确认~", false);
    }

    /**
     * 验证请求的字段是否为空
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Object validExceptionHandler(Exception e) {
        log.error("Error: {}", e.getMessage());
        BindingResult result;
        if (e instanceof BindException) {
            result = ((BindException) e).getBindingResult();
        } else {
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        }
        final List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, Object> data = new HashMap<>();
        List<String> msgList = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            msgList.add(error.getDefaultMessage());
            data.put(error.getField(), error.getDefaultMessage());
        }

        return new R<>(1005, msgList.stream().collect(Collectors.joining(",")), data);
    }
}
