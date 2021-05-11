package com.oruina.spring_app.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oruina.spring_app.entities.UserEntity;
import com.oruina.spring_app.entities.UserRoleEntity;
import com.oruina.spring_app.repositories.IUserRepository;

@Service("userService")
public class UserService implements UserDetailsService{
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUSer(user , buildGrantedAuthorities(user.getRoles()));
	}
	
	private User buildUSer(UserEntity user , List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword() , user.isEnabled() , true , true , true , grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRoleEntity> userRoles){
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for(UserRoleEntity userRole : userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	
	

}
