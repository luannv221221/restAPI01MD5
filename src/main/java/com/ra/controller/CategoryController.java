package com.ra.controller;

import com.ra.model.dto.reponse.CategoryResponse;
import com.ra.model.entity.Category;
import com.ra.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/v1/api/search")
    public ResponseEntity<Page<CategoryResponse>> search(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(defaultValue = "5",name = "limit") int limit,
            @RequestParam(defaultValue = "0",name = "page") int page,
            @RequestParam(defaultValue = "id",name = "sort") String sort,
            @RequestParam(defaultValue = "asc",name = "order") String order){
        Pageable pageable;
        if(order.equals("desc")) {
            pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        }
        Page<CategoryResponse> categoryResponses = categoryService.searchByName(pageable,keyword);
        return new ResponseEntity<>(categoryResponses,HttpStatus.OK);

    }
    @GetMapping("/v1/api/cate")
    public ResponseEntity<Page<CategoryResponse>> findAll(
            @RequestParam(defaultValue = "5",name = "limit") int limit,
            @RequestParam(defaultValue = "0",name = "page") int noPage,
            @RequestParam(defaultValue = "id",name = "sort") String sort,
            @RequestParam(defaultValue = "asc",name = "order") String order){
        Pageable pageable;
        if(order.equals("desc")) {
            pageable = PageRequest.of(noPage, limit, Sort.by(sort).descending());
        } else {
            pageable = PageRequest.of(noPage, limit, Sort.by(sort).ascending());
        }

        Page<CategoryResponse> categoryResponses = categoryService.getAll(pageable);
        return new ResponseEntity<>(categoryResponses,HttpStatus.OK);

    }
    @GetMapping("/v1/api/category")
    public ResponseEntity<?> getAll(  @RequestParam(defaultValue = "5",name = "limit") int limit,
                                      @RequestParam(defaultValue = "0",name = "page") int noPage){
        Pageable pageable = PageRequest.of(noPage,limit);
        Page<Category> categoryPage = categoryService.getAllPaginate(pageable);
        return new ResponseEntity<>(categoryPage,HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<Map<String,Object>> categories(
            @RequestParam(defaultValue = "5",name = "limit") int limit,
            @RequestParam(defaultValue = "0",name = "page") int noPage
            ){
        Pageable pageable = PageRequest.of(noPage,limit);
        Page<Category> categoryPage = categoryService.getPaginate(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("categories",categoryPage.getContent());
        data.put("total",categoryPage.getSize());
        data.put("totalElement",categoryPage.getTotalElements());
        data.put("totalPage",categoryPage.getTotalPages());
        return new ResponseEntity<>(data,HttpStatus.OK);

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
