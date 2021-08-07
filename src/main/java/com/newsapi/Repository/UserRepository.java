package com.newsapi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.newsapi.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	public Optional<User> findByEmail(String email);
	
	@Query(value="select u from User u where u.email= :email and u.password= :password")
	public Optional<User> validateUser(String email, String password);

}
