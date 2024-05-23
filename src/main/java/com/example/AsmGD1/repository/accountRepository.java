/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AsmGD1.repository;

import com.example.AsmGD1.model.account;

/**
 *
 * @author Hieu
 */
public class accountRepository {
    public boolean login(account ac){
        if(ac.getUsername().equalsIgnoreCase("admin")&&ac.getPassword().equals("admin")){
            return true;
        }
        return false;
    }
}
