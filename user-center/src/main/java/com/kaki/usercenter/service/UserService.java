package com.kaki.usercenter.service;

import com.kaki.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * user service
 *
 * @author kaki
 */
public interface UserService extends IService<User> {
    /**
     * user registration
     *
     * @param userAccount user account
     * @param userPassword user password
     * @param checkPassword check password
     * @return new user id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
