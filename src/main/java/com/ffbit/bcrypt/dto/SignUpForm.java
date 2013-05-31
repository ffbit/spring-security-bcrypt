package com.ffbit.bcrypt.dto;

import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// Just for fun
@ScriptAssert(lang = "javascript",
        script = "_this.password.equals(_this.passwordConfirmation)",
        message = "signup.page.form.error.password.confirm")
public class SignUpForm {
    private String username = "";
    private String password = "";
    private String passwordConfirmation = "";

    @Size(min = 4, max = 40, message = "signup.page.form.error.username.size")
    @Pattern(regexp = "^[a-z]+$", message = "signup.page.form.error.username.pattern")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min = 6, max = 40, message = "signup.page.form.error.password.size")
    @Pattern(regexp = "^\\S+$", message = "signup.page.form.error.password.pattern")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

}
