package vip.ashes.blood.exception;

import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import vip.ashes.blood.utils.Result;
import vip.ashes.blood.utils.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lovleiness
 * @description AccessDeineHandler 用来解决认证过的用户访问无权限资源时的异常
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 当用户尝试访问需要权限才能的REST资源而权限不足的时候，
     * 将调用此方法发送403响应以及错误信息
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String s = JSONUtil.toJsonStr(Result.RCode(false, ResultCode.NO_PERMISSION));
        response.getWriter().println(s);
    }
}
