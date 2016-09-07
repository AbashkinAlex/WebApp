package com.webapp.controller;

//import alex.pol.util.ClassNameUtil;
//import alex.pol.util.JspPath;
//import org.apache.log4j.Logger;

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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Abashkin Aleksandr on 18.06.2016.
 */


@Controller
@RequestMapping(value = "/adminDash")
public class AdminDashController {

    @Value("${cloud.aws.credentials.accesskey}")
    String accesskey;
    @Value("${cloud.aws.credentials.secretkey}")
    String secretkey;

    @Autowired
    UserService userService;
    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showAdminDashboard(
//            @ModelAttribute("myUserData") UserData myUserData,
//                                           @RequestParam(required = false) String firstName,
//                                           @RequestParam(required = false) String secondName,
            HttpServletRequest request) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/dashboards/admin/UserBoard");
        User customUserDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = customUserDetails.getUsername();
        com.webapp.model.User user = userService.findByEmail(userEmail);
//        List<Country> countryList = this.countryService.getAll();
//        List<City> cityList = this.cityService.getAll();
//        List<Avatar> avatarList = this.avatarService.getAll();

        modelAndView.addObject("myUserData", user);
//        modelAndView.addObject("streetList", streetList);
//        modelAndView.addObject("cityList", cityList);
//        modelAndView.addObject("countryList", countryList);
//        modelAndView.addObject("avatarList", avatarList);

