package vip.ashes.blood.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.blood.dao.BloodMapper;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.converter.BloodConverter;
import vip.ashes.blood.entity.vo.BloodVO;
import vip.ashes.blood.entity.vo.RankVO;
import vip.ashes.blood.service.BloodService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class BloodServiceImpl extends ServiceImpl<BloodMapper, Blood> implements BloodService {

    private final BloodMapper bloodMapper;
    private final BloodConverter bloodConverter;

    @Override
    public List<Blood> checkListForTrans(Integer needVolume, QueryWrapper<Blood> bloodQueryWrapper) {
        List<BloodVO> bloodVOS = bloodMapper.checkListForTrans(needVolume, bloodQueryWrapper);
        //最后一个的总和加起来不够
        if (bloodVOS.size() == 0 || (bloodVOS.get(bloodVOS.size() - 1).getTotalValue() < needVolume)) {
            return new ArrayList<>();
        } else {
            return bloodConverter.bloodVOToblood(bloodVOS);
        }
    }


    @Override
    public List<RankVO> getRankList() {
        return bloodMapper.getRankList();
    }
}
