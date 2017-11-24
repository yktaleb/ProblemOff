package com.hw.controller.resourse;

import com.hw.model.Category;
import com.hw.util.resource.links.CategoryLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class CategoryResourceProcessor implements ResourceProcessor<Resource<Category>> {

    @Autowired
    private CategoryLinks categoryLinks;

    @Override
    public Resource<Category> process(Resource<Category> categoryResource) {
        Category category = categoryResource.getContent();
        categoryResource.add(categoryLinks.getLinks(category.getId()));
        return categoryResource;
    }
}
