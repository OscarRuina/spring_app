package com.oruina.spring_app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oruina.spring_app.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    UserService userService;
	
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts etc.
    String[] resources = new String[]{
    		"/include/**",
    		"/css/**",
    		"/icons/**",
    		"/images/**",
    		"/js/**",
    		"/layer/**"
    };
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/","/login").permitAll()
	        .antMatchers("/admin*").access("hasRole('ADMIN')")
	        .antMatchers("/user*").access("hasRole('USER') or hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
    
    /*El metodo authorizeRequests() permite restringir y/o dar acceso request HTTP.
		antMatchers(): Lista de URL que corresponden a un RequestMapping como lo hacemos en los controladores.
		permitAll(): Especifica que estas URLs son accesibles por cualquiera.
		access(): permite el acceso cumpliendo la expresión, en este caso tenemos la expresion “hasRole()”. Donde verifica si el usuario tiene ese especifico Role.
		anyRequest(): Ya que la configuración es lineal poniendo este metodo al final interpreta los request a las URLs que no fueron descritos, y en conjunto con el metodo authenticated() permite y da acceso a cualquier usuario que este autenticado.
	El metodo fomLogin(). Permite personalizar el proceso de inicio de sesión.
		loginPage(): indica la url de la pagina de inicio de sesión
		defaultSuccessUrl(): Indica a cual URL sera redirigido cuando el usuario inicie sesión.
		failureUrl(): Indica a cual URL sera redirigido cuando el inicio de sesión falla.
		usernameParameter() y passwordParameter(): Indica el nombre de los parámetros respectivamente.
	El metodo logout(): Personaliza el proceso de cierre de sesión.
		logoutSuccessUrl(): Indica la URL donde sera redirigido cuando el usuario cierre sesión.
	*/
    
    
    //Crea el encriptador de contraseñas	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(7);
		//El numero 7 representa que tan fuerte quieres la encriptacion.
		//Se puede en un rango entre 4 y 31. 
		//Si no pones un numero el programa utilizara uno aleatoriamente cada vez
		//que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
        return bCryptPasswordEncoder;
    }
	
    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());     
    }

}
