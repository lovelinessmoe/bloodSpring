package vip.ashes.blood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色
 * @author loveliness
 */
@ApiModel(value = "vip-ashes-blood-entity-Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blood.`role`")
public class Role implements Serializable {
    public static final String COL_ROLE_ID = "role_id";
    public static final String COL_ROLE_NAME = "role_name";
    public static final String COL_ROLE_NAME_EN = "role_name_en";
    private static final long serialVersionUID = 1L;
    /**
     * 用户角色
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户角色")
    private Integer roleId;
    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色名称EN
     */
    @TableField(value = "role_name_en")
    @ApiModelProperty(value = "角色名称EN")
    private String roleNameEn;
}
