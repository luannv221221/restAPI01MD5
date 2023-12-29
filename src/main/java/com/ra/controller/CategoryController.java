package com.ra.controller;

import com.ra.model.dto.reponse.CategoryResponse;
import com.ra.model.entity.Category;
import com.ra.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> categories(){
        List<CategoryResponse> list = categoryService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category categoryNew = categoryService.saveOrUpdate(category);
        return new ResponseEntity<>(categoryNew,HttpStatus.CREATED);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if(category != null){
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>("Nót phao",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody Category category){
        Category categoryUpdate = categoryService.findById(id);
        categoryUpdate.setCategoryName(category.getCategoryName());
        categoryUpdate.setStatus(category.getStatus());
        Category categoryNew = categoryService.saveOrUpdate(categoryUpdate);
        return new ResponseEntity<>(categoryNew,HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
       if(categoryService.findById(id) != null){
           categoryService.delete(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>("NOt Phao",HttpStatus.NOT_FOUND);
    }
}
