package com.organiccropmastery.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.organiccropmastery.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsernameAndPassword(String username, String password);
	
	@Query(value="select count(u) from User u")
	Long countOfUser();
	
	User findByEmailid(String emailId);
	
	@Query("SELECT e FROM User e WHERE e.mobileno = :mobileNumber")
	User findByMobile(String mobileNumber);

}
