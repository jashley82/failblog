package com.josh.failblog.Controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.josh.failblog.Model.UserBean;
import com.josh.failblog.Utils.DBUtils;
import com.josh.failblog.Utils.PasswordEncryptionService;

@ManagedBean
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = -938745271916460953L;
	private boolean authenticated;
	private String username;
	private String password;
	private String confirmPassword;
	private String firstname;
	private String lastname;
	private String email;
	private UserBean user;

	public UserController() {}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String login() {
		UserBean tmp = DBUtils.lookupUser(username);
		if (tmp == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Username not found"));
		} else {
			try {
				if (PasswordEncryptionService.authenticate(password,
						tmp.getPasswordEncrypted(), tmp.getSalt())) {
					authenticated = true;
					setUser(tmp);
				} else {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(
							"Invalid password"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void clearData() {
		username = null;
		password = null;
		confirmPassword = null;
		firstname = null;
		lastname = null;
		email = null;
	}

	public String logout() {
		clearData();
		authenticated = false;
		user = null;
		return null;
	}

	public String createUser() {
		if (DBUtils.lookupUser(username) != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Username already taken"));
		} else {
			try {
				UserBean tmp = new UserBean();
				tmp.setUsername(username);
				tmp.setFirstname(firstname);
				tmp.setLastname(lastname);
				tmp.setEmail(email);
				tmp.setSalt(PasswordEncryptionService.generateSalt());
				tmp.setPasswordEncrypted(PasswordEncryptionService
						.getEncryptedPassword(password, tmp.getSalt()));
				DBUtils.insertUser(tmp);
				setUser(tmp);
				login();
				clearData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String cancelCreateUser() {
		clearData();
		authenticated = false;
		user = null;
		return null;
	}
}
