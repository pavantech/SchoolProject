package com.studentproject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="register")
@Entity
public class Register implements Serializable {
	  private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  @Column(name="id")
	  private int id;
	  @Column(name="username")
	  private String username;
	  @Column(name="password")
	  private String password;
	  @Column(name="firstname")
	  private String firstname;
	  @Column(name="lastname")
	  private String lastname;
	  @Column(name="email")
	  private String email;
	 
	  @Column(name="phone")
	  private String phone;
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	  
	  

}
