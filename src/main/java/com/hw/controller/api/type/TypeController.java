package com.hw.controller.api.type;

import com.hw.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
@RequestMapping("api/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/{id}/category/{categoryId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity setCategory(@PathVariable Long id,
                                  @PathVariable Long categoryId,
                                  PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(typeService.setCategory(id, categoryId))
        );
    }

}
