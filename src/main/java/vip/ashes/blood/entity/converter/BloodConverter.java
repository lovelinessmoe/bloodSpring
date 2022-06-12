package vip.ashes.blood.entity.converter;

import org.mapstruct.Mapper;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.vo.BloodVO;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface BloodConverter {

    /**
     * VO转实体 删去了total
     *
     * @param bloodVOs VO
     * @return 实体
     */
    List<Blood> bloodVOToblood(List<BloodVO> bloodVOs);
}
