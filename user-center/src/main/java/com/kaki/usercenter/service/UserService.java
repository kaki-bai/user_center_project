package com.kaki.usercenter.service;

import com.kaki.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

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
     * @param planetCode planet code
     * @return new user id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * user login
     * @param userAccount user account
     * @param userPassword user password
     * @return anonymized user information
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * anonymize user data
     *
     * @param user user
     * @return safety user
     */
    User getSafetyUser(User user);

    /**
     * user log out
     * @param request request
     */
    int userLogout(HttpServletRequest request);
}
