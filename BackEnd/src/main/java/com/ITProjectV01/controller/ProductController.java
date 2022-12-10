package com.ITProjectV01.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.ITProjectV01.DataObject.Product;
import com.ITProjectV01.Service.ProductsService;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductsService productsService;


    // add product
    @PostMapping("/uploadProduct")
    public Product submit(MultipartFile file, String productName, String productMaterial, Integer numberOfStock
            ,Integer moneyRequire, String productDescription, String suitableAge)

            throws Exception {

        Product newProduct = new Product();
        newProduct.setProductDescription(productDescription);

        newProduct.setProductName(productName);
        newProduct.setProductMaterial(productMaterial);
        newProduct.setMoneyRequire(moneyRequire);
        newProduct.setNumberOfStock(numberOfStock);
        newProduct.setSuitableAge(suitableAge);

        //upload product image
        // String filename = file.getOriginalFilename();
        

        // File f = new File(System.getProperty("user.dir"));
        
        // String filePath = f.getParent() +"\\FrontEnd\\static\\Image";
        String filePath = System.getProperty("user.dir") +"\\FrontEnd\\static\\Image";
        if (!new File(filePath).exists()){
            new File(filePath).mkdirs();

        }
        File dest = new File(filePath + File.separator + productName+".png");
        try {
            file.transferTo(dest);

        }catch (Exception e){
            e.printStackTrace();
        }

        newProduct.setImage(productName+".png");
        productsService.save(newProduct);
        return newProduct;
    }


    // delete product
    @PostMapping("/deleteProduct")
    public void deleteProduct(Integer productID){
        productsService.deleteProductByID(productID);
        System.out.println("Delete Sucessfully");
    }


    // look for product by ID
    @PostMapping("/lookByID")
    public Product lookByID(Integer productID){
        Product product = productsService.findOne(productID);
        return product;
    }


    // return all products
    @PostMapping("/findAll")
    public List<Product> findAllProducts(){
        return productsService.findAll();
    }




    // find product by age-range
    // must pass the "suitableAge" as variable name
    @PostMapping("/findByAgeRange")
    public List<Product> findByAgeRange(String suitableAge){
        System.out.println("found the same age-range products");
        List<Product> productList = productsService.findProductsByAge(suitableAge);
        return productList;
    }



    // find product by material
    // must pass the "productMaterial" as variable name
    @PostMapping("/findByMaterial")
    public List<Product> findByMaterial(String productMaterial){
        System.out.println("found the same material products");
        List<Product> productList = productsService.findProductsByMaterial(productMaterial);
        return productList;
    }

}
