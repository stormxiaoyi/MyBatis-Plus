package com.dong.mybatisplus.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dong.mybatisplus.mapper.UserMapper;
import com.dong.mybatisplus.polo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Author：whd
 * @Description：QueryWrapper、UpdateWrapper、LambdaQueryWrapper、LambdaUpdateWrapper条件构造器使用
 * @Date：2023/12/4 下午 04:15
 */
@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        /**
         * 组装查询条件
         * 场景：查询用户名中包含a，年龄在20-30岁之间，邮箱不为null的用户信息
         * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0
         * AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","a")//注意：column属性值为数据库表中的字段名
                        .between("age",20,30)
                                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test02(){
        /**
         * 组装排序条件
         * 场景：查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
         * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                        .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test03(){
        /**
         * 组装删除条件
         * 场景：删除邮箱地址为null的用户信息
         * UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL) 注：使用了逻辑删除
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result："+result);
    }

    @Test
    public void test04(){
        /**
         * 使用QueryWrapper实现修改功能
         * 场景：将（年龄大于20且用户名中包含a）或者邮箱为null的用户信息修改
         * UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("age",20)
                        .like("user_name","a")
                                .or()
                                        .isNull("email");
        User user=new User();
        user.setName("AAA");
        user.setEmail("AAA@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result："+result);
    }

    @Test
    public void test05(){
        /**
         * 条件的优先级，lambda中的条件优先执行
         * 场景：将用户名中包含a并且（年龄大于20或邮箱为null）的用户信息修改
         * UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        User user=new User();
        user.setName("BBB");
        user.setEmail("BBB@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result："+result);
    }

    @Test
    public void test06(){
        /**
         * 组装select字句
         * 场景：查询用户的用户名、年龄、邮箱信息
         * SELECT user_name,age,email FROM t_user WHERE is_deleted=0
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("user_name","age","email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07(){
        /**
         * 组装子查询
         * 场景：查询uid大于20的用户信息
         * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
         * WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid>20))
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.inSql("uid","select uid from t_user where uid>20");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test08(){
        /**
         * UpdateWrapper使用
         * 场景：将用户名中包含a并且（年龄大于20或邮箱为null）的用户信息修改
         * UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
         */
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.like("user_name","a")
                        .and(i->i.gt("age",20).or().isNull("email"))
                                .set("user_name","小李子")
                                        .set("email","xiao@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result："+result);
    }

    @Test
    public void test09(){
        /**
         * 模拟开发中组装条件的情况（使用condition）
         * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
         * WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
         */
        String username="a";
        Integer ageBegin=null;
        Integer ageEnd=30;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"user_name",username)
                .ge(ageBegin!=null,"age",ageBegin)
                .le(ageEnd!=null,"age",ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test10(){
        /**
         * LambdaQueryWrapper使用
         * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
         * WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
         */
        String username="a";
        Integer ageBegin=null;
        Integer ageEnd=30;
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(username),User::getName,username)//函数式接口，访问实体类某个属性所对应的字段名
                .ge(ageBegin!=null,User::getAge,ageBegin)
                .le(ageEnd!=null,User::getAge,ageEnd);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println)  ;
    }

    @Test
    public void test11(){
        /**
         * LambdaUpdateWrapper使用
         * 场景：将用户名中包含a并且（年龄大于20或邮箱为null）的用户信息修改
         * UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
         */
        LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.like(User::getName,"a")
                .and(i->i.gt(User::getAge,20).or().isNull(User::getEmail))
                .set(User::getName,"CCC")
                .set(User::getEmail,"CCC@qq.com");
        int result = userMapper.update(null,lambdaUpdateWrapper);
        System.out.println("result："+result);
    }
}
