package com.omnirio.service;

import com.omnirio.dao.DaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    @Qualifier("DaoParse")
    private DaoInterface dao;

    public String getAllUsers(){

        return dao.getAllUsers();

    }

}
