package com.oruina.spring_app.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oruina.spring_app.entities.UserEntity;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<UserEntity , Serializable>{
	
	@Query("SELECT u FROM UserEntity u JOIN FETCH u.roles WHERE u.username = (:username)")
	public abstract UserEntity findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);
	
}
