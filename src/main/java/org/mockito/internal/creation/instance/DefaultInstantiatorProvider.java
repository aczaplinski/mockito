/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.creation.instance;

import org.jpatterns.core.ValidationErrorLevel;
import org.jpatterns.gof.creational.AbstractFactoryPattern;
import org.mockito.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider2;

@AbstractFactoryPattern.ConcreteFactory(validationErrorLevel = ValidationErrorLevel.ERROR)
public class DefaultInstantiatorProvider implements InstantiatorProvider2 {

    private final static Instantiator INSTANCE = new ObjenesisInstantiator();

    @AbstractFactoryPattern.FactoryMethod(validationErrorLevel = ValidationErrorLevel.ERROR)
    @Override
    public Instantiator getInstantiator(MockCreationSettings<?> settings) {
        if (settings != null && settings.getConstructorArgs() != null) {
            return new ConstructorInstantiator(settings.getOuterClassInstance() != null, settings.getConstructorArgs());
        } else {
            return INSTANCE;
        }
    }
}
