package org.moroboshidan.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 成功的响应结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData(data);
    }


    /**
     * 不带数据的成功结果
     * @return
     */
    public static ResponseResult success() {
        return new ResponseResult(CommonStatusEnum.SUCCESS.getCode(), CommonStatusEnum.SUCCESS.getValue(), null);
    }


    /**
     * 失败 自定义失败结果
     *
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult(code, message, null);
    }

    /**
     * 失败 自定义失败结果，包含具体错误数据
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(int code, String message, T data) {
        return new ResponseResult(code, message, data);
    }


    /**
     * 默认失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setData(CommonStatusEnum.FAIL.getCode())
                .setMessage(CommonStatusEnum.FAIL.getValue())
                .setData(data);
    }


    /**
     * 默认失败
     * @return
     */
    public static  ResponseResult fail() {
        return new ResponseResult(CommonStatusEnum.FAIL.getCode(), CommonStatusEnum.FAIL.getValue(), null);
    }
}
