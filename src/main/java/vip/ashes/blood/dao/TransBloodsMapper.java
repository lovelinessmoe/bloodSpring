package vip.ashes.blood.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.TransBloods;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper
public interface TransBloodsMapper extends BaseMapper<TransBloods> {
    /**
     * 获取申请单对应的血液信息
     * @param bloodTransId 申请单id
     * @return 血液
     */
    List<Blood> lookByBlood(String bloodTransId);
}
