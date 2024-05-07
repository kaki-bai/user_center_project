package com.kaki.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * user registration request body
 *
 * @author kaki
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
