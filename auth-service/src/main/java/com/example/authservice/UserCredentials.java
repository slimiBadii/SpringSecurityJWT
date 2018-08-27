package com.example.authservice;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javassist.expr.NewArray;



@Entity
public class UserCredentials  implements Serializable{
	
	
	private String username;
	private String password; 
	private String firstName; 
	private String LastName; 
	private String email; 
	private String phone; 
	private boolean status = true; 
	
	private static final long serialVersionUID = 784154L;
	
	@Id 
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="user_id", nullable = false, updatable=false)
	private long user_id;
	
	
	
	@Transient
	private String userrole ;
	
	
	
	

	
	public UserCredentials(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public UserCredentials() {
		// TODO Auto-generated constructor stub
	}
	public String getUserrole() {
		return this.getRole().getRoleName();
		}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private  Role role;
	 
	 
	
	public long getId() {
		return user_id;
	}
	public void setId(long id) {
		this.user_id = id;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	
	public boolean getStatus(){
		return this.status;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStatus(boolean enabled) {
		this.status = enabled;
	}
	


	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<GrantedAuthority> authorities = new HashSet<>(); 
		 authorities.add(new Authority(role.getRoleName()));
		
		return authorities;
	}
	
	
	
	
	

}

