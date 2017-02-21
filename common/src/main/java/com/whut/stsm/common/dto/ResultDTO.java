package com.whut.stsm.common.dto;

/**
 * 返回给前台数据
 *
 * Created by null on 2016/12/31.
 */
public class ResultDTO<T> {

    private static final int SUCCESS_CODE = 200;

    private Integer code;
    private String message;
    private T result;

    public ResultDTO(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static ResultDTO<?> success(String message) {
        return new ResultDTO<String>(SUCCESS_CODE, message, null);
    }

    public static ResultDTO<?> success(String message, Object result) {
        return new ResultDTO<>(SUCCESS_CODE, message, result);
    }

    public static ResultDTO<?> fail(Integer code, String message) {
        return new ResultDTO<String>(code, message, null);
    }

    public static ResultDTO<?> fail(Integer code, String message, Object result) {
        return new ResultDTO<>(code, message, result);
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
