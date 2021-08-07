package com.newsapi.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newsapi.Model.User;
import com.newsapi.Service.AuthService;
import com.newsapi.Service.UserService;
import com.newsapi.exceptions.UserAlreadyExistsException;

@CrossOrigin
@RestController
@RequestMapping("/auth/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		User registeredUser;
		Map<String,String> response = new HashMap<>();
		try {
			registeredUser = userService.addUser(user);
		} catch (UserAlreadyExistsException e) {
			response.put("message", "User Already Exists");
			return new ResponseEntity<>(response,HttpStatus.CONFLICT);
		}
		if(registeredUser!=null) {
			response.put("message", "User Registered Successfully");
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}
		response.put("message", "Cannot Register User - Internal Server Error");
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		Map<String,String> map = new HashMap<>();
		try {
			String jwtToken = authService.generateToken(user.getEmail(), user.getPassword());
			map.put("message", "User Successfully LoggedIn");
			map.put("token", jwtToken);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("token", null);
			return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

}
