package com.ITProjectV01.Service.impl;


import com.ITProjectV01.DAO.UserDataDao;
import com.ITProjectV01.DataObject.UserData;
import com.ITProjectV01.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataImpl implements UserService{


    @Autowired
    private UserDataDao userDataDao;


    @Override
    public UserData findOne(Integer UserID) {return userDataDao.findById(UserID).get();}

    @Override
    public List<UserData> findAll() {
        return userDataDao.findAll();
    }

    @Override
    public UserData save(UserData userData) {
        return userDataDao.save(userData);
    }

    @Override
    public void deleteByID(Integer userId) {
        userDataDao.deleteById(userId);
    }




}
