package com.haojii.demo.service;

import com.haojii.demo.domain.User;

public interface UserService {

	boolean authenticateUser(User user) throws ServiceLayerException;
	

}
