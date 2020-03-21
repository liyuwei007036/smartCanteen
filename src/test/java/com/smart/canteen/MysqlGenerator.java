package com.smart.canteen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * <p>
 * mysql 代码生成器
 * </p>
 */
public class MysqlGenerator {

    /**
     * RUN THIS
     */
    @Test
    public void generatorCode() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("d:\\code\\java\\smartCanteen\\src\\main\\java");
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setAuthor("lc");
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存

        String entry_name = "LoginLog";
        String table_name = "login_log";


        gc.setServiceImplName(entry_name + "ServiceImpl");
        gc.setServiceName("I" + entry_name + "Service");
        gc.setMapperName(entry_name + "Mapper");
        gc.setEntityName(entry_name);
        gc.setXmlName(entry_name + "Mapper");
        gc.setControllerName(null);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://lumia.live:3306/smartcanteen?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);

        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig sy = new StrategyConfig();
        sy.setNaming(NamingStrategy.underline_to_camel);
        sy.setColumnNaming(NamingStrategy.no_change);

        sy.setInclude(table_name.toLowerCase());
        sy.setEntityLombokModel(true);
        sy.setRestControllerStyle(true);
        sy.setEntityTableFieldAnnotationEnable(true);
        sy.setVersionFieldName("version");
        sy.setLogicDeleteFieldName("deleted");
        mpg.setStrategy(sy);

        // 包配置
        PackageConfig pc = new PackageConfig();

        pc.setParent("com.smart.canteen");//父包名。// 自定义包路径  如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service");
        pc.setController("controller");//设置控制器包名
        mpg.setPackageInfo(pc);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
//        templateConfig.setEntity(gc.getEntityName());
//        templateConfig.setService(gc.getServiceName());
//        templateConfig.setController(gc.getControllerName());
        mpg.setTemplate(templateConfig);

        mpg.execute();


    }

}
