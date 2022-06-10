package vip.ashes.blood.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 配型的血液组
 * @author loveliness
 */
@ApiModel(value = "vip-ashes-blood-entity-TransBloods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blood.trans_bloods")
public class TransBloods implements Serializable {
    public static final String COL_TRANS_BLOODS_ID = "trans_bloods_id";
    public static final String COL_BLOOD_ID = "blood_id";
    private static final long serialVersionUID = 1L;
    /**
     * 血液组ID
     */
//    @TableId(value = "trans_bloods_id", type = IdType.INPUT)
    @ApiModelProperty(value = "血液组ID")
    private String transBloodsId;
    /**
     * 血液ID
     */
//    @TableId(value = "blood_id", type = IdType.INPUT)
    @ApiModelProperty(value = "血液ID")
    private String bloodId;
}
