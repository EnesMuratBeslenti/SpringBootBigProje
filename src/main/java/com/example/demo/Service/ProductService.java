package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        if (!product.getName().isEmpty() &&
                product.getPrice() > 0) {
            return productRepository.save(product);
        }
        return null;
    }


    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product findByProductId(Long productId) {
       Optional<Product> product = productRepository.findById(productId);
        if (!product.isEmpty()) {
            return product.get();
        } else {
            return null;

        }
    }
    public Product deleteByProductId(Long productId){
        if (findByProductId(productId) !=null){
            productRepository.deleteById(productId);
        }
       return null;
    }

}


