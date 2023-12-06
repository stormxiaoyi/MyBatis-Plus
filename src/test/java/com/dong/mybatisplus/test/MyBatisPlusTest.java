package com.dong.mybatisplus.test;

import com.dong.mybatisplus.mapper.UserMapper;
import com.dong.mybatisplus.polo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：whd
 * @Description：BaseMapper简单使用
 * @Date：2023/12/2 下午 09:19
 */
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectList(){
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        /**
         * 实现新增用户信息
         * INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
         */
        User user = new User();
        user.setName("张三");
        user.setAge(32);
        user.setEmail("111@qq.com");
        int result = userMapper.insert(user);
        System.out.println("result："+result);
        //获取新增用户的id
        System.out.println("Id："+user.getId());
    }

    @Test
    public void testDelete(){
        /**
         * 根据用户id删除用户信息
         * DELETE FROM user WHERE id=?
         */
        /*int result = userMapper.deleteById(1731198898845777921L);
        System.out.println("result："+result);*/

        /**
         * 根据map集合中所设置的条件来删除用户信息
         * DELETE FROM user WHERE name = ? AND age = ?
         */
        /*Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",32);
        int result = userMapper.deleteByMap(map);
        System.out.println("result："+result);*/

        /**
         * 通过多个id来实现批量删除
         * DELETE FROM user WHERE id IN ( ? , ? , ? )
         */
        List<Long> idList = Arrays.asList(31L, 32L, 33L,34L,35L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("result："+result);
    }

    @Test
    public void testUpdate(){
        /**
         * 修改用户信息
         * UPDATE user SET name=?, email=? WHERE id=?
         */
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("222@qq.com");
        int result = userMapper.updateById(user);
        System.out.println("result："+result);
    }

    @Test
    public void testSelect(){
        /**
         * 根据用户id查询用户信息
         * SELECT id,name,age,email FROM user WHERE id=?
         */
        /*User user = userMapper.selectById(1L);
        System.out.println(user);*/

        /**
         * 根据多个id查询多个用户信息
         * SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
         */
        /*List<Long> idList = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(idList);
        users.forEach(System.out::println);*/

        /**
         * 根据map集合中的条件来查询用户信息
         * SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
         */
        /*Map<String,Object> map=new HashMap<>();
        map.put("name","小王");
        map.put("age",84);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);*/

        /**
         * 查询所有用户信息
         * SELECT id,name,age,email FROM user
         */
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        //自定义功能
        /*Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);*/
    }
}
