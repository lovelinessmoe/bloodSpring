package vip.ashes.blood.controller.backstage.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.service.UserService;
import vip.ashes.blood.utils.Result;
import vip.ashes.blood.utils.ResultCode;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@Api(tags = "后台用户管理")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/backstage/user")
public class BackUserController {

    private final UserService userService;


    /**
     * 新增或修改用户
     */
    @PostMapping("/submit")
    @ApiOperation(value = "新增或修改", notes = "传入用户")
    public Result submit(@Valid @RequestBody User user) {
        return Result.RCode(userService.saveOrUpdate(user), ResultCode.SUCCESS);
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "传入用户")
    public Result update(@Valid @RequestBody User user) {
        return Result.RCode(userService.updateById(user), ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @ApiOperation(value = "删除用户", notes = "传入id")
    public Result remove(@ApiParam(value = "主键", required = true) @RequestParam String id) {
        return Result.RCode(userService.removeById(id), ResultCode.SUCCESS);
    }

    /**
     * 删除多个用户
     */
    @PostMapping("/removeMany")
    @ApiOperation(value = "删除多个用户", notes = "传入用户集合")
    public Result removeMany(@ApiParam(value = "用户集合", required = true)
                             @RequestBody ArrayList<User> userList) {
        return Result.RCode(userService.removeByIds(userList), ResultCode.SUCCESS);
    }

    /**
     * @param user  筛选用户
     * @param query 分页
     * @return Result
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入用户")
    public Result list(User user, PageDTO<User> query) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        //管理员的角色id
        final int adminRoleNum = 1;
        userQueryWrapper.ne(User.COL_ROLE_ID, adminRoleNum);
        PageDTO<User> pages = userService.page(query, userQueryWrapper);
        return Result.ok().data(pages);
    }
}
