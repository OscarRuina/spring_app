package com.unla.sistem.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "dni")
	private int dni;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "userName" , unique = true , nullable = false , length = 45)
	private String userName;
	
	@Column(name = "password" , nullable = false , length = 60)
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "creatAt")
	@CreationTimestamp
	private LocalDate creatAt;
	
	@Column(name = "updatedAt")
	@UpdateTimestamp
	private LocalDate updateAt;
	
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "user")
	private Set<UserRoleEntity> userRoles = new HashSet<UserRoleEntity>();
	
	public UserEntity() {}

	public UserEntity(String name, String lastName, String type, int dni, String email, String userName, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.type = type;
		this.dni = dni;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.enabled = true;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDate getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(LocalDate creatAt) {
		this.creatAt = creatAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

	public Set<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", lastName=" + lastName + ", type=" + type + ", dni=" + dni + ", email=" + email
				+ "]";
	}
	
}
