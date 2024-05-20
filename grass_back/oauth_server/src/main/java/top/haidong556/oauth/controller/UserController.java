package top.haidong556.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.haidong556.chat_server.common.exception.oauth.OauthUserException;
import top.haidong556.chat_server.common.response.BaseResponse;
import top.haidong556.chat_server.common.response.SuccessResponse;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public BaseResponse createUser(@RequestBody MyUser user)throws OauthUserException {
        userService.addUser(user);
        return new SuccessResponse("add user successfully");

    }
    @GetMapping("/id/{id}")
    public BaseResponse getUserById(@PathVariable("id") int id)throws OauthUserException{
        MyUser userByUserId = userService.getUserByUserId(id);
        return new SuccessResponse("get user successfully",userByUserId);
    }
    @GetMapping("/email/{email}")
    public BaseResponse getUserByEmail(@PathVariable("email")String email)throws OauthUserException{
        MyUser user = userService.getUserByEmail(email);
        return new SuccessResponse("get user successfully",user);
    }
    @GetMapping("/userName/{userName}")
    public BaseResponse getUserByUserName(@PathVariable("userName")String userName)throws OauthUserException{
        MyUser user = userService.getUserByUserName(userName);
        return new SuccessResponse("get user successfully",user);
    }

}
