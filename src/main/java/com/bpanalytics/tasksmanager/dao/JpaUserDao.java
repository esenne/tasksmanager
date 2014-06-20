package com.bpanalytics.tasksmanager.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bpanalytics.tasksmanager.persistence.model.User;

@Repository
public class JpaUserDao extends JpaDao<User, Long> implements UserDao {

	public JpaUserDao() {
		super(User.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", username);
		
		List<User> users = query.getResultList();
		
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("The username " + username + " was not found");
		}
		
		return users.iterator().next();
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean isUsernameTaken(String username) {
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", username);
		
		List<User> users = query.getResultList();
		
		boolean isUsernameTaken = false;
		
		if (!users.isEmpty()) {
			isUsernameTaken = true;
		}
		
		return isUsernameTaken;
	}
}
