package com.danny.project.service.impl;

import com.danny.project.dao.UserMapper;
import com.danny.project.model.User;
import com.danny.project.service.UserService;
import com.danny.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/09.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
