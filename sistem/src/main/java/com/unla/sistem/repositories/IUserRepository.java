package com.unla.sistem.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.sistem.entities.UserEntity;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<UserEntity , Serializable>{
	
	@Query("SELECT u from User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
	public abstract UserEntity findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);

}
