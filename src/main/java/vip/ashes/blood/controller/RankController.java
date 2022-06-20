package vip.ashes.blood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.vo.RankVO;
import vip.ashes.blood.service.BloodService;
import vip.ashes.blood.utils.Result;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/rank")
@Api(tags = "获取排行榜")
@AllArgsConstructor
public class RankController {
    private final BloodService bloodService;

    @GetMapping("/getRankList")
    @ApiOperation("获取排行榜")
    public Result getRankList() {
        List<RankVO> list = bloodService.getRankList();
        return Result.ok().data(list);
    }

}
