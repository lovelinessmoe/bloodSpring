package vip.ashes.blood.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.service.UserService;

/**
 * @author lovleiness
 * @description 获取当前请求的用户
 */
@Component
@AllArgsConstructor
public class CurrentUserUtils {

    private UserService userService;

    public User getCurrentUser() {
        User user = new User();
        user.setEmail(getCurrentEmail());
        return userService.login(user);
    }

    private String getCurrentEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
