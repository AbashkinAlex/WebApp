package com.webapp.dao;


import com.webapp.model.Picture;
import com.webapp.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("pictureDao")
public class PictureDaoImpl extends AbstractDao<Integer, Picture> implements PictureDao{

        public void save(Picture picture) {
            persist(picture);
        }

        public Picture findById(Integer id) {
            return getByKey(id);
        }

        public Picture findByUser(User user){return null;}

        public Picture getByPath(String path){return null;}


    }
