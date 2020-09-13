package cn.acyou.scorpio.security.controller;

import cn.acyou.scorpio.security.model.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author youfang
 * @version [1.0.0, 2020/6/30]
 **/
@RestController
@RequestMapping("sys")
public class SystemController {
    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Result<?> login(HttpServletRequest request, String username, String password) {
        System.out.println("用户执行登录：username:" + username);
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Object principal = authentication.getPrincipal();
        System.out.println(principal);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //登录后
        Authentication afterAuth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(afterAuth);

        HttpSession session = request.getSession();
        //重新设置会话过期时间
        session.setMaxInactiveInterval(60);
        return Result.success();
    }

    @PostMapping("/code")
    public Result<?> captchaImage() {
        System.out.println("code" );
        //登录后
        Authentication afterAuth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(afterAuth);
        return Result.success("yes code ok");
    }
    @PostMapping("/sessionInvalid")
    public Result<?> sessionInvalid() {
        System.out.println("sessionInvalid" );
        return Result.success("会话过期，请刷新页面！");
    }
}
