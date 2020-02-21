/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.creation.instance;

import org.jpatterns.core.ValidationErrorLevel;
import org.jpatterns.gof.creational.AbstractFactoryPattern;

/**
 * Provides instances of classes.
 * See more information about Mockito plugin {@link org.mockito.plugins.InstantiatorProvider2}
 *
 * @since 2.15.4
 */
@AbstractFactoryPattern.AbstractFactory(validationErrorLevel = ValidationErrorLevel.ERROR)
@AbstractFactoryPattern.AbstractProduct(validationErrorLevel = ValidationErrorLevel.ERROR)
public interface Instantiator {

    /**
     * Creates instance of given class
     *
     * @since 2.15.4
     */
    @AbstractFactoryPattern.FactoryMethod(validationErrorLevel = ValidationErrorLevel.NONE)
    <T> T newInstance(Class<T> cls) throws InstantiationException;

}
