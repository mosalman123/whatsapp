package com.example.whatsapp.Models;

public class Users {
    String profileic,username,password,mail,userId,lastMessage,status;

    public Users(String profileic, String username, String password, String mail, String userId, String lastMessage) {
        this.profileic = profileic;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }
    public  Users(){}


    //signup constructor

    public Users( String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getProfileic() {
        return profileic;
    }

    public void setProfileic(String profileic) {
        this.profileic = profileic;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
