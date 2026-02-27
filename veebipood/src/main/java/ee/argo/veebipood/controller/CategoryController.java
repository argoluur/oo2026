package ee.argo.veebipood.controller;

import ee.argo.veebipood.entity.Category;
import ee.argo.veebipood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository; //dependency injection, ei võta ressurssi


    @GetMapping("categories")
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }

    @DeleteMapping("categories/{id}")
    public List<Category> deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id);  //kustutab
        return categoryRepository.findAll();  //uuendatud seis
    }

    @PostMapping("categories")
    public List<Category> addCategory(@RequestBody Category category){
        categoryRepository.save(category);  //salvestab
        return categoryRepository.findAll();  //uuendatud seis
    }
}
