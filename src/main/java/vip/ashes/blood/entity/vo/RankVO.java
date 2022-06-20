package vip.ashes.blood.entity.vo;

import lombok.Data;

/**
 * @author loveliness
 */
@Data
public class RankVO {
    /**
     * 昵称
     */
    private String userName;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 总血量
     */
    private String volumeSum;
}
