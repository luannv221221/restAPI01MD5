package com.ra.service.category;

import com.ra.model.dto.reponse.CategoryResponse;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> getAll() {
        List<Category> list = categoryRepository.findAll();
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        for (Category category : list) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(category.getId());
            categoryResponse.setCategoryName(category.getCategoryName());
            categoryResponse.setStatus(category.getStatus());
            categoryResponse.setProducts(category.getProducts());
            categoryResponseList.add(categoryResponse);
        }

        return categoryResponseList;
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
}
