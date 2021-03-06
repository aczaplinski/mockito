/*
 * Copyright (c) 2018 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.session;

import org.jpatterns.core.ValidationErrorLevel;
import org.jpatterns.gof.structural.AdapterPattern;
import org.mockito.plugins.MockitoLogger;
import org.mockito.session.MockitoSessionLogger;

@AdapterPattern.Adapter(validationErrorLevel = ValidationErrorLevel.ERROR)
public class MockitoSessionLoggerAdapter implements MockitoSessionLogger {

    private final MockitoLogger logger;

    public MockitoSessionLoggerAdapter(MockitoLogger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String hint) {
        logger.log(hint);
    }
}
