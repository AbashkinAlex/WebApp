package com.webapp.service;

import com.webapp.model.Picture;

import java.sql.SQLException;
import java.util.List;

public interface PictureService {

    void save(Picture picture);

    void insert(Picture picture);

    Picture findById(Integer id) throws SQLException;

    Picture findByPath(String path) throws SQLException;
}
