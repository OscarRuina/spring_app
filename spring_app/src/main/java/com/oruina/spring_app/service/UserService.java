package com.oruina.spring_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oruina.spring_app.entity.RoleEntity;
import com.oruina.spring_app.repository.IUserRepository;

@Service
public class UserService implements UserDetailsService{

	 @Autowired
	 @Qualifier("userRepository")
	 IUserRepository userRepository;
		
	 @Override
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
		 com.oruina.spring_app.entity.UserEntity appUser = 
				 userRepository.findByUsername(username);

		 //Mapear nuestra lista de Authority con la de spring security 
		 List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		 for (RoleEntity role: appUser.getRoles()) {
			 // ROLE_USER, ROLE_ADMIN,..
			 GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
			 grantList.add(grantedAuthority);
		 }
		 
		 //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
		 UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
		 return user;
	
	 }

}
