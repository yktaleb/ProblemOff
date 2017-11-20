package com.hw.controller.api.category;

import com.hw.model.Category;
import com.hw.model.Type;
import com.hw.service.category.CategoryService;
import com.hw.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RepositoryRestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    @ResponseBody
    public Resources<PersistentEntityResource> getMainCategories(PersistentEntityResourceAssembler assembler) {
        Set<Category> mainCategories = categoryService.findAllMainCategories();
//        return ResponseEntity
//                .ok(mainCategories.stream()
//                .map(assembler::toFullResource)
//                .collect(Collectors.toList()));
//        return ResponseEntity
//                .ok(mainCategories.stream()
//                    .map(assembler::toFullResource)
//                    .collect(Collectors.toList()));
        return new Resources<PersistentEntityResource>(mainCategories.stream()
                .map(assembler::toFullResource)
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{id}/subCategories/{subId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity addSubCategory(@PathVariable Long id,
                                         @PathVariable Long subId,
                                         PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(categoryService.addSubCategory(id, subId))
        );
    }

    @RequestMapping(value = "/{id}/subCategories", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createSubCategory(@PathVariable Long id,
                                            @RequestBody Category subCategory,
                                            PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(categoryService.createSubCategory(id, subCategory))
        );
    }

    @RequestMapping(value = "/{id}/superCategory/{superId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity setSuperCategory(@PathVariable Long id,
                                           @PathVariable Long superId,
                                           PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(categoryService.setSuperCategory(id, superId))
        );
    }

    @RequestMapping(value = "/{id}/superCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity setSuperCategory(@PathVariable Long id,
                                           @RequestBody Category superCategory,
                                           PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(categoryService.createSuperCategory(id, superCategory))
        );
    }

    @RequestMapping(value = "/{id}/main", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity alterToMain(@PathVariable Long id,
                                      PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(categoryService.alterToMain(id))
        );
    }

    @RequestMapping(value = "/{id}/types", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addType(@PathVariable Long id,
                                  @RequestBody Type type,
                                  PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(categoryService.addType(id, type))
        );
    }

    @RequestMapping(value = "/{id}/types/{typeId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity addType(@PathVariable Long id,
                                  @PathVariable Long typeId,
                                  PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(categoryService.addType(id, typeId))
        );
    }
}
