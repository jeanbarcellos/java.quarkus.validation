package com.jeanbarcellos.demo.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.jeanbarcellos.core.RepositoryBase;
import com.jeanbarcellos.demo.domain.Category;

@ApplicationScoped
public class CategoryRepository extends RepositoryBase<Category, UUID> {

}
