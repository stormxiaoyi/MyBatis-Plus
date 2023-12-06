package com.dong.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.mybatisplus.polo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author：whd
 * @Description：
 * @Date：2023/12/2 下午 09:15
 */
//BaseMapper是MyBatis-Plus提供的模板mapper，其中包含了基本的CRUD方法，泛型为操作的实体类型
@Repository//标识为持久层组件
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户id查询用户信息为map结合
     * @param id
     * @return
     */
    @MapKey("id")
    Map<String,Object> selectMapById(@Param("id") Long id);

    /**
     * 自定义分页功能：通过用户年龄查询用户信息并分页
     * @param page MyBatis-Plus提供的的分页对象，必须位于第一个参数的位置。（可参照MyBatis-Plus的BaseMapper提供的selectPage分页方法）
     * @param age
     * @return
     */
    Page<User> selectPageByAge(@Param("page") Page<User> page,@Param("age") Integer age);
}
