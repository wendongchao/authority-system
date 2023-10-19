package org.example.authority.controller;



import org.example.authority.entity.User;
import org.example.authority.service.UserService;
import org.example.authority.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bingo
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/listAll")
    public Result findAll(){
        List<User> userList = userService.list();
        return Result.ok(userList);
    }


}

