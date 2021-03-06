/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.listeners;

import org.jpatterns.core.ValidationErrorLevel;
import org.jpatterns.gof.behavioral.ObserverPattern;
import org.mockito.mock.MockCreationSettings;

/**
 * Notified when mock object is created.
 * For more information on listeners see {@link org.mockito.MockitoFramework#addListener(MockitoListener)}.
 */
@ObserverPattern.Observer(validationErrorLevel = ValidationErrorLevel.ERROR)
public interface MockCreationListener extends MockitoListener {

    /**
     * Mock object was just created.
     *
     * @param mock created mock object
     * @param settings the settings used for creation
     */
    void onMockCreated(Object mock, MockCreationSettings settings);
}
