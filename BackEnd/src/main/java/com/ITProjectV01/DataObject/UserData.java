package com.ITProjectV01.DataObject;
// UserData 实体类

import javax.persistence.*;


@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    private String userName;

    private Boolean userIdentity;

    private String userPassword;

    private Integer moneyRemain;

    private String userEmail;

    private String userAddress;

    private String image;

    private String shoppingCart;


    private String favoriteList;



    // getters
    public Integer getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public Boolean getUserIdentity() {
        return userIdentity;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Integer getMoneyRemain() {
        return moneyRemain;
    }

    public String getUserEmail() {return userEmail;}

    public String getUserAddress() {return userAddress;}

    public String getImage() {
        return image;
    }

    public String getShoppingCart() {
        return shoppingCart;
    }


    public String getFavorite() {
        return favoriteList;
    }




    // setters
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserIdentity(Boolean userIdentity) {
        this.userIdentity = userIdentity;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setMoneyRemain(Integer moneyRemain) {
        this.moneyRemain = moneyRemain;
    }

    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    public void setUserAddress(String userAddress) {this.userAddress = userAddress;}

    public void setImage(String image) {
        this.image = image;
    }

    public void setShoppingCart(String shoppingCart) {
        this.shoppingCart = shoppingCart;
    }


    public void setFavorite(String favoriteList) {
        this.favoriteList = favoriteList;
    }
}
