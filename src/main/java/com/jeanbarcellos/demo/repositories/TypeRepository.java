package com.jeanbarcellos.demo.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.jeanbarcellos.core.RepositoryBase;
import com.jeanbarcellos.demo.domain.Type;

@ApplicationScoped
public class TypeRepository extends RepositoryBase<Type, Integer> {

    public List<Type> findByParent(Integer value) {
        return this.list("parent", value);
    }

}
