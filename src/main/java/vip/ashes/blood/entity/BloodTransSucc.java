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
 * 配型成功的血液对应
 * @author loveliness
 */
@ApiModel(value = "vip-ashes-blood-entity-BloodTransSucc")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blood.blood_trans_succ")
public class BloodTransSucc implements Serializable {
    public static final String COL_BLOOD_TRANS_SUCC_ID = "blood_trans_succ_id";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_TRANS_BLOODS_ID = "trans_bloods_id";
    public static final String COL_NEED_VOLUME = "need_volume";
    private static final long serialVersionUID = 1L;
    /**
     * 配型
     */
    @TableId(value = "blood_trans_succ_id", type = IdType.INPUT)
    @ApiModelProperty(value = "配型")
    private String bloodTransSuccId;
    /**
     * 患者ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "患者ID")
    private String userId;
    /**
     * 血液组ID
     */
    @TableField(value = "trans_bloods_id")
    @ApiModelProperty(value = "血液组ID")
    private String transBloodsId;
    /**
     * 预订量cc
     */
    @TableField(value = "need_volume")
    @ApiModelProperty(value = "预订量cc")
    private Integer needVolume;
}
