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
}
