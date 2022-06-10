package vip.ashes.blood.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.ashes.blood.dao.RoleMapper;
import vip.ashes.blood.entity.Role;
import vip.ashes.blood.service.RoleService;

/**
 * @author loveliness
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

