package com.webapp.controller;

import com.webapp.util.ClassNameUtil;
import com.webapp.util.JspPath;
import org.apache.log4j.Logger;
import com.webapp.service.UserProfileService;
import com.webapp.model.User;
import com.webapp.model.UserProfile;
import com.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.CachingUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());


    private static boolean registrationSuccessful;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("authMgr")
    private AuthenticationManager authMgr;

    @Autowired
    private UserDetailsService userDetailsSvc;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        log.info("user login");
        return "login";
    }


    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        registrationSuccessful = false;
        log.info("user logout");
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/selectDash", method = RequestMethod.GET)
    public String selectDashboard() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (role.equals("[ROLE_ADMIN]")) {
            if (registrationSuccessful == true) {
                return "redirect:/adminDash/profile?regSuccess";
            }
            return "redirect:/adminDash/profile";
        }
        if (role.equals("[ROLE_DBA]")) {
            if (registrationSuccessful == true) {
                return "redirect:/dbaDash/profile?regSuccess";
            }
            return "redirect:/dbaDash/profile";
        }
        if (role.equals("[ROLE_USER]")) {
            if (registrationSuccessful == true) {
                return "redirect:/userDash/profile?regSuccess";
            }
            return "redirect:/userDash/profile";
        }
        return JspPath.ACCESS_DENIED;
    }


    /*
     * This method will be called on form submission, handling POST request It
     * also validates the user input
     */
    @RequestMapping(value = "/newUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveRegistration(@Valid User user,
                                   BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            log.debug("user has some errors !");
            log.debug("First Name : " + user.getFirstName());
            log.debug("Last Name : " + user.getLastName());
            log.debug("Password : " + user.getPassword());
            log.debug("Email : " + user.getEmail());
            log.debug("BirthDay : " + user.getBirthday());
            log.debug("Message : " + user.getMessage());
            for (ObjectError objectError : result.getAllErrors()) {
                System.out.println("" + objectError.toString());
            }
            return "redirect:/login?regError";
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            return "redirect:/login?emailExist";
        }
        userService.save(user);

        try {
            UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getEmail());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
            authMgr.authenticate(auth);

            if (auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                registrationSuccessful = true;
                return "redirect:/selectDash";
            }
        } catch (Exception e) {
            log.debug("Problem authenticating user" + user, e);

        }

        return "redirect:/error";
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostConstruct
    private void addUserProfiles() {
        if (userProfileService.findByType("ADMIN") == null) {

            UserProfile userUserProfile = new UserProfile();
            userUserProfile.setType("USER");
            userProfileService.save(userUserProfile);

            UserProfile userDbaProfile = new UserProfile();
            userDbaProfile.setType("DBA");
            userProfileService.save(userDbaProfile);

            UserProfile userAdminProfile = new UserProfile();
            userAdminProfile.setType("ADMIN");
            userProfileService.save(userAdminProfile);

        }

    }
}