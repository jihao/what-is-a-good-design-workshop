package com.haojii.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.haojii.demo.domain.User;

@Component
public class JPAUserDao implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;	
	

	@Override
	public User add(User user)  {
		entityManager.persist(user);
		return user;
		/*
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
		*/
	}

	@Override
	public User remove(User u)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User u)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExist(User user)  {
		Query query = entityManager.createQuery("SELECT u FROM User u where u.username=? and u.password=?");
		query.setParameter(1, user.getUsername());
		query.setParameter(2, user.getPassword());
		return (query.getSingleResult()!=null);		
	}

	@Override
	public List<User> list()  {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
