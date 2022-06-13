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
 * 血液信息
 *
 * @author loveliness
 */
@ApiModel(value = "vip-ashes-blood-entity-Blood")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blood.blood")
public class Blood implements Serializable {
    public static final String COL_BLOOD_ID = "blood_id";
    public static final String COL_IN_TIME = "in_time";
    public static final String COL_IN_SOURCE = "in_source";
    public static final String COL_IN_PERSON = "in_person";
    public static final String COL_BLOOD_TYPE = "blood_type";
    public static final String COL_TAKE_TIME = "take_time";
    public static final String COL_EXPIRE_TIME = "expire_time";
    public static final String COL_BLOOD_GROUP = "blood_group";
    public static final String COL_RH = "rh";
    public static final String COL_TAKE_PERSON = "take_person";
    public static final String COL_BLOOD_VOLUME = "blood_volume";
    public static final String COL_STATE = "state";
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "blood_id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String bloodId;
    /**
     * 入库时间
     */
    @TableField(value = "in_time")
    @ApiModelProperty(value = "入库时间")
    private Date inTime;
    /**
     * 血液来源
     */
    @TableField(value = "in_source")
    @ApiModelProperty(value = "血液来源")
    private String inSource;
    /**
     * 献血者id
     */
    @TableField(value = "in_person")
    @ApiModelProperty(value = "献血者id")
    private String inPerson;
    /**
     * 血液种类BLOOD_TYPE (0普通冰冻血浆、1悬浮红细胞、2机采血小板)
     */
    @TableField(value = "blood_type")
    @ApiModelProperty(value = "血液种类BLOOD_TYPE (0普通冰冻血浆、1悬浮红细胞、2机采血小板)")
    private Integer bloodType;
    /**
     * 采血日期
     */
    @TableField(value = "take_time")
    @ApiModelProperty(value = "采血日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date takeTime;
    /**
     * 过期日期
     */
    @TableField(value = "expire_time")
    @ApiModelProperty(value = "过期日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;
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
    /**
     * 采血人id
     */
    @TableField(value = "take_person")
    @ApiModelProperty(value = "采血人id")
    private String takePerson;
    /**
     * 血量 cc
     */
    @TableField(value = "blood_volume")
    @ApiModelProperty(value = "血量 cc")
    private Integer bloodVolume;
    /**
     * 血液状态 BLOOD_STATE(0入库未使用 1已使用 2过期弃用)
     */
    @TableField(value = "state")
    @ApiModelProperty(value = "血液状态 BLOOD_STATE(0入库未使用 1已使用 2过期弃用)")
    private Integer state;
}
