package com.ITProjectV01.Service;


import com.ITProjectV01.DataObject.Product;

import java.util.List;


public interface ProductsService {

    Product findOne (Integer ProductID);

    List<Product> findAll ();

    Product save (Product product);

    void deleteProductByID (Integer ProductID);

    List<Product> findProductsByAge (String productAge);

    List<Product> findProductsByMaterial (String productMaterial);


}
