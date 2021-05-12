package com.oruina.spring_app;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.oruina.spring_app.entities.UserEntity;
import com.oruina.spring_app.repositories.IUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringAppApplicationTests {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	public void TestcreateUser() {
		
		UserEntity user = new UserEntity();
		user.setName("Oscar");
		user.setLastname("Ruina");
		user.setType("dni");
		user.setDni(35639606);
		user.setEmail("oscarruina@hotmail.com");
		user.setUsername("oruina");
		user.setPassword(encoder.encode("1234"));
		user.isEnabled();
		user.setCreateAt(LocalDate.now());
		user.setUpdatedAt(LocalDate.now());
		UserEntity retorno = userRepository.save(user);
		assertTrue(retorno.getPassword().equals(user.getPassword()));
	}
	
	@Test
	public void TestQueryUser() {
		
		UserEntity retorno = userRepository.findByUsernameAndFetchUserRolesEagerly("oruina");
		assertTrue(retorno != null);
		
	}

}
