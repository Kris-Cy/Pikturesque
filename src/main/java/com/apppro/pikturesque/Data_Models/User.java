package com.apppro.pikturesque.Data_Models;

//this is my user data model for relating user information put in the app with the Firebase database associated with it.
public class User {
    private String email;
    private String user_id;
    private String username;

    //the main constructor for this class.
    public User(String email, String user_id, String username) {
        this.email = email;
        this.user_id = user_id;
        this.username = username;
    }

    //an empty constructor for now.
    public User(){

    }


    //Below are all my getter and setter methods for the children of the user object.
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //what this does is in the method's name.
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
