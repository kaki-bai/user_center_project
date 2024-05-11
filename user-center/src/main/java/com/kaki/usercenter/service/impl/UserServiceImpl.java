package com.kaki.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaki.usercenter.model.domain.User;
import com.kaki.usercenter.service.UserService;
import com.kaki.usercenter.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kaki.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * user service implement class
 *
 * @author kaki
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     * salt: to obfuscate the password
     */
    private static final String SALT = "kaki";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // todo change to custom exception
        // 1. validation
        // not empty
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        // account length >= 4
        if (userAccount.length() < 4){
            return -1;
        }
        // password >= 8
        if (userPassword.length() < 8 || checkPassword.length() < 8){
            return -1;
        }
        // no special characters
        String validPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return -1;
        }
        // no repeat account
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.count(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // check password
        if (!userPassword.equals(checkPassword)){
            return -1;
        }

        // 2.encrypt password
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 3.insert user data into database
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult){
            return -1;
        }

        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. validation
        // not empty
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        // account length >= 4
        if (userAccount.length() < 4){
            return null;
        }
        // password >= 8
        if (userPassword.length() < 8){
            return null;
        }
        // no special characters
        String validPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return null;
        }

        // 2.encrypt password
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // check if the user exists
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        // user doesn't exist
        if (user == null){
            log.info("user login failed, userAccount cannot match userPassword");
            return null;
        }

        // 3.anonymize user data
        User safetyUser = getSafetyUser(user);

        // 4.track user login status
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    /**
     * anonymize user data
     *
     * @param user user
     * @return safety user
     */
    @Override
    public User getSafetyUser(User user) {
        if (user == null){
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserRole(user.getUserRole());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        return safetyUser;
    }
}




