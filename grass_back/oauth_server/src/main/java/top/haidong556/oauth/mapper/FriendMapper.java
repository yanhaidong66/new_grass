package top.haidong556.oauth.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.haidong556.oauth.entity.Friend;

import java.util.List;
@Mapper
public interface FriendMapper {
    Friend getFriend(Friend friend)throws Exception;
    int addFriend(Friend friend)throws Exception;
    int deleteFriend(Friend friend)throws Exception;
    List<Friend> getAllFriendsByUserId(long userId)throws Exception;
}