package com.mytectra.springboot.playground.orm.o2o;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="departments" , path ="departments")
public interface DepartmentRest extends CrudRepository<Department, Integer> {

}
