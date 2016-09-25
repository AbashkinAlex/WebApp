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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "/userDash")
public class UserDashController {

    @Value("${cloud.aws.credentials.accesskey}")
    String accesskey;
    @Value("${cloud.aws.credentials.secretkey}")
    String secretkey;

    @Autowired
    UserService userService;
    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showUserDashboard(HttpServletRequest request) throws SQLException {
        ModelAndView modelAndView = new ModelAndView(JspPath.USER_DASHBOARD);
        User customUserDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = customUserDetails.getUsername();
        com.webapp.model.User user = userService.findByEmail(userEmail);
        modelAndView.addObject("myUserData", user);
        return modelAndView;
    }


    @RequestMapping(value = "/uploadPictures", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addPictures(@RequestParam("pictures") MultipartFile[] pictures,
                                    @RequestParam(required = true) Integer Id,
                                    HttpServletRequest request
            , HttpServletResponse response) throws IOException {


        com.webapp.model.User myUser = userService.findById(Id);
        String bucketName = "test-avatars-test";
        AWSCredentials credentials = new BasicAWSCredentials(accesskey, secretkey);
        AmazonS3 s3client = new AmazonS3Client(credentials);
        List<Picture> pictureList = new ArrayList<Picture>();
        String pathes = "";

        for (MultipartFile p : pictures) {
            String keyName = Id.toString() + "/" + p.getOriginalFilename();
//            uploadOnS3(bucketName, keyName, s3client, p);
            String picturePath = "http://" + bucketName + ".s3.amazonaws.com/" + keyName;
            Picture picture = new Picture();
            picture.setPath(picturePath);
            picture.setUser(myUser);
            pictureService.save(picture);
            myUser.getUserPictures().add(picture);
            pathes += picturePath + ", id=" + picture.getId() + " ";
        }

        userService.update(myUser);


        ModelAndView modelAndView = new ModelAndView(JspPath.USER_DASHBOARD);
        modelAndView.addObject("myUserData", myUser);
        return modelAndView;/**/

    }

    public void uploadOnS3(String bucketName, String keyName, AmazonS3 s3client, MultipartFile file) throws IOException {

        try {
            s3client.putObject(
                    new PutObjectRequest(bucketName, keyName, convert(file))
                            .withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}