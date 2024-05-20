package top.haidong556.oauth.repository;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.oauth.entity.Friend;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.mapper.FriendMapper;
import top.haidong556.oauth.mapper.UserMapper;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {
        FriendRepository.class, FriendMapper.class,UserRepository.class, UserMapper.class
})
class FriendRepositoryTest {
    @Autowired
    private FriendRepository friendRepository;
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
    void after()throws Exception{
        MyUser user = new MyUser(USER1, "123","112@qq", "ROLE_user", System.currentTimeMillis(), System.currentTimeMillis());
        MyUser user1 = new MyUser(USER2, "123", "112@qq", "ROLE_user", System.currentTimeMillis(), System.currentTimeMillis());
        friend = new Friend();
        friend.setUserId(userRepository.getUserByUserName(user.getUserName()).getId());
        friend.setFriendName(user1.getUserName());
        friend.setFriendId(user1.getId());
        friendRepository.deleteFriend(friend);
        userRepository.deleteUser(user.getUserName());
        userRepository.deleteUser(user1.getUserName());
    }


    @Test
    void getFriend() throws Exception{
        friendRepository.addFriend(friend);
        System.out.println(friendRepository.getFriend(friend));
    }

    @Test
    void addFriend()throws Exception {
        friendRepository.addFriend(friend);
    }

    @Test
    void deleteFriend()throws Exception {
        friendRepository.addFriend(friend);
        System.out.println(friendRepository.deleteFriend(friend));
    }

    @Test
    void findAllFriendsByUserId() throws Exception{
        friendRepository.addFriend(friend);
        System.out.println(friendRepository.getAllFriendsByUserId(userRepository.getUserByUserName(USER1).getId()));
    }


}