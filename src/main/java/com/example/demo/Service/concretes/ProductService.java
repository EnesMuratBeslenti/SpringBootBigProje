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

    public Product createProduct(Product product) {
        if (!product.getName().isEmpty() &&
                product.getPrice() > 0) {
            return productRepository.save(product);
        }
        return null;
    }


    public List<Product> getProduct() {
        return productRepository.findAll();
    }

<<<<<<< HEAD:src/main/java/com/example/demo/Service/concretes/ProductService.java
    @Override
    public Product findByProductId() {
        return null;
    }

    public Product findByProductId(Long productId) {
=======
    public Product inquireProduct(Long productId) {
>>>>>>> dabe32c5f04979aad0504c0de663ba7a9c5e1871:src/main/java/com/example/demo/Service/ProductService.java
       Optional<Product> product = productRepository.findById(productId);
        if (!product.isEmpty()) {
            return product.get();
        } else {
            return null;

        }
    }
    public Product deleteProduct(Long productId){
        if (inquireProduct(productId) !=null){
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