        return modelAndView;
    }

    @RequestMapping(value = "/uploadPictures", method = RequestMethod.POST)
    @ResponseBody
    public String addPictures(//@ModelAttribute("myUserData") com.webapp.model.User myUserData,
                                    @RequestParam("pictures") MultipartFile[] pictures,
                                    @RequestParam(required = true) Integer Id,
                              HttpServletRequest request) throws IOException {
        /*HttpSession session = request.getSession();
        com.webapp.model.User sessionUser;
        String[] partEmail;
        sessionUser = (com.webapp.model.User) session.getAttribute("user");
        partEmail = sessionUser.getEmail().split("@");*/

/*
//~~~~~~~~~~~~~~~~~~~~~~~~~Костыль~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        User sessionUser;
        String[] partEmail;

        if(Id == 1){

            sessionUser = (User) session.getAttribute("admin");
            partEmail = userDataService.getById(1).getUser().getEmail().split("@");

        } else {

            sessionUser = (User) session.getAttribute("user");
            partEmail = sessionUser.getEmail().split("@");

        }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
       myUserData = userDataService.findByUser(sessionUser);*/



        com.webapp.model.User myUser= userService.findById(Id);
        String bucketName = "test-avatars-test";
        AWSCredentials credentials = new BasicAWSCredentials(accesskey,secretkey);
        AmazonS3 s3client = new AmazonS3Client(credentials);
        //Picture[] pictureArray =new Picture[pictures.length];


        String pathes="";
        for(int index = 0; index < pictures.length; index++) {
            String keyName = Id.toString()+ "/" + pictures[index].getOriginalFilename();
            uploadOnS3(bucketName, keyName, s3client, pictures[index]);
            String picturePath = "http://" + bucketName + ".s3.amazonaws.com/" + keyName;
            Picture picture = new Picture();
            picture.setPath(picturePath);
            picture.setUser(myUser);
            pictureService.save(picture);
            pathes+=picturePath+", id="+picture.getId()+" ";
        }









        /*if (!avatarFile.isEmpty()) {

            if (userDataService.getById(Id).getAvatar() != null) {
                preAva = (userDataService.getById(Id)).getAvatar();
                deleteAvatarOnS3(bucketName,keyName,s3client);
            }

            uploadOnS3(bucketName, keyName, s3client, avatarFile);

            String avatarPath = "http://" + bucketName + ".s3.amazonaws.com/" + keyName;

            Avatar avatar = new Avatar();
            List<Avatar> avatarList = avatarService.getAll();


            for (Avatar myAvatar : avatarList) {
                if (avatarPath.equals(myAvatar.getPath())) {
                    myUserData.setAvatar(this.avatarService.getByPath(avatarPath));
                    log.info("user changed avatar with Path " + avatarPath + " and  id " + avatar.getId());
                    userDataService.update(myUserData);

                    if(partEmail[0].equals("admin")){
                        return "redirect:/adminDash";
                    }
                    return "redirect:/userDash";
                }
            }

            avatar.setPath(avatarPath);
            avatarService.insert(avatar);

            if (avatar.getId() != null) {

                myUserData.setAvatar(this.avatarService.getById(avatar.getId()));

                if(preAva != null) {
                    avatarService.delete(preAva);
                    log.info("user deleted pre avatar from db with Path " + preAva.getPath() +
                            " and  id " + preAva.getId());
                }
            }

            log.info("user adding new avatar with Path " + avatarPath + " and  id " + avatar.getId());
            userDataService.update(myUserData);

        }

        if(partEmail[0].equals("admin")){
            return "redirect:/adminDash";
        }

        return "redirect:/userDash";
    }
*/


        return "id = " +Id.toString()+", fileNumber =" + pictures.length+" email = "+
                myUser.getEmail()+", picturePath = "+ pathes;

    }
    public void uploadOnS3(String bucketName, String keyName, AmazonS3 s3client,  MultipartFile file) throws IOException {

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

//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateAdminData(@ModelAttribute("myUserData") UserData myUserData,
//                                  @RequestParam(required = false) String firstName,
//                                  @RequestParam(required = false) String secondName,
//                                  @RequestParam(required = false) Integer countryId,
//                                  @RequestParam(required = false) Integer cityId,
//                                  @RequestParam(required = false) Integer streetId,
//                                  @RequestParam(required = false) String house,
//                                  @RequestParam(required = false) String apartment,
//                                  HttpServletRequest request) throws SQLException {
//        HttpSession session = request.getSession();
//        User sessionUser = (User) session.getAttribute("admin");
//        myUserData = userDataService.findByUser(sessionUser);
//        myUserData.setFirstName(firstName);
//        myUserData.setSecondName(secondName);
//        if (countryId != null) {
//            myUserData.setCountry(this.countryService.getById(countryId));
//        }
//        if (cityId != null) {
//            myUserData.setCity(this.cityService.getById(cityId));
//        }
//        if (streetId != null) {
//            myUserData.setStreet(this.streetService.getById(streetId));
//        }
//        myUserData.setHouse(house);
//        myUserData.setApartment(apartment);
//        userDataService.update(myUserData);
//        return "redirect:/adminDash/profile";
//    }
//
//
    @RequestMapping(value = "/listOfUsers", method = RequestMethod.GET)
    public ModelAndView showUsersTable(
//            @ModelAttribute("myUserData") UserData myUserData,
                                       HttpServletRequest request) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/dashboards/admin/admin");
//        List<UserData> userList = userDataService.getAll();
//        modelAndView.addObject("userList", userList);
        return modelAndView;
    }
