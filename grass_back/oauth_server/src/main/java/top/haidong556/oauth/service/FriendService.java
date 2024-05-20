package top.haidong556.oauth.service;

import top.haidong556.chat_server.common.exception.oauth.OauthFriendException;
import top.haidong556.oauth.entity.Friend;

import java.util.List;

public interface FriendService {
    public void addFriend(Friend friend) throws OauthFriendException;
    public void deleteFriend(Friend friend) throws OauthFriendException;
    public List<Friend> getAllFriendsByUserId(long userId) throws OauthFriendException;
}
