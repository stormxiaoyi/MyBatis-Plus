package com.dong.mybatisplus.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @Author：whd
 * @Description：代码生成器
 * @Date：2023/12/5 下午 07:58
 */
@SpringBootTest
public class FastAutoGeneratorTest {
    @Test
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/mybatis_plus?characterEncoding=utf-8&userSSL=false","root","200162")
                        .globalConfig(builder -> {
                            builder.author("whd") // 设置作者
                                    // .enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("G://IDEA-workplace//FastAutoMyBatis-Plus"); // 指定输出目录
                        })
                        .packageConfig(builder -> {
                            builder.parent("com.dong") // 设置父包名
                                    .moduleName("mybatisplus") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "G://IDEA-workplace//FastAutoMyBatis-Plus"));// 设置mapperXml生成路径
                        })
                        .strategyConfig(builder -> {
                            builder.addInclude("t_user") // 设置需要生成的表名
                                    .addInclude("t_product")
                                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                        })
                        .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                        .execute();
    }
}
