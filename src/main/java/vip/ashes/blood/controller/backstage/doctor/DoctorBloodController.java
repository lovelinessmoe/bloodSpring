package vip.ashes.blood.controller.backstage.doctor;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.BloodTransForm;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.service.BloodTransFormService;
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
    private BloodTransFormService bloodTransFormService;

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
    @PostMapping("applyList")
    public Result applyList() {

        String applyUserId = currentUserUtils.getCurrentUser().getUserId();
        QueryWrapper<BloodTransForm> wrapper = new QueryWrapper<>();
        wrapper.eq(BloodTransForm.COL_APPLY_USER, applyUserId);
        List<BloodTransForm> list = bloodTransFormService.list(wrapper);
        return Result.ok().data(list);
    }

}
