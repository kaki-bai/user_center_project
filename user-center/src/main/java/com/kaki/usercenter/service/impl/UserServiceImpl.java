package com.kaki.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaki.usercenter.model.domain.User;
import com.kaki.usercenter.service.UserService;
import com.kaki.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author kaki
* @description 针对表【user(user)】的数据库操作Service实现
* @createDate 2024-05-06 21:17:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




