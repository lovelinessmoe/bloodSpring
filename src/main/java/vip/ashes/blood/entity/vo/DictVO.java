package vip.ashes.blood.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author loveliness
 */
@Data
public class DictVO {
    /**
     * 字典值
     */
    @ApiModelProperty(value = "字典值")
    private Integer value;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String label;
}
