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
 * 字典表
 * @author loveliness
 */
@ApiModel(value = "vip-ashes-blood-entity-Dict")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blood.dict")
public class Dict implements Serializable {
    public static final String COL_CODE = "code";
    public static final String COL_DICT_KEY = "dict_key";
    public static final String COL_DICT_VALUE = "dict_value";
    public static final String COL_REMARK = "remark";
    private static final long serialVersionUID = 1L;
    /**
     * 字典码
     */
//    @TableId(value = "code", type = IdType.INPUT)
    @ApiModelProperty(value = "字典码")
    private String code;
    /**
     * 字典值
     */
//    @TableId(value = "dict_key", type = IdType.INPUT)
    @ApiModelProperty(value = "字典值")
    private Integer dictKey;
    /**
     * 字典名称
     */
    @TableField(value = "dict_value")
    @ApiModelProperty(value = "字典名称")
    private String dictValue;
    /**
     * 字典备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "字典备注")
    private String remark;
}
