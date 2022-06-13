package vip.ashes.blood.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.vo.BloodVO;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper
public interface BloodMapper extends BaseMapper<Blood> {
    /**
     * 获取需要血液的列表
     *
     * @param needVolume        需要的容量
     * @param bloodQueryWrapper 筛选条件 血型ABO RH
     * @return
     */
    List<BloodVO> checkListForTrans(@Param("needVolume") Integer needVolume, @Param(Constants.WRAPPER) QueryWrapper<Blood> bloodQueryWrapper);
}
