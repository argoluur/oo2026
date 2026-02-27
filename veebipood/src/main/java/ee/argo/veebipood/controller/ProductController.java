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

    // 1xx harva informatiivne
    // 2xx onnestuv
    // 3xx harva redirect
    // 4xx client error front end
    // 5xx server error
    @Autowired
    private ProductRepository productRepository; //dependency injection, ei võta ressurssi


    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getOneProduct(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);  //kustutab
        return productRepository.findAll();  //uuendatud seis
    }

    @PostMapping("products") //lisamine
    public List<Product> addProduct(@RequestBody Product product){
        if (product.getId()!=null){
            throw new RuntimeException("Cannot add without ID");
        }
        productRepository.save(product);  //salvestab
        return productRepository.findAll();  //uuendatud seis
    }

    @PutMapping("products") //muutmine
    public List<Product> editProduct(@RequestBody Product product){
        if (product.getId()==null){
            throw new RuntimeException("Cannot edit without ID");
        }
        if (productRepository.existsById(product.getId())){
            throw new RuntimeException("Product ID does not exist");
        }
        productRepository.save(product);  //salvestab
        return productRepository.findAll();  //uuendatud seis
    }
}
