package com.webapp.service;

import com.webapp.dao.PictureDao;
import com.webapp.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService{

    @Autowired
    private PictureDao dao;

   // @Autowired
    //private PasswordEncoder passwordEncoder;


    public void insert(Picture picture){
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //dao.save(picture);
    }

    public void save(Picture picture){
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(picture);
    }

    public Picture findById(Integer id) {
        return dao.findById(id);
    }

    public Picture findByPath(String path) {
        return dao.getByPath(path);
    }
}



