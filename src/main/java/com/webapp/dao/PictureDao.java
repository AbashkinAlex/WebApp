package com.webapp.dao;


import com.webapp.model.Picture;
import com.webapp.model.User;
import org.springframework.stereotype.Repository;


public interface PictureDao {

    void save(Picture picture);

    //void insert(Picture picture);

    Picture findById(Integer id);

    Picture findByUser(User user);

    Picture getByPath(String path);

}

