package vip.ashes.blood.controller.backstage.doctor;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.entity.converter.BloodToUserConverter;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.service.UserService;
import vip.ashes.blood.utils.CurrentUserUtils;
import vip.ashes.blood.utils.Result;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@Api(tags = "医生血液用户管理")
@PreAuthorize("hasRole('ROLE_DOCTOR')")
@RequestMapping("/backstage/doctor/blood")
public class DoctorBloodController {
    private final BloodService bloodService;
    private final CurrentUserUtils currentUserUtils;
    private final UserService userService;
    private final BloodToUserConverter bloodToUserConverter;

    /**
     * 医生抽血
     *
     * @param blood 添加的血液信息
     * @return Result状态消息
     */
    @PutMapping("takeBlood")
    public Result insertBlood(@RequestBody Blood blood) {
        //添加抽血人id
        blood.setTakePerson(currentUserUtils.getCurrentUser().getUserId());
        //转换实体
        User user = bloodToUserConverter.bloodToUserInfo(blood);
        userService.updateById(user);

        boolean save = bloodService.save(blood);
        if (save) {
            return Result.ok().message("入库成功");
        } else {
            return Result.error().message("入库失败，请联系管理员");
        }
    }
}
