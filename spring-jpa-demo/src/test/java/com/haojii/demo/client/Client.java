package com.haojii.demo.client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haojii.demo.domain.User;
import com.haojii.demo.service.ServiceLayerException;
import com.haojii.demo.service.UserService;
import com.haojii.demo.util.DBUtils;

@ContextConfiguration(locations={"/META-INF/spring/app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class Client {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserService service;
	
	private static final Logger logger = Logger.getLogger(Client.class);
	
	@BeforeClass
	public static void before() {
		DBUtils.setUp();	
	}
	@AfterClass
	public static void after() {
		DBUtils.tearDown();
	}
	
	// hey, let's just consider it as a mvc-controller method, the returned values will be mapped to some view
	@Test
	public void login() {
		
		User user = new User();
		user.setUsername("tim");
		user.setPassword("tim");
		
		boolean result = false;
		try {
			result = service.authenticateUser(user);
		} catch (ServiceLayerException e) {
			logger.warn("opps, exception happened during...", e);
			// flash.add("success","false");
			// flash.add("message","ServiceLayerException details:"+e.getMessage()); 
		} 
		if(result) {
			logger.info("User login succeed.");
			// flash.add("success","true");
			// flash.add("message","User login succeed.");
			// return "success";
		} else {
			logger.info("User login failed.");
			// flash.add("success","false");
			// flash.add("message","User login failed."); 
			// return "failed";
		}
		
		
	}
}
