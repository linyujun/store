package com.kinzhan.dev.platform.beans.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 统一JSON返回格式
 * @param <T>
 */
@ApiModel("接口通用返回数据")
public class R<T> implements Serializable {

    private static final String SUCCESS = "SUCCESS";

    private static final String ERROR = "ERROR";

    private static final Integer ERROR_CODE = 1001;

    private static final Integer SUCCESS_CODE = 0;

    /**
     *
     */
    private static final long serialVersionUID = -3288962446305233126L;

    /**
     * 非业务错误码，仅在<code>success==false</code>时才有效
     */
    @ApiModelProperty("标识代码, 0表示成功，非0表示出错")
    private int code;

    /**
     * 返回信息
     */
    @ApiModelProperty("提示信息,供报错时使用")
    private String message;

    /**
     * 是否处理成功
     */
    @ApiModelProperty("true 成功，false 失败")
    private boolean success;

    /**
     * 要返回的数据类型
     */
    @ApiModelProperty("返回的数据")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @param code    错误码
     * @param message 信息
     * @param data    数据
     */
    public R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 直接抛出错误
     */
    public R(String message) {
        this.code = ERROR_CODE;
        this.message = message;
        this.success = false;
    }

    /**
     * 一般用于成功的输出
     *
     * @param message
     * @param data
     */
    public R(String message, T data) {
        this.code = SUCCESS_CODE;
        this.message = message;
        this.data = data;
        this.success = true;
    }

    public static <T> R<T> ok() {
        return new R(SUCCESS, true);
    }

    public static <T> R<T> ok(String message) {
        return new R(message, true);
    }

    public static <T> R<T> ok(T data) {
        return new R(SUCCESS, data);
    }

    public static <T> R<T> ok(String message, T data) {
        return new R(message, data);
    }

    public static <T> R<T> err() {
        return new R(ERROR);
    }

    public static <T> R<T> err(String message) {
        return new R(message);
    }

    public static <T> R<T> err(String message, T data) {
        return new R(ERROR_CODE, message, data);
    }
}
