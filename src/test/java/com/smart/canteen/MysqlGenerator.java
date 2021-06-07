package com.smart.canteen;

import com.baomidou.mybatisplus.generator.SimpleAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * <p>
 * mysql 代码生成器
 * </p>
 */
public class MysqlGenerator extends SimpleAutoGenerator {


    private String outputDir = "/Users/liyuwei/code/self/smartCanteen/src/main/java";
    private String auth = "/Users/liyuwei/code/self/smartCanteen/src/main/java";

    @Disabled
    @Test
    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public IConfigBuilder<GlobalConfig> globalConfigBuilder() {
        return new GlobalConfig.Builder().fileOverride()
                .outputDir(outputDir)
                .author(auth)
                .fileOverride()
                .openDir(false)
                .commentDate("yyyy-MM-dd HH:mm:ss")
                .dateType(DateType.TIME_PACK);
    }

    @Override
    public IConfigBuilder<PackageConfig> packageConfigBuilder() {
        return new PackageConfig.Builder().parent("com.smart.canteen");
    }

    @Override
    public IConfigBuilder<TemplateConfig> templateConfigBuilder() {
        return new TemplateConfig.Builder();
    }

    @Override
    public IConfigBuilder<StrategyConfig> strategyConfigBuilder() {
        return new StrategyConfig.Builder().addInclude("").entityBuilder().naming(NamingStrategy.underline_to_camel)
                .controllerBuilder().enableRestStyle().enableHyphenStyle();
    }

    @Override
    public IConfigBuilder<InjectionConfig> injectionConfigBuilder() {
        return super.injectionConfigBuilder();
    }

    @Override
    public AbstractTemplateEngine templateEngine() {
        return super.templateEngine();
    }

    @Override
    public IConfigBuilder<DataSourceConfig> dataSourceConfigBuilder() {
        return () -> new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:6306/smartcanteen?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true", "", "").build();
    }
}
