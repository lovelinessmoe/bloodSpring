package vip.ashes.blood.controller.backstage.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.utils.Result;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/backstage/blood")
public class BackBloodController {
    private BloodService bloodService;

    /**
     * 添加一个血液
     */
    @PutMapping("insertBlood")
    @ApiOperation(value = "添加血液", notes = "建议协商")
    public Result insertBlood(@RequestBody Blood blood) {
        boolean save = bloodService.save(blood);
        if (save) {
            return Result.ok().message("添加成功");
        } else {
            return Result.error().message("添加失败");
        }
    }

    /**
     * 全部血液查看
     */
    @PostMapping("/getBloodList")
    @ApiOperation(value = "全部血液查看", notes = "建议协商")
    public Result getBloodList() {
        List<Blood> list = bloodService.list();
        return Result.ok().data(list);
    }

    /**
     * 修改血液信息
     */
    @PostMapping("/updateBlood")
    @ApiOperation(value = "修改血液信息", notes = "建议协商")
    public Result updateBlood(@RequestBody Blood blood) {
        boolean save = bloodService.updateById(blood);
        if (save) {
            return Result.ok().message("成功");
        } else {
            return Result.error().message("失败");
        }
    }

}
