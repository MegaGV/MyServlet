package practice1.service;

import practice1.entity.Administrator;

public class LoginService {

    public boolean login(String username, String password){
        Administrator administrator = new Administrator();
        return username.equals(administrator.getUsername()) && password.equals(administrator.getPassword());
    }
}
