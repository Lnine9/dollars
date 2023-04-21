package com.dollars.main.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.dollars.main.dto.Code;
import com.dollars.main.dto.UserDTO;
import com.dollars.main.service.UserService;
import com.dollars.main.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/user-manage", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public Result<Object> add(@RequestBody UserDTO userInfo){
        if (userService.add(userInfo)){
            return Result.success("注册成功");
        } else {
            return Result.error("注册失败");
        }
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public Result<UserDTO> getById(@RequestParam Long userId){
        UserDTO user = userService.getUserInfo(userId);
        if (user!=null){
            return new Result<>(Code.SUCCESS, "", user);
        } else {
            return new Result<>(Code.ERROR, "", null);
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public Result<UserDTO> login(@RequestParam String username, @RequestParam String password){
        UserDTO user = userService.login(username, password);
        if (user!=null){
            return new Result<>(Code.SUCCESS, "登陆成功", user);
        } else {
            return new Result<>(Code.ERROR, "登陆失败", null);
        }
    }

    @RequestMapping(path = "/contacts", method = RequestMethod.GET)
    public Result<List<UserDTO>> getContacts(@RequestParam Long userId){
        return new Result<>(Code.SUCCESS,"",userService.getContacts(userId));
    }

    @RequestMapping(path = "/contacts", method = RequestMethod.POST)
    public Result<Object> addFriend(@RequestBody String json){
        JSONObject req = JSON.parseObject(json);
        Long user1Id = req.getLong("user1Id");
        Long user2Id = req.getLong("user2Id");
        if (userService.addFriend(user1Id, user2Id)){
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public Result<Object> exception(Exception e){
        return Result.error(e.getMessage());
    }

}
