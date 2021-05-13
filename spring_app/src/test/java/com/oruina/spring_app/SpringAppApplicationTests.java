package com.oruina.spring_app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oruina.spring_app.entity.UserEntity;
import com.oruina.spring_app.repository.IUserRepository;

@SpringBootTest
class SpringAppApplicationTests {
    
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void createUser() {
		
		UserEntity user = new UserEntity("user" , encoder.encode("user"));
		UserEntity userDb = userRepository.save(user);
		
		assertTrue(user.getPassword().equals(userDb.getPassword()));
		
		
	}

}
