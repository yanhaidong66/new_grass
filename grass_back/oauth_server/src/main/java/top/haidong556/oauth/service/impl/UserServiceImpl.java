package top.haidong556.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.haidong556.chat_server.common.exception.oauth.OauthFriendException;
import top.haidong556.chat_server.common.exception.oauth.OauthUserException;
import top.haidong556.oauth.repository.UserRepository;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.service.UserService;


@Service
public class UserServiceImpl implements UserService {
    private final String REDIS_USER_USERNAME_KEY_PREFIX="oauth:user:user_username:";
    private final String REDIS_USER_USERID_KEY_PREFIX="oauth:user:user_id:";
    @Autowired
    private UserRepository userRepository;
    @Override
    public MyUser getUserByUserName(String userName) throws OauthUserException {
//        Jedis jedisSession= JedisPool.getInstance().getJedis();
//        String redisResult=jedisSession.get(REDIS_USER_USERNAME_KEY_PREFIX+userName);
//        if(redisResult!=null)
//            return JSON.parseObject(redisResult, MyUser.class);
        MyUser user = null;
        try {
            user = userRepository.getUserByUserName(userName);
        } catch (Exception e) {
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(user==null)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_NOT_EXIST);
        return user;

    }
    @Override
    public void addUser(MyUser user) throws OauthFriendException {
        MyUser user1 = null;
        try {
            user1 = userRepository.getUserByUserName(user.getUserName());
        } catch (Exception e) {
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(user1!=null)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_HAVE_EXIST);
        long current=System.currentTimeMillis();
        user.setCreateTime(current);
        user.setModifiedTime(current);
        int effectRows = 0;
        try {
            effectRows = userRepository.addUser(user);
        } catch (Exception e) {
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(effectRows!=1)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_ADD_WRONG);
        //        Jedis jedisSession=JedisPool.getInstance().getJedis();
//        jedisSession.set(REDIS_USER_USERNAME_KEY_PREFIX+user.getUserName(),JSON.toJSONString(user));
//        jedisSession.set(REDIS_USER_USERID_KEY_PREFIX+user.getId(),JSON.toJSONString(user));

    }

    @Override
    public MyUser getUserByEmail(String email)throws OauthUserException {
        MyUser user = null;
        try {
            user = userRepository.getUserByEmail(email);
        } catch (Exception e) {
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(user==null)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_NOT_EXIST);
        return user;
    }

    @Override
    public void updatePassword(MyUser user, String newPassword)throws OauthUserException {
        MyUser user1 = null;
        try {
            user1 = userRepository.getUserByUserName(user.getUserName());
        } catch (Exception e) {
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(user1==null)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_NOT_EXIST);
        int effectRows = 0;
        try {
            userRepository.updatePasswordByUserName(user.getUserName(), newPassword);
        }catch (Exception e){
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(effectRows<1)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_PASSWORD_UPDATE_FAIL);
    }

    @Override
    public void deleteUser(MyUser user) throws OauthUserException {
        MyUser user1 = null;
        try {
            user1 = userRepository.getUserByUserName(user.getUserName());
        } catch (Exception e) {
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(user1==null)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_NOT_EXIST);
        int effectRows = 0;
        try {
            effectRows = userRepository.deleteUser(user.getUserName());
        } catch (Exception e) {
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(effectRows<1)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_DELETE_WRONG);
    }

    @Override
    public MyUser getUserByUserId(int id) throws OauthUserException {
        MyUser user = null;
        try {
            user = userRepository.getUserById(id);
        } catch (Exception e) {
            throw new OauthUserException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(user==null)
            throw new OauthUserException(OauthUserException.OauthUserExceptionType.USER_NOT_EXIST);
        return user;
    }
}
