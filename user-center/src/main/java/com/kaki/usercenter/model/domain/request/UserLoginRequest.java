package com.kaki.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * user login request body
 *
 * @author kaki
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userAccount;

    private String userPassword;
}
