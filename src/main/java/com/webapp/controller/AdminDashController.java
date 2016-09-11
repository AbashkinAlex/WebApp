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
    public ModelAndView addPictures(//@ModelAttribute("myUserData") com.webapp.model.User myUserData,
                                    @RequestParam("pictures") MultipartFile[] pictures,
                                    @RequestParam(required = true) Integer Id,
                                    HttpServletRequest request
            , HttpServletResponse response) throws IOException {



        com.webapp.model.User myUser= userService.findById(Id);
        String bucketName = "test-avatars-test";
        AWSCredentials credentials = new BasicAWSCredentials(accesskey,secretkey);
        AmazonS3 s3client = new AmazonS3Client(credentials);
        //Picture[] pictureArray =new Picture[pictures.length];
        List<Picture> pictureList =new ArrayList<Picture>();
        String pathes="";

        for (MultipartFile p : pictures) {
            String keyName = Id.toString()+ "/" + p.getOriginalFilename();
            uploadOnS3(bucketName, keyName, s3client, p);
            String picturePath = "http://" + bucketName + ".s3.amazonaws.com/" + keyName;
            Picture picture = new Picture();
            picture.setPath(picturePath);
            picture.setUser(myUser);
            pictureService.save(picture);
            myUser.getUserPictures().add(picture);
            pathes+=picturePath+", id="+picture.getId()+" ";
        }

        userService.update(myUser);



        ModelAndView modelAndView = new ModelAndView("/dashboards/admin/UserBoard");
        modelAndView.addObject("myUserData", myUser);
        return modelAndView;/**/

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