package com.timothy.aopdemo;

import com.timothy.aopdemo.business.BusinessService1;
import com.timothy.aopdemo.business.BusinessService2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopDemoApplication implements CommandLineRunner {

	private BusinessService1 businessService1;
	private BusinessService2 businessService2;
	public AopDemoApplication(BusinessService1 businessService1,BusinessService2 businessService2) {
		this.businessService1 = businessService1;
		this.businessService2 = businessService2;
	}


	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Output is  "+ businessService1.calMaths());
		System.out.println("Output is  "+ businessService2.calMaths());
	}
}
