package com.ITProjectV01.controller;


import com.ITProjectV01.DataObject.Product;
import com.ITProjectV01.DataObject.UserData;
import com.ITProjectV01.Service.ProductsService;
import com.ITProjectV01.Service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    @Resource
    private ProductsService productsService;

    // user login
    @PostMapping ("/login")
    @ResponseBody
    public UserData userLogin(@RequestBody(required = false) UserData userEnters){
        UserData userInDataBase = userService.findOne(userEnters.getUserID());
        String inputPwd = DigestUtils.sha256Hex(userEnters.getUserPassword());
        if (userInDataBase.getUserPassword().equals(inputPwd)){
            System.out.println("Login done");
            return userInDataBase;
        }
        System.out.println("Login fail");
        return null;
    }


    // user register
    @PostMapping("/registerUser")
    public UserData registerUser(@RequestBody(required = false)  UserData userEnters){
        UserData userData = new UserData();
        userData.setUserName(userEnters.getUserName());
        userData.setUserIdentity(userData.getUserIdentity());
        userData.setUserPassword(DigestUtils.sha256Hex(userEnters.getUserPassword()));
        userData.setUserEmail(userEnters.getUserEmail());
        userData.setUserAddress(userEnters.getUserAddress());
        userData.setMoneyRemain(9999);
        userData.setShoppingCart("");
        userData.setUserAddress("");
        userData.setFavorite("");
        userService.save(userData);
        System.out.println("User has registered");
        return userData;
    }


    // change user information
    @PostMapping("/updaterUser")
    public UserData updateUser(@RequestBody(required = false)  UserData userEnters){
        UserData userData = userService.findOne(userEnters.getUserID());
        userData.setUserName(userEnters.getUserName());
        userData.setUserIdentity(userEnters.getUserIdentity());
        userData.setUserPassword(DigestUtils.sha256Hex(userEnters.getUserPassword()));
        userData.setUserEmail(userEnters.getUserEmail());
        userData.setUserAddress(userEnters.getUserAddress());
        System.out.println("Data has been update to database");
        userService.save(userData);
        return userData;
    }


    // search users by ID
    @PostMapping("/lookUpUser")
    public UserData lookUpUser(Integer userID){
        UserData userData = userService.findOne(userID);
        return userData;
    }


    // delete user by ID
    @PostMapping("/deleteUser")
    public String deleteUser(Integer userID){
        System.out.println(userService.findOne(userID));
        userService.deleteByID(userID);
        return "User delete sucessfully";
    }




    // submit image for user (no use)
    @PostMapping("/submitImage")
    public UserData submit(MultipartFile file, UserData info)
            throws Exception {

        String filename = file.getOriginalFilename();
        System.out.println(filename);
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\img";
        if (!new File(filePath).exists()){
            new File(filePath).mkdir();
            
        }
        File dest = new File(filePath + File.separator + info.getImage()+"_"+filename);
        try {
            file.transferTo(dest);

        }catch (Exception e){
            e.printStackTrace();
        }

        info.setImage(info.getUserName()+"_"+filename);
        userService.save(info);
        return info;
    }




    // return shoppingCart
    @PostMapping("/getShoppingCart")
    public List<Product> getShoppingCart(Integer userID){
        UserData userInDataBase = userService.findOne(userID);

        // list to store shoppingCart item
        List<Product> shopCartConvert = new ArrayList<>();

        // if shopCart is first time (null)
        if (userInDataBase.getShoppingCart() == null){
            return shopCartConvert;

        }

        // convert item from string to Product and store in list
        String shoppingCart_curr = userInDataBase.getShoppingCart();
        for (int i = 0; i < shoppingCart_curr.length(); i++){
            if (shoppingCart_curr.charAt(i) == ' '){
                Integer product_id = Integer.parseInt((shoppingCart_curr.substring(i-2, i)));
                shopCartConvert.add(productsService.findOne(product_id));
            }
        }

        System.out.println("return shoppingCart");
        return shopCartConvert;
    }



    // add item to user shoppingCart
    @PostMapping("/addToShoppingCart")
    public List<Product> addToShoppingCart (Integer userID, Integer productID){
        UserData userInDataBase = userService.findOne(userID);
        // list to store item
        List<Product> shopCartConvert = new ArrayList<>();

        // first time add shopCart
        if (userInDataBase.getShoppingCart() == null){
            String shoppingCart_curr = userInDataBase.getShoppingCart();
            shoppingCart_curr += "00";
            shoppingCart_curr += productID.toString();
            shoppingCart_curr += " ";
            shopCartConvert.add(productsService.findOne(productID));
            userInDataBase.setShoppingCart(shoppingCart_curr);
            userService.save(userInDataBase);
            System.out.println("shoppingCart have been update");
            return shopCartConvert;
        }

        // convert original item from string to Product and store in list
        String shoppingCart_curr = userInDataBase.getShoppingCart();
        for (int i = 0; i < shoppingCart_curr.length(); i++){
            if (shoppingCart_curr.charAt(i) == ' '){
                Integer product_id = Integer.parseInt((shoppingCart_curr.substring(i-2, i)));
                shopCartConvert.add(productsService.findOne(product_id));
            }
        }
        // add new item
        shopCartConvert.add(productsService.findOne(productID));

        // update shoppingCart string
        shoppingCart_curr += "00";
        shoppingCart_curr += productID.toString();
        shoppingCart_curr += " ";

        // update to database
        userInDataBase.setShoppingCart(shoppingCart_curr);
        userService.save(userInDataBase);
        System.out.println("shoppingCart add item done");
        return shopCartConvert;
    }



    // delete item from user shoppingCart
    @PostMapping("/deleteFromShoppingCart")
    public List<Product> deleteFromShoppingCart (Integer userID, Integer productID){
        UserData userData = userService.findOne(userID);
        String shoppingCart_curr = userData.getShoppingCart();

        // convert item from string to Product and store in list, but not delete item
        List<Product> shopCartConvert = new ArrayList<>();
        for (int i = 0; i < shoppingCart_curr.length(); i++){
            if (shoppingCart_curr.charAt(i) == ' ' ){
                int remainItem = Integer.parseInt((shoppingCart_curr.substring(i - 2, i)));
                if (remainItem != productID){
                    shopCartConvert.add(productsService.findOne(remainItem));
                }
            }
        }

        // update shoppingCart String
        String new_shopCart = "";
        for (int i = 0; i < shopCartConvert.size(); i++){
            new_shopCart += "00";
            new_shopCart += shopCartConvert.get(i).getProductID().toString();
            new_shopCart += " ";
        }

        // update to database
        userData.setShoppingCart(new_shopCart);
        userService.save(userData);
        System.out.println("shoppingCart delete item done");
        return shopCartConvert;
    }



    // buy item
    @PostMapping("/buyItem")
    public Boolean buyItem (Integer userID, int money_needed){
        UserData userInDataBase = userService.findOne(userID);

        // change default value to 0
        if (userInDataBase.getMoneyRemain() == null){
            userInDataBase.setMoneyRemain(0);
            userService.save(userInDataBase);
        }

        Integer userMoney = userInDataBase.getMoneyRemain();
        // buying process identify
        if (userMoney < money_needed){
            System.out.println("buying fail");
            return false;
        }
        // update database
        userInDataBase.setMoneyRemain(userMoney-money_needed);
        System.out.println("buying done");
        userService.save(userInDataBase);

        // update product number of stock
        String shoppingCart_curr = userInDataBase.getShoppingCart();
        List<Product> shopCartConvert = new ArrayList<>();
        for (int i = 0; i < shoppingCart_curr.length(); i++){
            if (shoppingCart_curr.charAt(i) == ' ' ){
                Integer product_id = Integer.parseInt((shoppingCart_curr.substring(i-2, i)));
                shopCartConvert.add(productsService.findOne(product_id));
            }
        }
        for (int i = 0; i < shopCartConvert.size(); i++){
            shopCartConvert.get(i).setNumberOfStock((shopCartConvert.get(i).getNumberOfStock())-1);
            productsService.save(shopCartConvert.get(i));
        }
        return true;
    }








    // return favorite list
    @PostMapping("/getFavorite")
    public List<Product> getFavorite(Integer userID){
        UserData userInDataBase = userService.findOne(userID);

        List<Product> favoriteCart = new ArrayList<>();

        // if favorite is null (nothing inside)
        if (userInDataBase.getFavorite() == null){
            return favoriteCart;

        }

        // convert item from string to Product and store in list
        String favoriteList_curr = userInDataBase.getFavorite();
        for (int i = 0; i < favoriteList_curr.length(); i++){
            if (favoriteList_curr.charAt(i) == ' '){
                Integer product_id = Integer.parseInt((favoriteList_curr.substring(i-2, i)));
                System.out.println(product_id);
                favoriteCart.add(productsService.findOne(product_id));
            }
        }

        System.out.println("return Favorite");
        return favoriteCart;
    }




    // add favorite
    @PostMapping("/addFavorite")
    public List<Product> addFavorite (Integer userID, Integer productID){
        UserData userInDataBase = userService.findOne(userID);

        // generate list to store favorite
        List<Product> favoriteCart = new ArrayList<>();

        // first time add favorite
        if (userInDataBase.getFavorite() == null){
            String favorite_curr = userInDataBase.getFavorite();
            favorite_curr += "00";
            favorite_curr += productID.toString();
            favorite_curr += " ";
            favoriteCart.add(productsService.findOne(productID));
            userInDataBase.setFavorite(favorite_curr);
            userService.save(userInDataBase);
            System.out.println("favorites have been update");
            return favoriteCart;
        }

        // convert item from string to Product and store in list
        String favorite_curr = userInDataBase.getFavorite();
        for (int i = 0; i < favorite_curr.length(); i++){
            if (favorite_curr.charAt(i) == ' ' ){
                Integer productFav_curr = Integer.parseInt(favorite_curr.substring(i-2, i));
                favoriteCart.add(productsService.findOne(productFav_curr));
            }
        }
        // add new favorite item
        favoriteCart.add(productsService.findOne(productID));

        // update user favorite string
        String favorite_new = favorite_curr;
        favorite_new += "00";
        favorite_new += productID.toString();
        favorite_new += " ";

        // update to database
        userInDataBase.setFavorite(favorite_new);
        userService.save(userInDataBase);
        System.out.println("favorites have been update");
        return favoriteCart;

    }



    // delete favorite item from user favoriteList
    @PostMapping("/deleteFromFavoriteList")
    public List<Product> deleteFromFavoriteList (Integer userID, Integer productID){
        UserData userData = userService.findOne(userID);
        String favorite_curr = userData.getFavorite();

        // convert item from string to Product and store in list but not delete item
        List<Product> favoriteList = new ArrayList<>();
        for (int i = 0; i < favorite_curr.length(); i++){
            if (favorite_curr.charAt(i) == ' ' ){
                int remainItem = Integer.parseInt((favorite_curr.substring(i - 2, i)));
                if (remainItem != productID){
                    favoriteList.add(productsService.findOne(remainItem));
                }
            }
        }

        // update favorite string
        String new_favorite = "";
        for (int i = 0; i < favoriteList.size(); i++){
            new_favorite += "00";
            new_favorite += favoriteList.get(i).getProductID().toString();
            new_favorite += " ";
        }

        // update database
        userData.setFavorite(new_favorite);
        userService.save(userData);
        System.out.println("delete item from Favorite done");
        return favoriteList;
    }



}
