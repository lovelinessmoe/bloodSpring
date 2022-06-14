package vip.ashes.blood.controller.backstage.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.entity.BloodTransForm;
import vip.ashes.blood.entity.TransBloods;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.service.BloodTransFormService;
import vip.ashes.blood.service.TransBloodsService;
import vip.ashes.blood.service.UserService;
import vip.ashes.blood.utils.Result;

import java.util.ArrayList;
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
    private final TransBloodsService transBloodsService;


    /**
     * 全部血液查看并且分页
     *
     * @param query
     * @param blood
     * @return
     */
    @PostMapping("/getBloodList")
    @ApiOperation(value = "全部血液查看", notes = "列出全部血液")
    public Result getBloodList(PageDTO<Blood> query, Blood blood) {

        QueryWrapper<Blood> bloodQueryWrapper = new QueryWrapper<>(blood);
        PageDTO<Blood> pages = bloodService.page(query, bloodQueryWrapper);
        return Result.ok().data(pages);

    }

    /**
     * 修改血液信息
     */
    @PostMapping("/updateBlood")
    @ApiOperation(value = "修改血液信息", notes = "根据bloodId修改血液信息")
    public Result updateBlood(@RequestBody Blood blood) {
        boolean save = bloodService.updateById(blood);
        if (save) {
            return Result.ok().message("修改成功");
        } else {
            return Result.error().message("修改失败");
        }
    }

    /**
     * @param bloodTransFormId 血液申请单id
     * @param accept           是否同意
     * @return Res
     */
    @GetMapping("/approvalBlood")
    @ApiOperation(value = "管理员审批血液", notes = "管理员审批血液")
    public Result updateBlood(@RequestParam String bloodTransFormId, @RequestParam boolean accept) {
        if (accept) {
            //同意用血
            //根据id查找申请单
            BloodTransForm bloodTransForm = bloodTransFormService.getById(bloodTransFormId);
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
                    .eq(Blood.COL_BLOOD_TYPE, bloodTransForm.getNeedBloodType())
                    //血液容量不等于0
                    .ne(Blood.COL_BLOOD_VOLUME, 0)
                    .eq(Blood.COL_STATE, 0);
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
                //更新配型成功ID
                //更新血液信息
                for (Blood blood : bloodList) {
                    blood.setState(1);
                }
                bloodService.updateBatchById(bloodList);
                //构建血液组ID
                String snowID = IdWorker.getIdStr();
                ArrayList<TransBloods> transBloods = new ArrayList<>();
                for (Blood blood : bloodList) {
                    TransBloods transBloods1 = new TransBloods(snowID, blood.getBloodId());
                    transBloods.add(transBloods1);
                }
                //插入血液组ID
                transBloodsService.saveBatch(transBloods);
                //血液申请单成功
                UpdateWrapper<BloodTransForm> updateWrapper = new UpdateWrapper<BloodTransForm>()
                        .eq(BloodTransForm.COL_FORM_ID, bloodTransForm.getFormId())
                        .set(BloodTransForm.COL_TRANS_BLOODS_ID, snowID)
                        .set(BloodTransForm.COL_STATE, 1);
                bloodTransFormService.update(updateWrapper);
                return Result.ok().message("审批同意");
            }
        } else {
            //拒绝用血
            UpdateWrapper<BloodTransForm> updateWrapper = new UpdateWrapper<BloodTransForm>()
                    .eq(BloodTransForm.COL_FORM_ID, bloodTransFormId)
                    .set(BloodTransForm.COL_STATE, 2);
            bloodTransFormService.update(updateWrapper);
            return Result.ok().message("审批完成");
        }
    }

}
