package com.hw.controller.resourse;

import com.hw.controller.api.admin.category.CategoryController;
import com.hw.model.Category;
import com.hw.model.Role;
import com.hw.model.User;
import com.hw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CategoryResourceProcessor implements ResourceProcessor<Resource<Category>> {

    private String userRole = "USER_ROLE";
    private String adminRole = "ADMIN_ROLE";

    @Override
    public Resource<Category> process(Resource<Category> categoryResource) {
        Category category = categoryResource.getContent();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            User user = (User) authentication.getPrincipal();
            for (Role role : user.getRoles()) {
                if (role.getName().equals(userRole)) {

                } else if (role.getName().equals(adminRole)) {
                    categoryResource.add(linkTo(methodOn(CategoryController.class)
                            .addSubCategory(category.getId(), null, null))
                            .withRel("addSubCategory"));
                    categoryResource.add(linkTo(methodOn(CategoryController.class)
                            .createSubCategory(category.getId(),null, null))
                            .withRel("createSubCategory"));
                    categoryResource.add(linkTo(methodOn(CategoryController.class)
                            .setSuperCategory(category.getId(), null, null))
                            .withRel("setSuperCategory"));
                    categoryResource.add(linkTo(methodOn(CategoryController.class)
                            .createSuperCategory(category.getId(), null, null))
                            .withRel("createSuperCategory"));
                    categoryResource.add(linkTo(methodOn(CategoryController.class)
                            .alterToMain(category.getId(), null))
                            .withRel("alterToMain"));
                    categoryResource.add(linkTo(methodOn(CategoryController.class)
                            .alterToMain(category.getId(), null))
                            .withRel("alterToMain"));
                    categoryResource.add(linkTo(methodOn(CategoryController.class)
                            .addType(category.getId(), null, null))
                            .withRel("addType"));
                    categoryResource.add(linkTo(methodOn(CategoryController.class)
                            .setType(category.getId(), null, null))
                            .withRel("setType"));
                }
            }
        }
        return categoryResource;
    }


}
