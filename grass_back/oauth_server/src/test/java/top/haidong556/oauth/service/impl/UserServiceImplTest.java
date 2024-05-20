package top.haidong556.oauth.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import top.haidong556.oauth.repository.UserRepository;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.mapper.UserMapper;

@SpringBootTest(
        classes = {
                UserServiceImpl.class,UserRepository.class, UserMapper.class
        }
)


class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;


    @Test
    void testGetUserByUserName() {
        MyUser user = userService.getUserByUserName("haidong");
        if(user==null)
            System.out.println("dont have");
        else
            System.out.println(user.toString());
    }

    @Test
    void testAddUser() {
        MyUser user=new MyUser("haidong89","555");
        user.setEmail("123");
        user.setAuthorities("ROLE_CHAT_USER,ROLE_USER");
        userService.addUser(user);
    }

    @Test
    void getUserByEmail() {
        MyUser user = userService.getUserByEmail("12453233");
        if(user==null)
            System.out.println("dont have");
        else
            System.out.println(user.toString());
    }

    @Test
    void testUpdatePassword() {
        MyUser user=new MyUser("haidon56g","55325");
        user.setEmail("123");
        userService.updatePassword(user, "33343");

    }
}