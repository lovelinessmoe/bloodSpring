package vip.ashes.blood.controller.backstage.doctor;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.BloodTransForm;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.entity.converter.BloodToUserConverter;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.service.BloodTransFormService;
import vip.ashes.blood.service.UserService;
import vip.ashes.blood.utils.CurrentUserUtils;
import vip.ashes.blood.utils.Result;

import java.util.Date;
import java.util.List;

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
    private final BloodTransFormService bloodTransFormService;
    private final UserService userService;
    private final BloodToUserConverter bloodToUserConverter;

    /**
     * 医生抽血
     *
     * @param blood 添加的血液信息
     * @return Result状态消息
     */
    @PutMapping("/takeBlood")
    public Result insertBlood(@RequestBody Blood blood) {
        //转换实体,将抽血信息添加到用户信息
        User user = bloodToUserConverter.bloodToUserInfo(blood);
        userService.updateById(user);

        //添加抽血人id
        blood.setTakePerson(currentUserUtils.getCurrentUser().getUserId());
        //添加抽血时间
        blood.setInTime(new Date());
        blood.setTakeTime(new Date());
        //过期时间
        final int ordinaryFrozenPlasma = 0;
        final int suspendedRedBloodCells = 1;
        final int machineCollectedPlatelets = 2;
        switch (blood.getBloodType()) {
            case ordinaryFrozenPlasma:
                blood.setExpireTime(DateUtil.offsetWeek(new Date(), 3));
                break;
            case suspendedRedBloodCells:
                blood.setExpireTime(DateUtil.offsetDay(new Date(), 35));
                break;
            case machineCollectedPlatelets:
                blood.setExpireTime(DateUtil.offsetDay(new Date(), 5));
                break;
            default:
        }

        boolean save = bloodService.save(blood);
        if (save) {
            return Result.ok().message("入库成功");
        } else {
            return Result.error().message("入库失败，请联系管理员");
        }
    }


    /**
     * 医生查看自己的申请记录
     *
     * @return
     */
    @PostMapping("/applyList")
    public Result applyList() {

        String applyUserId = currentUserUtils.getCurrentUser().getUserId();
        QueryWrapper<BloodTransForm> wrapper = new QueryWrapper<>();
        wrapper.eq(BloodTransForm.COL_APPLY_USER, applyUserId);
        List<BloodTransForm> list = bloodTransFormService.list(wrapper);
        return Result.ok().data(list);
    }


    /**
     * @param user  筛选用户
     * @param query 分页
     * @return Result
     */
    @GetMapping("/userList")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入用户")
    public Result list(User user, PageDTO<User> query) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        //用户的角色id
        final int userRoleNum = 2;
        userQueryWrapper.eq(User.COL_ROLE_ID, userRoleNum)
                .select(User.COL_USER_ID, User.COL_AGE, User.COL_BLOOD_GROUP, User.COL_REAL_NAME,
                        User.COL_RH, User.COL_SEX, User.COL_REAL_NAME, User.COL_USER_NAME);
        PageDTO<User> pages = userService.page(query, userQueryWrapper);
        return Result.ok().data(pages);
    }
}
