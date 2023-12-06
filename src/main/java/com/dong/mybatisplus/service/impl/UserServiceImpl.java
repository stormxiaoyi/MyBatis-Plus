package com.dong.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.mybatisplus.mapper.UserMapper;
import com.dong.mybatisplus.polo.User;
import com.dong.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author：whd
 * @Description：实现自定义Service，并继承mybatis-plus默认IService实现类ServiceImpl（泛型为：自定义mapper接口和要操作的实体类）
 * @Date：2023/12/3 下午 04:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
