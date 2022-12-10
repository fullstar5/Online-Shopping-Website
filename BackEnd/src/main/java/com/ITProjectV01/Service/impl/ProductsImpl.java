package com.ITProjectV01.Service.impl;


import com.ITProjectV01.DAO.ProductsDao;
import com.ITProjectV01.DataObject.Product;
import com.ITProjectV01.Service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductsImpl implements ProductsService{

    @Autowired
    private ProductsDao productsDao;


    @Override
    public Product findOne(Integer ProductID) {return productsDao.findById(ProductID).get();}

    @Override
    public List<Product> findAll() {return productsDao.findAll();}

    @Override
    public Product save(Product product) {return productsDao.save(product);}

    @Override
    public void deleteProductByID(Integer ProductID) {productsDao.deleteById(ProductID);}



    @Override
    public List<Product> findProductsByAge(String productAge) {
        return productsDao.findByAge(productAge);
    }

    @Override
    public List<Product> findProductsByMaterial(String productMaterial) {
        return productsDao.findByMaterial(productMaterial);
    }
}
