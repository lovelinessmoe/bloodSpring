package vip.ashes.blood.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.blood.dao.BloodMapper;
import vip.ashes.blood.entity.Blood;
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

    @Override
    public List<Blood> checkListForTrans(Integer needVolume, QueryWrapper<Blood> bloodQueryWrapper) {

        bloodMapper.checkListForTrans(needVolume, bloodQueryWrapper);

        return new ArrayList<Blood>();
    }
}
