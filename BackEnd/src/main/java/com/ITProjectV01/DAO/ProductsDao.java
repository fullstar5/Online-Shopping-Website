package com.ITProjectV01.DAO;


import com.ITProjectV01.DataObject.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// Products 持久层
public interface ProductsDao extends JpaRepository<Product,Integer> {


    @Query(nativeQuery = true, value = "SELECT * FROM products p WHERE suitable_age LIKE %?1%")
    List<Product> findByAge(String suitableAge);



    @Query(nativeQuery = true, value = "SELECT * FROM products p WHERE product_material LIKE %?1%")
    List<Product> findByMaterial(String productMaterial);

}
