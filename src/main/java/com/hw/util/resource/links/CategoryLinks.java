package com.hw.util.resource.links;

import com.hw.controller.api.admin.category.CategoryController;
import com.hw.exception.UserNotFoundException;
import com.hw.model.Role;
import com.hw.model.User;
import com.hw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CategoryLinks implements HypermediaLinks{

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
}
