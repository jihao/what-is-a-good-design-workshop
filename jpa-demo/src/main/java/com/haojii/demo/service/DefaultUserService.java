package com.haojii.demo.service;

import com.haojii.demo.dao.UserDao;
import com.haojii.demo.domain.User;

public class DefaultUserService implements UserService {
	private UserDao userDao;
	public DefaultUserService(UserDao userDao) {
		this.userDao = userDao;
	}
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

}
