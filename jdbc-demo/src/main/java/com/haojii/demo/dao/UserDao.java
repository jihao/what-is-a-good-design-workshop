package com.haojii.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.haojii.demo.domain.User;

public interface UserDao {

	User add(User u) throws SQLException;
	User remove(User u) throws SQLException;
	User update(User u) throws SQLException;
	boolean isExist(User u) throws SQLException;
	List<User> list() throws SQLException;
}
