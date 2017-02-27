package com.whut.stsm.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回给前台数据
 *
 * Created by null on 2016/2/21.
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> implements Serializable {

    private static final int SUCCESS_CODE = 200;

    @ApiModelProperty(value = "状态码", example="200", required = true, position=-2)
    private Integer code;
    @ApiModelProperty(value = "消息", example="success", position=-1)
    private String message;
    @ApiModelProperty(value = "数据")
    private T result;

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

}
