package com.newsapi.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsapi.Model.User;
import com.newsapi.Repository.UserRepository;
import com.newsapi.exceptions.UserAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) throws UserAlreadyExistsException {
		Optional<User> founduser = userRepository.findByEmail(user.getEmail());
		if(founduser.isPresent()) {
			throw new UserAlreadyExistsException("User Already Registered");
		}
		return userRepository.saveAndFlush(user);
	}

	@Override
	public boolean validateUser(String email, String password){
		
		Optional<User> user = userRepository.validateUser(email, password);
		if(user.isPresent()) {
			return true;
		}
		return false;
		
	}

}
