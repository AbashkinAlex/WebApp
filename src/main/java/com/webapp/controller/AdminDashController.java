package com.webapp.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.webapp.model.Picture;
import com.webapp.service.PictureService;
import com.webapp.service.UserService;
import com.webapp.util.JspPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abashkin Aleksandr on 18.06.2016.
 */


@Controller
@RequestMapping(value = "/adminDash")
public class AdminDashController {

    @Autowired
    UserService userService;
    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showAdminDashboard(HttpServletRequest request) throws SQLException {
        ModelAndView modelAndView = new ModelAndView(JspPath.ADMIN_DASHBOARD);
        User customUserDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = customUserDetails.getUsername();
        com.webapp.model.User user = userService.findByEmail(userEmail);
        modelAndView.addObject("myUserData", user);
        return modelAndView;
    }

}