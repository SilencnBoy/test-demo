package com.xl.controller;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 在普通的控制器和Servie业务中也可以直接实现EnvironmentAware来获取系统环境变量,但是在获取系统环境变量的时机为系统加载的时候
 * 
 * @author xl
 *
 */
@RestController
@RequestMapping("/system")
public class SystemEnvironmentController implements EnvironmentAware {

	private String java_home;

	@RequestMapping("/javahome")
	public String getJAVAHOME() {

		return java_home;
	}

	public void setEnvironment(Environment environment) {

		java_home = environment.getProperty("JAVA_HOME");
		System.out.println("控制器中获取的系统环境变量：" + java_home);
	}

}
