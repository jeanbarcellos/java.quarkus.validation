package com.jeanbarcellos.demo.dtos;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jeanbarcellos.demo.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private UUID id;
    private String name;
    private String description;

    public static CategoryResponse of(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static List<CategoryResponse> of(List<Category> categories) {
        return categories.stream().map(CategoryResponse::of).collect(Collectors.toList());
    }

}
