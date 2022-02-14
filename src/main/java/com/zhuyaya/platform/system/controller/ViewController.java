package com.zhuyaya.platform.system.controller;

import com.zhuyaya.platform.common.authentication.ShiroHelper;
import com.zhuyaya.platform.common.controller.BaseController;
import com.zhuyaya.platform.common.utils.PlatformUtil;
import com.zhuyaya.platform.system.service.IUserService;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统 视图控制类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-09 10:59
 */
@Controller("systemView")
public class ViewController extends BaseController {

    @Resource
    private IUserService userService;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (PlatformUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(PlatformUtil.view("login"));
            return modelAndView;
        }
    }

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }
}