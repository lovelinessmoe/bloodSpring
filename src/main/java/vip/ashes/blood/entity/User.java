package vip.ashes.blood.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户大表,管理员和医生都是这张
 *
 * @author loveliness
 */
@ApiModel(value = "vip-ashes-blood-entity-User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blood.`user`")
public class User implements Serializable {
    public static final String COL_USER_ID = "user_id";
    public static final String COL_ROLE_ID = "role_id";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_REAL_NAME = "real_name";
    public static final String COL_SEX = "sex";
    public static final String COL_AGE = "age";
    public static final String COL_BLOOD_GROUP = "blood_group";
    public static final String COL_RH = "rh";
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String userId;
    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    /**
     * 昵称
     */
    @TableField(value = "user_name", condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "昵称")
    private String userName;
    /**
     * 登录密码，加密后保存
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "登录密码，加密后保存")
    private String password;
    /**
     * 邮箱
     */
    @TableField(value = "email", condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 姓名
     */
    @TableField(value = "real_name", condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "姓名")
    private String realName;
    /**
     * 性别USER_SEX 0女 1男
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别USER_SEX 0女 1男")
    private Byte sex;
    /**
     * 年龄
     */
    @TableField(value = "age")
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
     * 血型BLOOD_GROUP(abo)
     */
    @TableField(value = "blood_group")
    @ApiModelProperty(value = "血型BLOOD_GROUP(abo)")
    private Integer bloodGroup;
    /**
     * RH0阴性 1阳性
     */
    @TableField(value = "rh")
    @ApiModelProperty(value = "RH0阴性 1阳性")
    private Integer rh;
}
