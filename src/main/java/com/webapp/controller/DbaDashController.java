package com.webapp.controller;

import com.webapp.service.PictureService;
import com.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Abashkin Aleksandr on 18.06.2016.
 */


@Controller
@RequestMapping(value = "/dbaDash")
public class DbaDashController {

    @Value("${cloud.aws.credentials.accesskey}")
    String accesskey;
    @Value("${cloud.aws.credentials.secretkey}")
    String secretkey;

    @Autowired
    UserService userService;
    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showDbaDashboard(HttpServletRequest request) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/dashboards/dbaBoard");
        User customUserDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = customUserDetails.getUsername();
        com.webapp.model.User user = userService.findByEmail(userEmail);
        modelAndView.addObject("myUserData", user);
        return modelAndView;
    }

}