package vip.ashes.blood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.vo.DictVO;
import vip.ashes.blood.service.DictService;
import vip.ashes.blood.utils.Result;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@Api(tags = "字典")
@RequestMapping("/dict")
@AllArgsConstructor
public class DictController {

    private final DictService dictService;

    /**
     * 通过code获取字典
     *
     * @return 结构
     * dicData: [
     * {
     * label: "阴性",
     * value: 0
     * }, {
     * label: "阳性",
     * value: 1
     * },
     * ]
     */
    @GetMapping("/getDictByCode")
    @ApiOperation("通过code获取字典")
    public Result getDictByCode(@ApiParam("code") String code) {
        List<DictVO> dictByCode = dictService.getDictByCode(code);
        return Result.ok().data(dictByCode);
    }
}
