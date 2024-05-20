package top.haidong556.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import top.haidong556.chat_server.common.exception.oauth.OauthFriendException;
import top.haidong556.oauth.entity.Friend;
import top.haidong556.oauth.repository.FriendRepository;
import top.haidong556.oauth.service.FriendService;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Override
    public void addFriend(Friend friend) throws OauthFriendException {
        Friend repositoryFriend = null;
        try {
            repositoryFriend = friendRepository.getFriend(friend);
        } catch (Exception e) {
            throw new OauthFriendException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(repositoryFriend!=null)
            throw new OauthFriendException(OauthFriendException.OauthFriendExceptionType.FRIEND_HAVE_EXIST);
        int effectRows=0;

        try {
            effectRows = friendRepository.addFriend(friend);
        } catch (Exception e) {
            throw new OauthFriendException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }


//        }catch (Exception e) {
//            throw new OauthFriendException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
//        }
        if(effectRows<1)
            throw new OauthFriendException(OauthFriendException.OauthFriendExceptionType.FRIEND_ADD_FAIL);
    }

    @Override
    public void deleteFriend(Friend friend) throws OauthFriendException{
        Friend repositoryFriend = null;
        try {
            repositoryFriend = friendRepository.getFriend(friend);
        } catch (Exception e) {
            throw new OauthFriendException(OauthFriendException.MYBATIS_ERROR_CODE,e.getMessage());
        }
        if(repositoryFriend==null)
            throw new OauthFriendException(OauthFriendException.OauthFriendExceptionType.FRIEND_NOT_EXIST);
        int effectRows =0;
        try {
            friendRepository.deleteFriend(friend);
        }catch (Exception e){
            throw new OauthFriendException(OauthFriendException.OauthFriendExceptionType.FRIEND_DELETE_FAIL);
        }
        if(effectRows<1)
            throw new OauthFriendException(OauthFriendException.OauthFriendExceptionType.FRIEND_DELETE_FAIL);
    }

    @Override
    public List<Friend> getAllFriendsByUserId(long userId) throws OauthFriendException{
        List<Friend> allFriendsByUserId=null;
        try{
            allFriendsByUserId= friendRepository.getAllFriendsByUserId(userId);
            if(allFriendsByUserId.isEmpty()){
                throw new OauthFriendException(OauthFriendException.OauthFriendExceptionType.FRIEND_NOT_EXIST);
            }
        }catch (Exception e){
            throw new OauthFriendException(OauthFriendException.OauthFriendExceptionType.FRIEND_GET_FAIL);
        }
        return allFriendsByUserId;
    }

}
