package com.ffbit.bcrypt.web;

import com.ffbit.bcrypt.domain.User;
import com.ffbit.bcrypt.dto.SignUpForm;
import com.ffbit.bcrypt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/signup")
@Secured("isAnonymous()")
public class SignUpController {
    private final String DEFAULT_REDIRECT = "redirect:/";
    private final String VIEW = "signup-form";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("signUpForm")
    public SignUpForm createSignUpFormBean(Model model) {
        return new SignUpForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Model model, @Valid SignUpForm signUpForm,
                         BindingResult result) {
        if (result.hasErrors()) {
            logSignUpErrors(result);
            fillErrors(model, result);

            return VIEW;
        }

        signUpUser(signUpForm);

        return DEFAULT_REDIRECT;
    }

    // Just for fun
    private void fillErrors(Model model, BindingResult result) {
        List<ObjectError> errors = result.getAllErrors();
        List<String> errorMessages = new ArrayList<String>(errors.size());

        for (ObjectError e : errors) {
            errorMessages.add(e.getDefaultMessage());
        }

        model.addAttribute("errors", errorMessages);
    }

    private void logSignUpErrors(BindingResult validationResult) {
        logger.info("Sing Up errors");

        for (ObjectError e : validationResult.getAllErrors()) {
            logger.info("sing up user error", e);
        }
    }

    private void signUpUser(SignUpForm signUpForm) {
        User user = new User(signUpForm.getUsername(), signUpForm.getPassword());
        userRepository.sigUp(user);
    }

}
