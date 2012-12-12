package com.haojii.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haojii.demo.dao.UserRepository;
import com.haojii.demo.domain.User;

@Service
public class DefaultUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean authenticateUser(final User user) throws ServiceLayerException {
		
		// FIXME: hey, where is exception? both checked and unchecked exception !!!
		User tmp = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if (tmp == null) {
			// FIXME: hey, it turns out here, we don't need this exception for handling normal business flow, right? 
			throw new ServiceLayerException(new Exception("username password doesn't match"));
		}
		
		return (tmp!=null);
	}

}
