package com.haojii.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haojii.demo.dao.UserDao;
import com.haojii.demo.domain.User;

@Service
public class DefaultUserService implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean authenticateUser(User user) throws ServiceLayerException {
		boolean result = false;
		
		try {
			result = userDao.isExist(user);
		} catch (Exception e) {
			// FIXME: this can totally be a point cut 
			throw new ServiceLayerException(e);
		}
		
		return result;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
