package com.hw.repository;

import com.hw.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface TypeRepository extends CrudRepository<Type, Long> {

}
