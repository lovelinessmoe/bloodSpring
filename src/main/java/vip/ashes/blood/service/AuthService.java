package vip.ashes.blood.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import vip.ashes.blood.entity.JwtUser;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.utils.CurrentUserUtils;
import vip.ashes.blood.utils.JwtTokenUtils;
import vip.ashes.blood.utils.Result;
import vip.ashes.blood.utils.ResultCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lovleiness
 **/
@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;
    private final CurrentUserUtils currentUserUtils;


    public Result createToken(User loginRequest) {
        User user = userService.login(loginRequest);

        if (user == null) {
            return Result.RCode(false, ResultCode.USER_CREDENTIALS_ERROR);
        }

        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            return Result.RCode(false, ResultCode.USER_CREDENTIALS_ERROR);

        }
        JwtUser jwtUser = new JwtUser(user);

        if (!jwtUser.isEnabled()) {
            return Result.RCode(false, ResultCode.USER_ACCOUNT_DISABLE);
        }
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        //使用email创建token
        String token = JwtTokenUtils.createToken(user.getEmail(), user.getUserId(), authorities, true);
        stringRedisTemplate.opsForValue().set("LOGIN_" + user.getUserId(), token);
        jwtUser.setToken(token);

        return Result.ok().data(jwtUser);
    }

    public Result removeToken() {
        try {
            String userId = currentUserUtils.getCurrentUser().getUserId();
            stringRedisTemplate.delete(userId);
        } catch (Exception e) {
            return Result.RCode(false, ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        return Result.ok();
    }
}
