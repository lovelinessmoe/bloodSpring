package vip.ashes.blood.utils;

/**
 * @Author: NieChangan
 * @Description: 返回码定义
 * 规定:
 * #200表示成功
 * #1001～1999 区间表示文章错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 * #后面对什么的操作自己在这里注明就行了
 */
public enum ResultCode {
    /* 成功 */
    SUCCESS(200, null),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),


    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "用户名或密码不正确"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_USERNAME_ALREADY_EXIST(2008, "账号已存在,请更换用户名"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    USER_CAPTCHA_CODE_ERR(2010, "验证码错误"),
    EMAIL_FORMAT_ERROR(2011, "邮箱格式错误"),
    USERNAME_ERROR(2012, "用户名错误"),
    USER_ACCOUNT_EMAIL_ALREADY_EXIST(2013, "账号已存在,请更换邮箱"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限"),


    /*运行时异常*/
    ARITHMETIC_EXCEPTION(9001, "算数异常");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
