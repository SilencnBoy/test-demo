package com.xl;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
  * TODO AppConfig启动类
  * 
  * @author xl
  * @date 2017年3月3日 下午8:41:19
  */
@SuppressWarnings("deprecation")
@EntityScan(basePackages = "com.xl")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.xl.dao")
@ImportResource(locations = { "applicationContext.xml" })// 导入spring配置文件
public class AppConfig extends SpringBootServletInitializer{
	
	// 配置文件上传
	@Bean
	public MultipartConfigElement multipartConfigFactory() {

		MultipartConfigFactory configFactory = new MultipartConfigFactory();
		configFactory.setMaxFileSize("128MB");// KB MB 设置单个上传文件大小
		configFactory.setMaxRequestSize("1024MB");
		configFactory.setLocation("/");// 设置文件上传路径
		return configFactory.createMultipartConfig();
	}
	
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppConfig.class);
    }
    //Banner.Mode.OFF:关闭;Banner.Mode.CONSOLE:控制台输出，默认方式; Banner.Mode.LOG:日志输出方式;
	public static void main(String[] args) {
		SpringApplication application = new  SpringApplication(AppConfig.class);
		application.setBannerMode(Mode.OFF);
		application.run(args);
	}
}
