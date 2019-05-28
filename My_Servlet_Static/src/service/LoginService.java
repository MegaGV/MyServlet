package service;

import entity.Administrator;

public class LoginService {

    public boolean login(String username, String password){
        Administrator administrator = new Administrator();
        return username.equals(administrator.getUsername()) && password.equals(administrator.getPassword());
    }
}
