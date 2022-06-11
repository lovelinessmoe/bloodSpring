package vip.ashes.blood.controller.backstage.doctor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.blood.entity.BloodTransForm;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.utils.Result;

/**
 * @author jh
 */
@RestController
@AllArgsConstructor
@Api(tags = "申请用血表管理")
@PreAuthorize("hasRole('ROLE_DOCTOR')")
@RequestMapping("/backstage/doctor/trans")
public class BloodTransController {
    private BloodService bloodService;
    /**
     * 医生申请用血（添加一个用血申请表）
     */
//    @PostMapping("/useBlood")
//    @ApiOperation(value = "医生申请用血", notes = "上传血液申请单")
//    public Result useBlood(@RequestBody BloodTransForm bloodTransForm) {
//        boolean save = bloodService.save();
//        if (save) {
//            return Result.ok().message("申请表提交成功");
//        } else {
//            return Result.error().message("申请失败，请联系管理员");
//        }
//    }
}
