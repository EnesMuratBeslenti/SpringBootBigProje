package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.concretes.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/list")
    public List<Product> getProduct() {
        return productService.getProduct();
    }


    @GetMapping(path = "{productId}")
    public ResponseEntity<Product> productFindById(@PathVariable("productId") final Long productId) {
        Product resultId = productService.findByProductId(productId);
        if (resultId != null) {
            return new ResponseEntity<>(resultId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(path = "/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product result = productService.addProduct(product);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(path = "/sil/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") Long productId) {
        Product deleteProductId = productService.findByProductId(productId);
        if (deleteProductId != null) {
            productService.deleteByProductId(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/update/{productId}")
    public void updateProduct(@PathVariable("productId") Long productId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer price) {

    productService.updateProduct(productId, name, price);


    }
}