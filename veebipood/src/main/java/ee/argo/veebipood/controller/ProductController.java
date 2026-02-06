package ee.argo.veebipood.controller;

import ee.argo.veebipood.entity.Product;
import ee.argo.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    //localhost:8090/products
    // application.properties server.port=8090
//    @GetMapping("products")
//    public String helloworld(){
//        return "Hello World!";
//    }
    @Autowired
    private ProductRepository productRepository; //dependency injection, ei võta ressurssi


    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);  //kustutab
        return productRepository.findAll();  //uuendatud seis
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product){
        productRepository.save(product);  //salvestab
        return productRepository.findAll();  //uuendatud seis
    }
}
