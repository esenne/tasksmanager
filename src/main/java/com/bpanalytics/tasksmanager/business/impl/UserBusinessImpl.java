package com.bpanalytics.tasksmanager.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bpanalytics.tasksmanager.business.UserBusiness;
import com.bpanalytics.tasksmanager.dao.UserDao;
import com.bpanalytics.tasksmanager.framework.exception.BusinessException;
import com.bpanalytics.tasksmanager.persistence.model.User;

@Service
public class UserBusinessImpl implements UserBusiness, UserDetailsService  {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User newUser(User user) {
		if (userDao.isUsernameTaken(user.getUsername())) {
			throw new BusinessException("Username already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userDao.save(user);
		
		return user;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.loadUserByUsername(username);
	}
}
