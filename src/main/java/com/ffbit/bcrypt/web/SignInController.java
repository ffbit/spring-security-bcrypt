package com.ffbit.bcrypt.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/signin")
public class SignInController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpSession session;

    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        logger.info("sign in form is shown");

        return "signin-form";
    }

    @ModelAttribute("securityException")
    private String signinException() {
        String securityExceptionAttribute = "SPRING_SECURITY_LAST_EXCEPTION";
        Object securityExceptionObject =
                session.getAttribute(securityExceptionAttribute);

        if (securityExceptionObject == null) {
            return "";
        }

        session.removeAttribute(securityExceptionAttribute);

        String prefix = "signin.exception.";
        String securityExceptionL10nKey =
                prefix + securityExceptionObject.getClass().getCanonicalName();

        logger.info("securityExceptionL10nKey {}" + securityExceptionL10nKey);

        return securityExceptionL10nKey;
    }

}
