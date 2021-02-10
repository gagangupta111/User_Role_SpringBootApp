package com.omnirio.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("DaoParse")
public class DaoImplMemory implements DaoInterface{

    public String getAllUsers(){

        return "ABC";

    }

}