//
//
////    @RequestMapping(value = "/listOfCountry", method = RequestMethod.GET)
////    public ModelAndView showCountryTable(@ModelAttribute("myUserData") UserData myUserData,
////                                         HttpServletRequest request) throws SQLException {
////        ModelAndView modelAndView = new ModelAndView(JspPath.ADMIN_DASHBOARD_COUNTRY_TABLE);
////        List<Country> countryList = countryService.getAll();
////        modelAndView.addObject("countryList", countryList);
////        return modelAndView;
////    }
////
////    @RequestMapping(value = "/listOfCity", method = RequestMethod.GET)
////    public ModelAndView showCityTable(@ModelAttribute("myUserData") UserData myUserData,
////                                      HttpServletRequest request) throws SQLException {
////        ModelAndView modelAndView = new ModelAndView(JspPath.ADMIN_DASHBOARD_CITY_TABLE);
////        List<City> cityList = cityService.getAll();
////        modelAndView.addObject("cityList", cityList);
////        return modelAndView;
////    }
//
////    @RequestMapping(value = "/listOfStreet", method = RequestMethod.GET)
////    public ModelAndView showStreetTable(@ModelAttribute("myUserData") UserData myUserData,
////                                        HttpServletRequest request) throws SQLException {
////        ModelAndView modelAndView = new ModelAndView(JspPath.ADMIN_DASHBOARD_STREET_TABLE);
////        List<Street> streetList = streetService.getAll();
////        modelAndView.addObject("streetList", streetList);
////        return modelAndView;
////    }
//    @RequestMapping(value = "/showMap", method = RequestMethod.GET)
//    public ModelAndView showMap(@ModelAttribute("myUserData") UserData myUserData,
//                                HttpServletRequest request) throws SQLException {
//        ModelAndView modelAndView = new ModelAndView(JspPath.ADMIN_DASHBOARD_MAP);
//        return modelAndView;
//    }
////
////
////    @RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
////    public ModelAndView addNewUser(@ModelAttribute("myUserData") UserData myUserData,
////                                HttpServletRequest request) throws SQLException {
////        ModelAndView modelAndView = new ModelAndView(JspPath.ADMIN_DASHBOARD_REGISTRATION);
////        return modelAndView;
////    }
//
//
////    /**
////     * Update(save new updated name of street) or save new street
////     *
////     * @param streetId
////     * @param streetName
////     * @return
////     * @throws SQLException
////     */
////    @RequestMapping(value = "/addNewStreet", method = RequestMethod.POST)
////    public String addNewStreet(@RequestParam(required = false) Integer streetId, @RequestParam(required = true) String streetName) throws SQLException {
////
////        System.out.println("streetId = " + streetId);
////        System.out.println("streetName = " + streetName);
////        if (streetId == null) {
////            Street street = new Street();
////            if (streetName == null) {
////                return "redirect:/except";
////            }
////            List<Street> streetList = streetService.getAll();
////            for (Street street1 : streetList) {
////                if (streetName.equals(street1.getName())) {
////                    return "redirect:/except";
////                }
////            }
////            street.setName(streetName);
////            log.info("admin adding new street with name " + streetName + " and  id" + streetId);
////            streetService.insert(street);
////        } else {
////
////            try {
////                Street street = streetService.getById(streetId);
////                street.setName(streetName);
//////                Street street = Street.newBuilder().setName(streetName).setId(streetId).build();
////                log.info("admin update the name of street on " + streetName + " and  id" + streetId);
////                streetService.update(street);
////            } catch (Exception e) {
////                e.printStackTrace();
////                return "redirect:/except";
////            }
////        }
////        return "redirect:/listOfStreet";
////    }
////
////
////    /**
////     * Show the page where you can update current name of street without adding new one
////     *
////     * @param streetId
////     * @return
////     * @throws SQLException
////     */
////
////    @RequestMapping(value = "/streetEdit", method = RequestMethod.POST)
////    public ModelAndView saveOrUpdateStreet(@RequestParam(required = false) Integer streetId) throws SQLException {
////        ModelAndView modelAndView = new ModelAndView(JspPath.ADMIN_DASHBOARD_STREET_EDIT);
////        if (streetId != null) {
////            Street street = streetService.getById(streetId);
////            modelAndView.addObject("street", street);
////        }
////        return modelAndView;
////    }
////
////    /**
////     * Delete street
////     *
////     * @param streetId
////     * @return
////     * @throws SQLException
////     */
////    @RequestMapping(value = "/deleteStreet", method = RequestMethod.POST)
////    public String deleteOne(@RequestParam(required = true) Integer streetId) throws SQLException {
////        streetService.delete(streetService.getById(streetId));
////        return "redirect:/listOfStreet";
////    }

}