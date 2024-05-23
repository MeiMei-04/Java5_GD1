/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AsmGD1.repository;

import com.example.AsmGD1.model.user;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hieu
 */
public class userRepository {
    private List<user> listUser = new ArrayList<>();
    public userRepository() {
        for(int i = 1;i<11;i++){
            listUser.add(new user(i, "Dhieu"+i,i+"Dhieu@gmail.com", i%2==0?0:1));
        }
    }
    public List<user> fillAll(){
        return listUser;
    }
    public int getId(){
        int id = 0;
        int sizeList = listUser.size();
        if(sizeList>0){
            id = listUser.get(sizeList-1).getId();
        }
        return id+1;
    }
    public void create(user u){
        listUser.add(u);
    }
    public void update(user u){
        for(int i = 0;i<listUser.size();i++){
            if(listUser.get(i).getId() == u.getId()){
                listUser.set(i, u);
            }
        }
    }
    public void deleteById(int id){
        for(int i = 0;i<listUser.size();i++){
            if(listUser.get(i).getId() == id){
                listUser.remove(i);
            }
        }
    }
    public user getuserById(int id){
        user u = null;
        for(int i = 0;i<listUser.size();i++){
            if(listUser.get(i).getId() == id){
                u = listUser.get(i);
            }
        }
        return u;
    }
    
}
