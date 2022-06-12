package vip.ashes.blood.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author loveliness
 */
@Data
public class BloodVO {

    /**
     * 主键
     */
    private String bloodId;
    /**
     * 入库时间
     */
    private Date inTime;
    /**
     * 血液来源
     */
    private String inSource;
    /**
     * 献血者id
     */
    private String inPerson;
    /**
     * 血液种类BLOOD_TYPE (0普通冰冻血浆、1悬浮红细胞、2机采血小板)
     */
    private Integer bloodType;
    /**
     * 采血日期
     */
    private Date takeTime;
    /**
     * 过期日期
     */
    private Date expireTime;
    /**
     * 血型BLOOD_GROUP(abo)
     */
    private Integer bloodGroup;
    /**
     * RH0阴性 1阳性
     */
    private Integer rh;
    /**
     * 采血人id
     */
    private String takePerson;
    /**
     * 血量 cc
     */
    private Integer bloodVolume;
    /**
     * 血液状态 BLOOD_STATE(0入库未使用 1已使用 2过期弃用)
     */
    private Integer state;
    /**
     * 血液总数
     */
    private Integer totalValue;
}
