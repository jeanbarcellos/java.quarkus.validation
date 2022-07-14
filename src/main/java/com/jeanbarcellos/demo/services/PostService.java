package com.jeanbarcellos.demo.services;

import javax.enterprise.context.ApplicationScoped;

import com.jeanbarcellos.demo.dtos.PostRequest;

@ApplicationScoped
public class PostService {

    public void insert(PostRequest request) {
        request.validate();

    }

}
