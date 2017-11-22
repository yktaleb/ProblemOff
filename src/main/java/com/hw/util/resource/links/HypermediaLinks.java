package com.hw.util.resource.links;

import com.hw.exception.UserNotFoundException;
import org.springframework.hateoas.Link;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface HypermediaLinks {
    List<Link> getLinks();
}
