package vip.ashes.blood.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * 公共返回结果
 *
 * @author NieChangan
 */
@Data
public class Result {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data = new HashMap<String, Object>();

    /**
     * 构造方法私有化,里面的方法都是静态方法
     * 达到保护属性的作用
     */
    private Result() {

    }

    /**
     * 这里是使用链式编程
     */
    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return result;
    }

    public static Result RCode(boolean success, ResultCode resultCode) {
        Result result = new Result();
        result.setSuccess(success);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }


    /**
     * 自定义返回成功与否
     *
     * @param success 是否成功
     * @return 自己
     */
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(Object value) {
        this.setData(value);
        return this;
    }

    public Result data(String key, Object value) {
        if (this.data instanceof HashMap) {
            ((HashMap) this.data).put(key, value);
        } else {
            HashMap<String, Object> data = new HashMap<>(5);
            data.put(key, value);
            this.data = data;
        }
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


}
