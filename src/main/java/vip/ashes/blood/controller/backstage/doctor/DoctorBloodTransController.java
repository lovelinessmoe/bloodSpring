package vip.ashes.blood.controller.backstage.doctor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.blood.dao.TransBloodsMapper;
import vip.ashes.blood.entity.BloodTransForm;
import vip.ashes.blood.entity.TransBloods;
import vip.ashes.blood.service.BloodTransFormService;
import vip.ashes.blood.utils.CurrentUserUtils;
import vip.ashes.blood.utils.Result;

import java.util.Date;
import java.util.List;

/**
 * @author jh
 */
@RestController
@AllArgsConstructor
@Api(tags = "申请用血表管理")
@PreAuthorize("hasRole('ROLE_DOCTOR')")
@RequestMapping("/backstage/doctor/trans")
public class DoctorBloodTransController {
    private final CurrentUserUtils currentUserUtils;
    private BloodTransFormService bloodTransFormService;
    @Autowired
    TransBloodsMapper transBloodsMapper;

    /**
     * 医生申请用血（添加一个用血申请表）
     *
     * @param bloodTransForm 医生申请单 needVolume needBloodType needPerson
     * @return Res
     */
    @PostMapping("/useBlood")
    @ApiOperation(value = "医生申请用血", notes = "上传血液申请单")
    public Result useBlood(@RequestBody BloodTransForm bloodTransForm) {
        String applyUserId = currentUserUtils.getCurrentUser().getUserId();
        bloodTransForm.setApplyUser(applyUserId);
        bloodTransForm.setApplyTime(new Date());
        boolean save = bloodTransFormService.save(bloodTransForm);
        if (save) {
            return Result.ok().message("申请表提交成功");
        } else {
            return Result.error().message("申请失败，请联系管理员");
        }
    }

    /**
     * 查看申请单对应的血液
     *
     * @return
     */
    @PostMapping("/lookByblood")
    @ApiOperation(value = "查看申请单对应的血液", notes = "无")
    public Result lookByblood() {

        List<TransBloods> list = transBloodsMapper.lookbyblood();
        return Result.ok().data(list);
    }
}
