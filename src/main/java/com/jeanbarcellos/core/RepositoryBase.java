package com.jeanbarcellos.core;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class RepositoryBase<TEntity, TId>
        implements PanacheRepositoryBase<TEntity, TId> {

}
