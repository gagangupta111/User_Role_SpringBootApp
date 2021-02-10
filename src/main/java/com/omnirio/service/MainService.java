package com.omnirio.service;

import com.omnirio.dao.DaoInterface;
import com.omnirio.model.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    @Qualifier("InMemory")
    private DaoInterface dao;

    public CustomResponse getAllUsers(){

        return dao.getAllUsers();

    }


}
