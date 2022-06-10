package vip.ashes.blood.entity.converter;


import org.mapstruct.Mapper;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.entity.vo.UserVO;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * 类型转换
     *
     * @param user user对象
     * @return userVO对象
     */
    UserVO userToVO(User user);

}
