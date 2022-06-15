package vip.ashes.blood.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.ashes.blood.dao.TransBloodsMapper;
import vip.ashes.blood.entity.TransBloods;
import vip.ashes.blood.service.TransBloodsService;

import java.util.List;

/**
 * @author loveliness
 */
@Service
public class TransBloodsServiceImpl extends ServiceImpl<TransBloodsMapper, TransBloods> implements TransBloodsService {

}
