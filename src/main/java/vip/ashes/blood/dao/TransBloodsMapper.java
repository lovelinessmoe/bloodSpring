package vip.ashes.blood.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.blood.entity.TransBloods;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper
public interface TransBloodsMapper extends BaseMapper<TransBloods> {
    List<TransBloods> lookbyblood();
}
