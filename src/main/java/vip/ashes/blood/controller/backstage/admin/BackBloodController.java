package vip.ashes.blood.controller.backstage.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.BloodTransForm;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.service.BloodTransFormService;
import vip.ashes.blood.service.UserService;
import vip.ashes.blood.utils.Result;

import java.util.List;

/**
 * @author jh
 */
@RestController
@AllArgsConstructor
@Api(tags = "后台血液管理")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/backstage/blood")
public class BackBloodController {
    private final BloodService bloodService;
    private final BloodTransFormService bloodTransFormService;
    private final UserService userService;


    /**
     * 全部血液查看
     */
    @PostMapping("/getBloodList")
    @ApiOperation(value = "全部血液查看", notes = "列出全部血液")
    public Result getBloodList() {
        List<Blood> list = bloodService.list();
        return Result.ok().data(list);
    }

    /**
     * 修改血液信息
     */
    @PostMapping("/updateBlood")
    @ApiOperation(value = "修改血液信息", notes = "根据bloodid修改血液信息")
    public Result updateBlood(@RequestBody Blood blood) {
        boolean save = bloodService.updateById(blood);
        if (save) {
            return Result.ok().message("修改成功");
        } else {
            return Result.error().message("修改失败");
        }
    }

    /**
     * @param bloodTransForm 血液申请单id
     * @param accept         是否同意
     * @return Res
     */
    @PostMapping("/approvalBlood")
    @ApiOperation(value = "管理员审批血液", notes = "管理员审批血液")
    public Result updateBlood(@RequestBody BloodTransForm bloodTransForm, boolean accept) {
        if (accept) {
            //同意用血
            //根据id查找申请单
            bloodTransForm = bloodTransFormService.getById(bloodTransForm.getFormId());
            //查找患者血型
            User patient = userService.getById(bloodTransForm.getNeedPerson());
            if (patient.getRh() == null || patient.getBloodGroup() == null) {
                return Result.error().message("患者未验血");
            }
            //构建warpper
            QueryWrapper<Blood> bloodQueryWrapper = new QueryWrapper<Blood>()
                    //RH
                    .eq(Blood.COL_RH, patient.getRh())
                    //BloodType
                    .eq(Blood.COL_BLOOD_TYPE, bloodTransForm.getNeedBloodType());
            final int BLOOD_A = 0;
            final int BLOOD_B = 1;
            final int BLOOD_AB = 2;
            final int BLOOD_O = 3;
            switch (patient.getBloodGroup()) {
                case BLOOD_AB:
                    bloodQueryWrapper.eq(Blood.COL_BLOOD_GROUP, BLOOD_AB);
                    break;
                case BLOOD_A:
                    bloodQueryWrapper.in(Blood.COL_BLOOD_GROUP, BLOOD_A, BLOOD_O);
                    break;
                case BLOOD_B:
                    bloodQueryWrapper.in(Blood.COL_BLOOD_GROUP, BLOOD_B, BLOOD_O);
                    break;
                case BLOOD_O:
                    bloodQueryWrapper.eq(Blood.COL_BLOOD_GROUP, BLOOD_O);
                    break;
                default:
            }

            //查看血库
            List<Blood> bloodList = bloodService.checkListForTrans(bloodTransForm.getNeedVolume(), bloodQueryWrapper);
            if (bloodList.size() == 0) {
                return Result.error().message("当前血库血液不足");
            } else {
                UpdateWrapper<BloodTransForm> updateWrapper = new UpdateWrapper<BloodTransForm>()
                        .eq(BloodTransForm.COL_FORM_ID, bloodTransForm.getFormId())
                        .set(BloodTransForm.COL_STATE, false);
                bloodTransFormService.update(updateWrapper);
                return Result.ok().message("审批同意");
            }
        } else {
            //拒绝用血
            UpdateWrapper<BloodTransForm> updateWrapper = new UpdateWrapper<BloodTransForm>()
                    .eq(BloodTransForm.COL_FORM_ID, bloodTransForm.getFormId())
                    .set(BloodTransForm.COL_STATE, false);
            bloodTransFormService.update(updateWrapper);
            return Result.ok().message("审批完成");
        }
    }

}
