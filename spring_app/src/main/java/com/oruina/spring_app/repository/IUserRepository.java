package com.oruina.spring_app.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oruina.spring_app.entity.UserEntity;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<UserEntity,Serializable>{
	
	public abstract UserEntity findByUsername(String username);
	
}
