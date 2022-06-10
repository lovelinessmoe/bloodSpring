package vip.ashes.blood.entity.vo;

import lombok.Data;

/**
 * @author loveliness
 */
@Data
public class UserVO {

    /**
     * 昵称
     */
    private String userName;

    /**
     * 登录密码，加密后保存
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别USER_SEX 0女 1男
     */
    private Byte sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 血型BLOOD_GROUP(abo)
     */
    private Integer bloodGroup;

    /**
     * RH0阴性 1阳性
     */
    private Integer rh;

}
