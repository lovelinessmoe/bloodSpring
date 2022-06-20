package vip.ashes.blood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.BloodTransForm;

import java.util.List;

/**
 * @author loveliness
 */
public interface BloodTransFormService extends IService<BloodTransForm> {

    /**
     * 获取申请单对应的血液信息
     *
     * @param bloodTransId 申请单id
     * @return 血液
     */
    List<Blood> lookByBlood(String bloodTransId);
}

