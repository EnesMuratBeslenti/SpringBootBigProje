package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private  final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping(path = "/list")
    public List<Product> getProduct(){
        return  productService.getProduct();
    }
    @GetMapping(path = "{productId}")
    public ResponseEntity<Product> productFindById(@PathVariable ("productId") final Long productId){
      return productService.findByProductId(productId);
    }


   @PostMapping(path ="/add" )
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product add = productService.addProduct(product);
        if (add != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

   }
 @DeleteMapping(path ="/sil/{productId}")
        public ResponseEntity<Object> deleteProduct(@PathVariable ("productId")Long productId){

        if (productId !=null) {
            productService.deleteByProductId(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }

}
