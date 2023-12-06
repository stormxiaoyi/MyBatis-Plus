package com.dong.mybatisplus.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.mybatisplus.mapper.ProductMapper;
import com.dong.mybatisplus.mapper.UserMapper;
import com.dong.mybatisplus.polo.Product;
import com.dong.mybatisplus.polo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author：whd
 * @Description：分页插件和乐观锁插件
 * @Date：2023/12/4 下午 08:04
 */
@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testPage(){
        Page<User> page = new Page<>(10,3);
        page=userMapper.selectPage(page,null);
        System.out.println(page);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testPageByAge(){
        Page<User> page=new Page<>(2,2);
        page = userMapper.selectPageByAge(page, 20);
        System.out.println(page);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testProduct01(){
        //小李查询的商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格："+productLi.getPrice());
        //小王查询的商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格："+productWang.getPrice());
        //小李将商品价格加50
        productLi.setPrice(productLi.getPrice()+50);
        productMapper.updateById(productLi);
        //小王将商品价格减30
        productWang.setPrice(productWang.getPrice()-30);
        int result = productMapper.updateById(productWang);
        if(result==0){
            //操作失败，再次操作
            Product productWangNew = productMapper.selectById(1);
            System.out.println("小王最新查询的商品价格："+productWangNew.getPrice());
            productWangNew.setPrice(productWangNew.getPrice()-30);
            productMapper.updateById(productWangNew);
        }
        //老板查看商品价格
        Product productBoss=productMapper.selectById(1);
        System.out.println("老板查询的商品价格："+productBoss.getPrice());
    }
}
