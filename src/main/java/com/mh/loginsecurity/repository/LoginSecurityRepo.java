package com.mh.loginsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mh.loginsecurity.model.UserInfo;


public interface LoginSecurityRepo extends JpaRepository<UserInfo, Long> {

	@Query(value = "Select * from user_info where user_name=?1 and user_password=?2",nativeQuery = true)
	UserInfo checkLoginUser(String userName,String userPassword);
	
}
