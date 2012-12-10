package com.haojii.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.haojii.demo.domain.User;

public class JDBCUserDao implements UserDao {
		
	/**
	 * FIXME: hey, the following code has many issues, U know?
	 */
	@Override
	public User add(User user) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO user_table(username,password) VALUES(?, ?)");
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.executeUpdate();
		
		ps = conn.prepareStatement("SELECT id,username,password FRPM user_table where username=? and password=?");
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Long id = rs.getLong(1);
			user.setId(id);
			break;
		}
		ps.close();
		conn.close();
		
		return user;
	}

	@Override
	public User remove(User u) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User u) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * FIXME: hey, the following code has many issues, U know?
	 */
	@Override
	public boolean isExist(User user) throws SQLException {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT id,username,password FROM user_table where username=? and password=?");
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ResultSet rs = ps.executeQuery();
		ps.close();
		conn.close();
		
		return rs.next();
	}

	@Override
	public List<User> list() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
