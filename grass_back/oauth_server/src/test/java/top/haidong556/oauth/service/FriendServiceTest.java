package top.haidong556.oauth.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.oauth.entity.Friend;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.mapper.FriendMapper;
import top.haidong556.oauth.mapper.UserMapper;
import top.haidong556.oauth.repository.FriendRepository;
import top.haidong556.oauth.repository.UserRepository;
import top.haidong556.oauth.service.impl.FriendServiceImpl;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {
        FriendService.class
        , FriendServiceImpl.class
        , FriendMapper.class
        ,FriendRepository.class
        ,UserRepository.class
        , UserMapper.class
})
class FriendServiceTest {
    @Autowired
    private FriendService friendService;
    @Autowired
    private UserRepository userRepository;
    private Friend friend;
    static Random random=new Random();
    public static final String USER1="haidong"+random.nextInt(0,1000);
    public static final String USER2="haidong"+random.nextInt(0,1000);

    @BeforeEach
    void init() throws Exception {

        MyUser user = new MyUser(USER1, "123","112@qq", "ROLE_user", System.currentTimeMillis(), System.currentTimeMillis());
        MyUser user1 = new MyUser(USER2, "123", "112@qq", "ROLE_user", System.currentTimeMillis(), System.currentTimeMillis());
        userRepository.addUser(user);
        userRepository.addUser(user1);
        friend = new Friend();
        friend.setUserId(userRepository.getUserByUserName(user.getUserName()).getId());
        friend.setFriendName(user1.getUserName());
        friend.setFriendId(userRepository.getUserByUserName(user1.getUserName()).getId());
    }
    @AfterEach
    void after()throws SQLException{

    }

    @Test
    void addFriend() throws SQLException{
        friendService.addFriend(friend);
    }

    @Test
    void deleteFriend()throws SQLException {
        friendService.deleteFriend(friend);
    }

    @Test
    void getAllFriendsByUserId()throws SQLException {
        System.out.println(friendService.getAllFriendsByUserId(friend.getUserId()));
    }
}