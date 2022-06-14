package vip.ashes.blood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private final StringRedisTemplate stringRedisTemplate;

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
        List<DictVO> dictByCode = null;
        String dictByRedis = null;
        try {
            dictByRedis = stringRedisTemplate.opsForValue().get("DICT_" + code);
            ObjectMapper objectMapper = new ObjectMapper();
            if (dictByRedis == null) {
                dictByCode = dictService.getDictByCode(code);
                stringRedisTemplate.opsForValue().set("DICT_" + code, objectMapper.writeValueAsString(dictByCode));
            } else {
                dictByCode = objectMapper.readValue(dictByRedis, new TypeReference<List<DictVO>>() {
                });
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Result.ok().data(dictByCode);
    }
}
