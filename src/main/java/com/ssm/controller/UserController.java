package com.ssm.controller;

import com.ssm.common.bean.VResponse;
import com.ssm.entity.User;
import com.ssm.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @email: dong980514280@gmail.com
 * @author: Dong
 * @date: 2018/11/18
 * @time: 22:43
 * Ps: UserController
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        Integer test = userService.test();
        return "ok" + test;
    }

    //  返回jsp页面
    @RequestMapping(value = "/getUserJSP", method = RequestMethod.GET)
    public ModelAndView getUserJSP(@RequestParam("id") long id) {
        User user = this.userService.selectUser(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user");
        mv.addObject("user", user);
        return mv;
    }

    //  用ResponseBody返回json格式内容
    @ResponseBody
    @RequestMapping(value = "/getUserResponseBody",method = RequestMethod.GET)
    public User getUserResponseBody(@RequestParam("id") long id) {
        User user = this.userService.selectUser(id);
        return user;
    }

    /**
     * 增加用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public VResponse<Object> addUser(@RequestBody User user) {
        this.userService.addUser(user);
        return VResponse.success("添加成功");
    }

    /**
     *  修改用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public VResponse<Object> updateUser(@RequestBody User user) {
        this.userService.updateUser(user);
        return VResponse.success("修改成功");
    }

    /**
     *  删除用户
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public VResponse<Object> deleteUser(@RequestParam("id") long id) {
        this.userService.deleteUser(id);
        return VResponse.success("删除成功");
    }
}