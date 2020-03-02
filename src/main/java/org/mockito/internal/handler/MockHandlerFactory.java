/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.handler;

import org.jpatterns.core.ValidationErrorLevel;
import org.jpatterns.gof.creational.FactoryMethodPattern;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;

/**
 * by Szczepan Faber, created at: 5/21/12
 */
@FactoryMethodPattern.ConcreteCreator(validationErrorLevel = ValidationErrorLevel.NONE)
public class MockHandlerFactory {

    public static <T> MockHandler<T> createMockHandler(MockCreationSettings<T> settings) {
        MockHandler<T> handler = new MockHandlerImpl<T>(settings);
        MockHandler<T> nullResultGuardian = new NullResultGuardian<T>(handler);
        return new InvocationNotifierHandler<T>(nullResultGuardian, settings);
    }
}
