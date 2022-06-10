package vip.ashes.blood.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.ashes.blood.dao.BloodMapper;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.service.BloodService;

/**
 * @author loveliness
 */
@Service
public class BloodServiceImpl extends ServiceImpl<BloodMapper, Blood> implements BloodService {

}
