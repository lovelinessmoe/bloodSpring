package vip.ashes.blood.controller.backstage.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.service.BloodService;
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
    private BloodService bloodService;



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



}
