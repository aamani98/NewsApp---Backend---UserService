package com.newsapi.Service;

import com.newsapi.Model.User;
import com.newsapi.exceptions.UserAlreadyExistsException;

public interface UserService {
	
	public User addUser(User user) throws UserAlreadyExistsException;
	public boolean validateUser(String email,String password);
}
