package com.haojii.demo.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.haojii.demo.dao.JPAUserDao;
import com.haojii.demo.dao.UserDao;
import com.haojii.demo.domain.User;
import com.haojii.demo.service.DefaultUserService;
import com.haojii.demo.service.ServiceLayerException;
import com.haojii.demo.service.UserService;
import com.haojii.demo.util.DBUtils;

public class Client {
	private static final Logger logger = Logger.getLogger(Client.class);
	public static void main(String... args) {
		
		DBUtils.setUp();
		
		User user = new User();
		user.setUsername("tim");
		user.setPassword("tim");
		
		login(user);
		
		DBUtils.tearDown();
	}
	
	// hey, let's just consider it as a mvc-controller method, the returned values will be mapped to some view
	public static String login(User user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		UserDao userDao = new JPAUserDao(em);
		UserService service = new DefaultUserService(userDao);
		
		boolean result = false;
		try {
			result = service.authenticateUser(user);
		} catch (ServiceLayerException e) {
			logger.warn("opps, exception happened during...", e);
			// flash.add("success","false");
			// flash.add("message","ServiceLayerException details:"+e.getMessage()); 
		} finally {
			em.getTransaction().commit();
			em.close();
			factory.close();
		}
		if(result) {
			logger.info("User login succeed.");
			// flash.add("success","true");
			// flash.add("message","User login succeed.");
			return "success";
		} else {
			logger.info("User login failed.");
			// flash.add("success","false");
			// flash.add("message","User login failed."); 
			return "failed";
		}
		
		
	}
}
