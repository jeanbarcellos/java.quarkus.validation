package com.jeanbarcellos.core.utils;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;

/**
 * Utiliário para Manipulação do DI Container
 *
 * @author Jean Silva de Barcellos
 */
public class ContainerUtils {

    private ContainerUtils() {
    }

    public static <U extends Object> U get(Class<U> subtype) {
        Instance<U> instance = CDI.current().select(subtype);

        return instance.get();
    }

    // var container = CDI.current();
    // BeanManager beanManager = container.getBeanManager();

    // Bean<?> bean =
    // beanManager.getBeans(javax.validation.Validator.class).iterator().next();

    // CreationalContext<?> creationalContext =
    // beanManager.createCreationalContext(bean);

    // return (javax.validation.Validator) beanManager.getReference(bean,
    // bean.getBeanClass(), creationalContext);

    // Instance<javax.validation.Validator> instance =
    // container.select(javax.validation.Validator.class);

    // public UserService getUserService() {
    // BeanManager beanManager = CDI.current().getBeanManager();
    // Bean<?> bean = beanManager.getBeans("userService").iterator().next();
    // final CreationalContext<?> creationalContext =
    // beanManager.createCreationalContext(bean);
    // return (UserService) beanManager.getReference(bean, bean.getBeanClass(),
    // creationalContext);
    // }

}
