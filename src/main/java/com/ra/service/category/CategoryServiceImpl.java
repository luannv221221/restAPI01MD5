package com.ra.service.category;

import com.ra.model.dto.reponse.CategoryResponse;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<CategoryResponse> getAll(Pageable pageable) {
        // phan trang theo entity
        Page<Category> list = categoryRepository.findAll(pageable);
        // convert tu Page Entity => Page DTO
        return list.map(category -> new CategoryResponse(category.getId(),category.getCategoryName(),category.getStatus()));
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> getPaginate(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> getAllPaginate(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<CategoryResponse> searchByName(Pageable pageable,String name) {
        Page<Category> categoryPage = categoryRepository.findAllByCategoryNameContainingIgnoreCase(pageable,name);
        return categoryPage.map(CategoryResponse::new);

    }




}
