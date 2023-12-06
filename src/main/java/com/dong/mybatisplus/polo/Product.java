package com.dong.mybatisplus.polo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：whd
 * @Description：
 * @Date：2023/12/4 下午 09:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    private Long id;
    private String name;
    private Integer price;
    @Version//表示乐观锁版本号字段
    private Integer version;

}
