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

}
