package com.kaki.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaki.usercenter.model.domain.User;
import com.kaki.usercenter.model.domain.request.UserLoginRequest;
import com.kaki.usercenter.model.domain.request.UserRegisterRequest;
import com.kaki.usercenter.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kaki.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.kaki.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * user interface
 *
 * @author kaki
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }

        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    @PostMapping("/logout")
    public Integer userLogout(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        return userService.userLogout(request);
    }

    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
        return null;
        }
        long userId = currentUser.getId();
        //TODO 校验用户是否合法
        User user = userService.getById(userId);
        return userService.getSafetyUser(user);
    }

    @GetMapping("/search")
    public List<User> searchUsers(String username, HttpServletRequest request) {
        if (!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)){
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * check if admin
     *
     * @param request request
     * @return boolean
     */
    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
