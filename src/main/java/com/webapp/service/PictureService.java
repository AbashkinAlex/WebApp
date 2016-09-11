package com.webapp.service;

import com.webapp.model.Picture;

import java.sql.SQLException;
import java.util.List;

public interface PictureService {//extends Service<Picture>
    //@Override
    //List<Picture> getAll() throws SQLException;

    void save(Picture picture);

    void insert(Picture picture);

    //@Override
    Picture findById(Integer id) throws SQLException;

    Picture findByPath(String path) throws SQLException;
}
