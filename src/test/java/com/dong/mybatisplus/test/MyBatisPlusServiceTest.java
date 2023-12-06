package com.dong.mybatisplus.test;

import com.dong.mybatisplus.polo.User;
import com.dong.mybatisplus.service.UserService;
import com.dong.mybatisplus.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：whd
 * @Description：
 * @Date：2023/12/3 下午 04:11
 */
@SpringBootTest
public class MyBatisPlusServiceTest {

    /**
     * 一：@Service，@Repository不能写在接口上，需要写在接口的实现类上。
     * 二：@Autowired注入的时候，可以用接口注入，也可以用接口的实现类注入。
     * 三：如果使用接口注入，并且接口有多个实现类，那么必须配合@Qualifier(value = “bean别名”)使用，指定具体的bean，
     * 需要在注入的实现类上面加上别名，例如@Repository(“accountDaoImpl”)
     */
    @Autowired
    private UserService userService;
    //private UserServiceImpl userServiceimpl;

    @Test
    public void testCount(){
        /**
         * 查询总记录数
         * SELECT COUNT( * ) AS total FROM user
         */
        long count = userService.count();
        System.out.println("总记录数："+count);
    }

    @Test
    public void testInsertBatch(){
        /**
         * 批量添加，循环执行单个sql语句
         * INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
         */
        List<User> userList=new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            User user = new User();
            user.setName("zhao"+i);
            user.setEmail(i+"126.com");
            user.setAge(5+i);
            userList.add(user);
        }
        boolean b = userService.saveBatch(userList);
        System.out.println("操作是否成功："+b);
    }

}
