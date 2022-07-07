package com.jeanbarcellos.demo.domain;

import java.util.List;
import java.util.UUID;

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
public class Post {

    private UUID id;

    private Category category;

    private String title;

    private String text;

    private Person author;

    private List<Comment> comments;

}
