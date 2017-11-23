package com.hw.util.resource.links;

import com.hw.controller.api.admin.category.CategoryController;
import com.hw.exception.UserNotFoundException;
import com.hw.model.Category;
import com.hw.model.Role;
import com.hw.model.User;
import com.hw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CategoryLinks implements HypermediaLinks<Category> {

    @Override
    public List<Link> getLinks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Link> links = new ArrayList<>();
        User currentUser = null;

        for (Role role : currentUser.getRoles()) {
            if (role.getName().equals("USER_ROLE")) {
                links.add(linkTo(methodOn(CategoryController.class)
                        .getMainCategories( null))
                        .withRel("faaaakee"));
            }
        }
        return links;
    }

    @Override
    public List<Link> getUsersLinks() {
//        List<Link> links = new ArrayList<>();
//        links.add(linkTo(methodOn(CategoryController.class)
//                .setSubCategory(category.getId(), null, null))
//                .withRel("setSubCategory"));
//        links.add(linkTo(methodOn(CategoryController.class)
//                .createSubCategory(category.getId(),null, null))
//                .withRel("createSubCategory"));
//        links.add(linkTo(methodOn(CategoryController.class)
//                .setSuperCategory(category.getId(), null, null))
//                .withRel("setSuperCategory"));
//        links.add(linkTo(methodOn(CategoryController.class)
//                .createSuperCategory(category.getId(), null, null))
//                .withRel("createSuperCategory"));
//        links.add(linkTo(methodOn(CategoryController.class)
//                .alterToMain(category.getId(), null))
//                .withRel("alterToMain"));
//        links.add(linkTo(methodOn(CategoryController.class)
//                .createType(category.getId(), null, null))
//                .withRel("createType"));
//        links.add(linkTo(methodOn(CategoryController.class)
//                .setType(category.getId(), null, null))
//                .withRel("setType"));
        return null;
    }

    @Override
    public List<Link> getAdminsLinks() {
        return null;
    }

}
