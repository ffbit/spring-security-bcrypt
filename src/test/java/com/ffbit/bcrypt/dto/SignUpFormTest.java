package com.ffbit.bcrypt.dto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SignUpFormTest {

    private SignUpForm form;
    private boolean isValid;

    private Validator validator;

    public SignUpFormTest(String username, String password,
                          String passwordConfirmation, boolean isValid) {
        form = new SignUpForm();

        form.setUsername(username);
        form.setPassword(password);
        form.setPasswordConfirmation(passwordConfirmation);

        this.isValid = isValid;
    }

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {"user", "password", "password", true}
        });
    }

    @Test
    public void itShouldValidate() throws Exception {
        Set<ConstraintViolation<SignUpForm>> violations =
                validator.validate(form);
        assertThat(violations.isEmpty(), is(isValid));
    }

}
