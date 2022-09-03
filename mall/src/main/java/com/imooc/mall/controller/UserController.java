package com.imooc.mall.controller;

import com.imooc.mall.form.UserLoginForm;
import com.imooc.mall.form.UserRegisterForm;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IUserService;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.imooc.mall.consts.MallConst.CURRENT_USER;

@RestController
@RequestMapping("")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user/register")
    public ResponseVo register(@Valid @RequestBody UserRegisterForm userRegisterForm) {

        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);
        userService.register(user);
        return ResponseVo.success();
    }

    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm, HttpSession session) {

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());

        //设置Session
        session.setAttribute(CURRENT_USER, userResponseVo.getData());
        log.info("/login sessionId={}", session.getId());
        return userResponseVo;
    }

    //session保存在内存里，改进版：token+redis
    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session) {
        log.info("/user sessionId={}", session.getId());
        User user = (User) session.getAttribute(CURRENT_USER);
        return ResponseVo.success(user);
    }

    @PostMapping("/user/logout")
    public ResponseVo logout(HttpSession session) {
        log.info("/login sessionId={}", session.getId());
        return ResponseVo.success();
    }

}
