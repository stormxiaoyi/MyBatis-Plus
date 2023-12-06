package com.dong.mybatisplus.polo;

import com.baomidou.mybatisplus.annotation.*;
import com.dong.mybatisplus.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：whd
 * @Description：
 * @Date：2023/12/2 下午 09:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
//设置实体类所对应的表名
//@TableName(value = "t_user")
public class User {

    //将属性所对应的字段指定为主键，mybatis-plus默认指定id为主键。
    //@TableId注解的value属性用于指定主键所对应数据库表中主键的字段名（注：如果注解只设置一个属性值，则默认设置的是value属性值）
    //@TableId注解的type属性设置主键生成策略。IdType.AUTO为数据库ID自增，该类型请确保数据库设置了ID自增，否则无效
    @TableId(value = "uid",type = IdType.AUTO)
    private Long id;

    //驼峰和下滑线自动转换
    //指定属性所对应数据库表中属性的字段名
    @TableField("user_name")
    private String name;

    private SexEnum sex;

    private Integer age;

    private String email;

    //逻辑删除，使用删除命令时会自动转变为update命令，将要删除的元素的isDeleted字段值修改为1，在进行查询时只会查询isDeleted字段值为0的元素
    @TableLogic
    private Integer isDeleted;

}
