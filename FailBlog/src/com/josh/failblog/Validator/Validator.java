package com.josh.failblog.Validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
@RequestScoped
public class Validator {

	private static final String EMAIL_PATTERN = 
			"";
	
	private static final String PASSWORD_PATTERN = 
            "";
	
	public static String getEmailPattern() {
		return EMAIL_PATTERN;
	}

	public static String getPasswordPattern() {
		return PASSWORD_PATTERN;
	}

	public void validatePasswords(ComponentSystemEvent event) {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIComponent components = event.getComponent();

		UIInput uiInputPassword = (UIInput) components
				.findComponent("password");
		String password = uiInputPassword.getLocalValue() == null ? ""
				: uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();

		UIInput uiInputConfirmPassword = (UIInput) components
				.findComponent("confirmPassword");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
				: uiInputConfirmPassword.getLocalValue().toString();

		if (password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		}

		if (!password.equals(confirmPassword)) {
			fc.addMessage(passwordId, new FacesMessage("Passwords must match"));
			fc.renderResponse();

		}
	}

}
