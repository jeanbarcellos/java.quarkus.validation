package com.jeanbarcellos.demo.services;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jeanbarcellos.demo.dtos.CategoryRequest;
import com.jeanbarcellos.demo.dtos.CategoryResponse;
import com.jeanbarcellos.demo.repositories.CategoryRepository;

@ApplicationScoped
public class CategoryService {

    @Inject
    protected CategoryRepository repository;

    public List<CategoryResponse> list() {
        return CategoryResponse.of(this.repository.listAll());
    }

    public CategoryResponse insert(CategoryRequest request) {
        request.validate();

        return CategoryResponse
                .builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public boolean exists(UUID id) {
        return this.repository.existsById(id);
    }
}
