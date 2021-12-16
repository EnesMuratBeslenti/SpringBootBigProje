package com.example.demo.Service;

import com.example.demo.Model.Product;

import java.util.List;

public interface IProductService {
     Product addProduct(Product product);
     List<Product> getProduct();
     Product findByProductId ();
     Product deleteByProductId(Long productId);
    void  updateProduct(Long productId,
                        String name,
                        Integer price);
}
