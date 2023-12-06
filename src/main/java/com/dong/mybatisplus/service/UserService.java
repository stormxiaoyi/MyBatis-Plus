package com.dong.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.mybatisplus.polo.User;
import org.springframework.stereotype.Service;

/**
 * @Author：whd
 * @Description：继承Mybatis-plus通用的Service接口IService（泛型为要操作的实体类）
 * @Date：2023/12/3 下午 04:03
 */
public interface UserService extends IService<User> {
}
