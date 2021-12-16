package com.example.demo.Service.concretes;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService  implements IProductService {
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

    @Override
    public Product findByProductId() {
        return null;
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
    @Transactional
    public  void  updateProduct(Long productId,
                                 String name,
                                 Integer price){
        Product product =productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product with id" + productId + "does not exist"));
        if (name !=null &&
            name.length() >0&&
            Objects.equals(product.getName(), name)){
            product.setName(name);

        }
        if (price !=null &&
            Objects.equals(product.getPrice() , price)){
            product.setPrice(price);
        }



    }

    }




