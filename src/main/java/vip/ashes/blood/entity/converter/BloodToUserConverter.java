package vip.ashes.blood.entity.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.User;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface BloodToUserConverter {

    /**
     * 将血液中的信息附加到用户上
     *
     * @param blood 血液
     * @return 用户
     */
    @Mapping(target = "userId", source = "inPerson")
    User bloodToUserInfo(Blood blood);
}
