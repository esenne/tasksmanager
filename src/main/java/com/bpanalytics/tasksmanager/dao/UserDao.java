package com.bpanalytics.tasksmanager.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bpanalytics.tasksmanager.persistence.model.User;

public interface UserDao extends Dao<User, Long> {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	boolean isUsernameTaken(String username);

}
