package com.hw.service.category;

import com.hw.model.Category;
import com.hw.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Set<Category> findAllMainCategories() {
        return categoryRepository.findAllMainCategories();
    }

    @Override
    public Category addSubCategory(Long id, Long subId) {
        Category category = categoryRepository.findOne(id);
        Category subCategory = categoryRepository.findOne(subId);
        subCategory.setSuperCategory(category);
        categoryRepository.save(subCategory);
        return category;
    }

    @Override
    public Category createSubCategory(Long id, Category subCategory) {
        Category category = categoryRepository.findOne(id);
        subCategory.setSuperCategory(category);
        categoryRepository.save(subCategory);
        return category;
    }

    @Override
    public Category setSuperCategory(Long id, Long superId) {
        Category category = categoryRepository.findOne(id);
        Category superCategory = categoryRepository.findOne(superId);
        category.setSuperCategory(superCategory);
        return categoryRepository.save(category);
    }

    @Override
    public Category createSuperCategory(Long id, Category superCategory) {
        Category category = categoryRepository.findOne(id);
        category.setSuperCategory(superCategory);
        return categoryRepository.save(category);
    }

    @Override
    public Category alterToMain(Long id) {
        Category category = categoryRepository.findOne(id);
        category.setSuperCategory(null);
        return categoryRepository.save(category);
    }
}
