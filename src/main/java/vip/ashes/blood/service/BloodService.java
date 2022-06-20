package vip.ashes.blood.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.vo.RankVO;

import java.util.List;

/**
 * @author loveliness
 */
public interface BloodService extends IService<Blood> {

    /**
     * 获取需要血液的列表,不符合返回空List
     *
     * @param needVolume        需要的容量
     * @param bloodQueryWrapper 筛选条件 血型ABO RH
     * @return 获取需要血液的列表,不符合返回空List
     */
    List<Blood> checkListForTrans(Integer needVolume, QueryWrapper<Blood> bloodQueryWrapper);

    List<RankVO> getRankList();
}
