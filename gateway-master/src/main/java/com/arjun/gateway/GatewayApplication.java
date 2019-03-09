package com.arjun.gateway;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.arjun.gateway.bean.auth.Role;
import com.arjun.gateway.bean.auth.User;
import com.arjun.gateway.repository.UserRepository;
import com.arjun.gateway.security.JwtTokenProvider;
import com.arjun.gateway.service.serviceimpl.LoginService;

@EnableZuulProxy
@EnableEurekaServer
@SpringBootApplication
public class GatewayApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private LoginService serv;
	@Autowired
	private JwtTokenProvider tkt;
	
	public static void main(String[] args) {
		
			
		
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		repo.deleteAll();

		User a = new User();
		a.setEmail("username");
		a.setPassword("password");
		a.setEnabled(true);
		a.setLastName("username");
		a.setName("username");
		//a.setExpired(false);
	
	     
		Set<Role> set = new HashSet<Role>();
		Role r= new Role();
		r.setId(123);
		r.setRole("readWrite");
		set.add(r);
		//set.add(new Role());

		a.setRole(set);
		//a.setName(name);
		List<String> roles= new ArrayList<String>();
		roles.add("readWrite");
		serv.saveUser(a);
		tkt.createToken("username",roles);
		System.out.println("Success");
		

		
		}



	
}
