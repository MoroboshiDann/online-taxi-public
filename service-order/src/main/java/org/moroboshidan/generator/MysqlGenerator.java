package org.moroboshidan.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MysqlGenerator {
    /**
     * 自动生成工具类
     * @param
     * @return
     * @throws
     *
     */
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-order?charactorEncoding=utf-8&serverTimezone=GMT%2B8", "root", "123123")
                .globalConfig(builder -> {
                    builder.author("MoroboshiDan").fileOverride().outputDir("D:\\MyRepository\\gitRepository\\online-taxi-public\\service-order\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("org.moroboshidan").pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\MyRepository\\gitRepository\\online-taxi-public\\service-order\\src\\main\\java\\org\\moroboshidan\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("order");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
