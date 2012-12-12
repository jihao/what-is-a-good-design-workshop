package com.haojii.demo.dao;

import java.util.List;

import com.haojii.demo.domain.User;

public interface UserDao {

	User add(User u);

	User remove(User u);

	User update(User u);

	boolean isExist(User u);

	List<User> list();
}
