package com.haojii.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtils {
	private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);
	private static final String db_file_name_prefix = "db_file";

	static {
		// Load the HSQL Database Engine JDBC driver
		// hsqldb.jar should be in the class path or made part of the current
		// jar
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("NO HSQLDB DRIVER", e);
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:"
					+ db_file_name_prefix, // filenames
					"sa", // username
					""); // password
		} catch (SQLException e) {
			logger.error("CAN NOT ACCESS DATABASE", e);
		}
		return conn;
	}

	public static void setUp() {
		Statement st = null;
		Connection conn = null;
		try {

			conn = getConnection();
			st = conn.createStatement(); // statements

			String expression = "CREATE TABLE user_table ( id BIGINT IDENTITY, username VARCHAR(256), password VARCHAR(256) )";
			int i = st.executeUpdate(expression); // run the query

			if (i == -1) {
				logger.error("db error : " + expression);
			}

			st.executeUpdate("INSERT INTO user_table(username,password) VALUES('tim', 'tim')");
			st.executeUpdate("INSERT INTO user_table(username,password) VALUES('steve', 'steve')");
			st.executeUpdate("INSERT INTO user_table(username,password) VALUES('reggie', 'reggie')");
			st.executeUpdate("INSERT INTO user_table(username,password) VALUES('bill', 'bill')");

			st.close();
			conn.close();

		} catch (SQLException e) {
			logger.error("CAN CREATE TABLE user_table", e);
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e1) {
					logger.error("CAN NOT CLOSE Statement", e1);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					logger.error("CAN NOT CLOSE Connection", e1);
				}
			}
		}

	}

	public static void tearDown() {

		Statement st = null;
		Connection conn = null;
		try {
			conn = getConnection();
			st = conn.createStatement(); // statements
			st.executeUpdate("DELETE FROM user_table");
			st.executeUpdate("DROP TABLE user_table");
			st.executeUpdate("SHUTDOWN");
			st.close();
			conn.close(); // if there are no other open connection
		} catch (SQLException e) {
			logger.error("CAN SHUTDOWN DATABASE", e);
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e1) {
					logger.error("CAN NOT CLOSE Statement", e1);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					logger.error("CAN NOT CLOSE Connection", e1);
				}
			}
		}

	}

}