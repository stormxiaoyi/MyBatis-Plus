package com.dong.mybatisplus.test;

import com.dong.mybatisplus.enums.SexEnum;
import com.dong.mybatisplus.mapper.UserMapper;
import com.dong.mybatisplus.polo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author：whd
 * @Description：通用枚举
 * @Date：2023/12/5 下午 07:36
 */
@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testEnum(){
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setEmail("admin@qq.com");
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result："+result);
    }

}
