package com.example.androidmvpdemo.Login.model;



public class LoginModel {

   public int login (String username, String password) {
       if(username == null ||username.isEmpty() ){
                return -2;
       }else if( password == null ||password.isEmpty()){
                return  -1;
       }else {
                return 0;
       }
   }
}
