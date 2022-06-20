package vip.ashes.blood.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.blood.dao.BloodTransFormMapper;
import vip.ashes.blood.dao.TransBloodsMapper;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.BloodTransForm;
import vip.ashes.blood.service.BloodTransFormService;

import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class BloodTransFormServiceImpl extends ServiceImpl<BloodTransFormMapper, BloodTransForm> implements BloodTransFormService {
    private final TransBloodsMapper transBloodsMapper;


    @Override
    public List<Blood> lookByBlood(String bloodTransId) {
        return transBloodsMapper.lookByBlood(bloodTransId);
    }
}

