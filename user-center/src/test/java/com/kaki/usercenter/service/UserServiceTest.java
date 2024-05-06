package com.kaki.usercenter.service;
import java.util.Date;

import com.kaki.usercenter.model.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
/**
 * UserService Test
 *
 * @author kaki
 * */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("mockUser");
        user.setUserAccount("123");
        user.setAvatarUrl("mockAvatarUrl");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("123@qq.com");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);
    }

}