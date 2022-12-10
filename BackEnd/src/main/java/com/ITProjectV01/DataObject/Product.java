package com.ITProjectV01.DataObject;
// 商家商品实体类

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer productID;

    private String productName;

    private String productMaterial;

    private Integer numberOfStock;

    private Integer moneyRequire;

    private String productDescription;

    private String suitableAge;

    private String image;



    // getters
    public String getImage() {
        return image;
    }

    public Integer getProductID() {return productID;}

    public String getProductName() {
        return productName;
    }

    public String getProductMaterial() {
        return productMaterial;
    }

    public Integer getNumberOfStock() {
        return numberOfStock;
    }

    public Integer getMoneyRequire() {
        return moneyRequire;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getSuitableAge() {return suitableAge;}






    // setters
    public void setImage(String image) {
        this.image = image;
    }
    public void setProductID(Integer productID) {this.productID = productID;}

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    public void setNumberOfStock(Integer numberOfStock) {
        this.numberOfStock = numberOfStock;
    }

    public void setMoneyRequire(Integer moneyRequire) {
        this.moneyRequire = moneyRequire;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setSuitableAge(String suitableAge) {
        this.suitableAge = suitableAge;
    }
}
