package com.newsapi.Service;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

	@Autowired
	private UserService userService;
	
	public String generateToken(String email,String password) throws ServletException {
		String jwtToken="";
		if(email==null || password==null) {
			throw new ServletException("Email or Password cannot be Empty");
		}
		boolean validUser = userService.validateUser(email, password);
		if(!validUser) {
			throw new ServletException("Invalid Credentials");
		}
		jwtToken = Jwts.builder().setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+36000000))
				.signWith(SignatureAlgorithm.HS256,"NewsApiSecretKey")
				.compact();
		return jwtToken;
	}
}
