package top.haidong556.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.haidong556.chat_server.common.exception.oauth.OauthFriendException;
import top.haidong556.chat_server.common.response.BaseResponse;
import top.haidong556.chat_server.common.response.SuccessResponse;
import top.haidong556.oauth.entity.Friend;
import top.haidong556.oauth.service.FriendService;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
    @GetMapping("/{id}")
    public BaseResponse<List<Friend>> getFriends(@PathVariable("id") int userId) throws OauthFriendException {
        return new SuccessResponse<List<Friend>>("get Friend list success",friendService.getAllFriendsByUserId(userId));
    }
    @DeleteMapping()
    public BaseResponse deleteFriend(Friend friend)throws OauthFriendException{
        friendService.deleteFriend(friend);
        return new SuccessResponse("delete success");
    }
    @PostMapping()
    public BaseResponse addFriend(@RequestBody Friend friend)throws OauthFriendException{
        friendService.addFriend(friend);
        return new SuccessResponse("add success");
    }

}
