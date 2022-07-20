package com.jeanbarcellos.demo.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.jeanbarcellos.demo.domain.Type;

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
public class TypeResponse {

    private Integer id;
    private String name;

    public static TypeResponse of(Type type) {
        return TypeResponse
                .builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }

    public static List<TypeResponse> of(List<Type> types) {
        return types.stream().map(TypeResponse::of).collect(Collectors.toList());
    }

}
