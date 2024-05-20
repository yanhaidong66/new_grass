package top.haidong556.oauth.service;

import top.haidong556.chat_server.common.exception.oauth.OauthUserException;
import top.haidong556.oauth.entity.MyUser;

public interface UserService {
    public MyUser getUserByUserName(String userName) throws OauthUserException;
    public void addUser(MyUser user) throws OauthUserException;
    public MyUser getUserByEmail(String email) throws OauthUserException;
    public void updatePassword(MyUser user,String newPassword) throws OauthUserException;
    public void deleteUser(MyUser user) throws OauthUserException;
    public MyUser getUserByUserId(int id) throws OauthUserException;
}
