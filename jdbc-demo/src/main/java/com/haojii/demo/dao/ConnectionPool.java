package com.haojii.demo.dao;

import java.sql.Connection;

import com.haojii.demo.util.DBUtils;

public class ConnectionPool {
	/**
	 * Fake pool
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		return DBUtils.getConnection();
	}

}
