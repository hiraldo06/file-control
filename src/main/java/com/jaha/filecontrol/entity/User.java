package com.jaha.filecontrol.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="username", unique=true,nullable=false,length=45)
	private String username;
	
	@Column(name="password",nullable=false,length=60)
	private String password;
	
	@Column(name="enable",nullable=false)
	private boolean enable;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="cedula")
	@Max(11)
	@Min(11)
	private String cedula;
	
	@Column(name="exequatur")
	private String exequatur;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefono")
	private String telefono;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="user")
	private Set<UserRole>userRole=new HashSet<UserRole>();
	
	public User() {
		
	}

	public User(String username, String password, boolean enable) {
		super();
		this.username = username;
		this.password = password;
		this.enable = enable;
	}

	public User(String username, String password, boolean enable, Set<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	
	
}
