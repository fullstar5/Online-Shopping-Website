package com.ITProjectV01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.scheduling.annotation.EnableScheduling;


//@ComponentScan(basePackages={"com.ITProjectV01"}) // 扫描该包路径下的所有spring组件
//@EnableJpaRepositories("com.ITProjectV01.DAO") // JPA扫描该包路径下的Repositorie
//@EntityScan("com.ITProjectV01.DataObject") // 扫描实体类
@SpringBootApplication
@EnableScheduling
public class Itv01Application {

	public static void main(String[] args) {
		SpringApplication.run(Itv01Application.class, args);
	}

}
