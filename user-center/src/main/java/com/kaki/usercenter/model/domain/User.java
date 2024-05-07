package com.kaki.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * user account
     */
    private String userAccount;

    /**
     * profile picture
     */
    private String avatarUrl;

    /**
     * gender
     */
    private Integer gender;

    /**
     * password
     */
    private String userPassword;

    /**
     * phone number
     */
    private String phone;

    /**
     * email address
     */
    private String email;

    /**
     *  user status (0: normal)
     */
    private Integer userStatus;

    /**
     * update time
     */
    private Date createTime;

    /**
     * update time
     */
    private Date updateTime;

    /**
     * is delete
     */
    @TableLogic
    private Integer isDelete;

    /**
     * user role (0-normal 1-manager)
     */
    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}