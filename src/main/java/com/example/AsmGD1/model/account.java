/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AsmGD1.model;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * @author Hieu
 */
public class account implements Serializable{
    @NotBlank(message = "Username Can not null")
    private String username;
    @NotBlank(message = "Password Can not null")
    private String password;

    public account() {
    }

    public account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
