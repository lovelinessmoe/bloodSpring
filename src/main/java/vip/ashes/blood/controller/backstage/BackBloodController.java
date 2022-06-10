package vip.ashes.blood.controller.backstage;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.Blood;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.utils.Result;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/backstage/blood")
public class BackBloodController {
    private BloodService bloodService;

    //添加血液
    @PutMapping("insertBlood")
    public Result insertBlood(@RequestBody Blood blood){
        boolean save = bloodService.save(blood);
        return Result.ok().success(save);
    }

    //全部血液列表
    @PostMapping("/getBloodList")
    public Result getBloodList(){
        List<Blood> list = bloodService.list();
        return Result.ok().data(list);
    }
}
