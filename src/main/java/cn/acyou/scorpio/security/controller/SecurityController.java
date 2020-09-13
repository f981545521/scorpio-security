package cn.acyou.scorpio.security.controller;

import cn.acyou.scorpio.security.model.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youfang
 * @version [1.0.0, 2020/6/29]
 **/
@RestController
@RequestMapping("demo")
public class SecurityController {

    @RequestMapping(value = "/list")
    @PreAuthorize("hasAuthority('demo:list')")
    public Result<?> list() {
        System.out.println("拥有demo:list 权限才能访问成功！");
        return Result.success("ok");
    }

    @RequestMapping(value = "/rolePermission")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<?> roleTest() {
        System.out.println("拥有Admin角色才能访问成功！");
        return Result.success("ok");
    }

}
