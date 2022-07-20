package com.jeanbarcellos.core;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class RepositoryBase<TEntity, TId>
        implements PanacheRepositoryBase<TEntity, TId> {

    public Long countById(TId id) {
        return this.count("id", id);
    }

    public boolean existsById(TId id) {
        return this.countById(id) > 0;
    }
}
