package vip.ashes.blood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 输血申请单
 *
 * @author loveliness
 */
@ApiModel(value = "vip-ashes-blood-entity-BloodTransForm")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blood.blood_trans_form")
public class BloodTransForm implements Serializable {
    public static final String COL_FORM_ID = "form_id";
    public static final String COL_NEED_PERSON = "need_person";
    public static final String COL_NEED_VOLUME = "need_volume";
    public static final String COL_NEED_BLOOD_TYPE = "need_blood_type";
    public static final String COL_APPLY_USER = "apply_user";
    public static final String COL_APPLY_TIME = "apply_time";
    public static final String COL_STATE = "state";
    public static final String COL_TRANS_BLOODS_ID = "trans_bloods_id";
    private static final long serialVersionUID = 1L;
    /**
     * 输血申请单id
     */
    @TableId(value = "form_id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "输血申请单id")
    private String formId;
    /**
     * 患者id
     */
    @TableField(value = "need_person")
    @ApiModelProperty(value = "患者id")
    private String needPerson;
    /**
     * 预订量cc
     */
    @TableField(value = "need_volume")
    @ApiModelProperty(value = "预订量cc")
    private Integer needVolume;
    /**
     * 血液种类BLOOD_TYPE (0普通冰冻血浆、1悬浮红细胞、2机采血小板)
     */
    @TableField(value = "need_blood_type")
    @ApiModelProperty(value = "血液种类BLOOD_TYPE (0普通冰冻血浆、1悬浮红细胞、2机采血小板)")
    private Integer needBloodType;
    /**
     * 申请医师id
     */
    @TableField(value = "apply_user")
    @ApiModelProperty(value = "申请医师id")
    private String applyUser;
    /**
     * 申请时间
     */
    @TableField(value = "apply_time")
    @ApiModelProperty(value = "申请时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    /**
     * 申请单状态BLOOD_TRANS_FORM_STATE(0申请未处理 1通过 2拒绝)
     */
    @TableField(value = "state")
    @ApiModelProperty(value = "申请单状态BLOOD_TRANS_FORM_STATE(0申请未处理 1通过 2拒绝)")
    private Integer state;
    /**
     * 输血申请成功的配型id
     */
    @TableField(value = "trans_bloods_id")
    @ApiModelProperty(value = "输血申请成功的配型id")
    private String transBloodsId;
}
