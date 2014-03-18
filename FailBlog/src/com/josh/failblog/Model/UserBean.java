package com.josh.failblog.Model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class UserBean implements Serializable {

	private static final long serialVersionUID = -7820087644383771655L;
	private String username;
	private byte[] passwordEncrypted;
	private String firstname;
	private String lastname;
	private String email;
	private Integer userid;
	private byte[] salt;

	public UserBean() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPasswordEncrypted() {
		return passwordEncrypted;
	}

	public void setPasswordEncrypted(byte[] passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
