/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.handler;

import static org.mockito.internal.exceptions.Reporter.invocationListenerThrewException;

import java.util.List;

import org.jpatterns.core.ValidationErrorLevel;
import org.jpatterns.gof.behavioral.ChainOfResponsibilityPattern;
import org.jpatterns.gof.creational.FactoryMethodPattern;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MockHandler;
import org.mockito.listeners.InvocationListener;
import org.mockito.mock.MockCreationSettings;

/**
 * Handler, that call all listeners wanted for this mock, before delegating it
 * to the parameterized handler.
 */
@ChainOfResponsibilityPattern.ConcreteHandler(validationErrorLevel = ValidationErrorLevel.ERROR)
@FactoryMethodPattern.ConcreteProduct(validationErrorLevel = ValidationErrorLevel.ERROR)
class InvocationNotifierHandler<T> implements MockHandler<T> {

    private final List<InvocationListener> invocationListeners;
    private final MockHandler<T> mockHandler;

    public InvocationNotifierHandler(MockHandler<T> mockHandler, MockCreationSettings<T> settings) {
        this.mockHandler = mockHandler;
        this.invocationListeners = settings.getInvocationListeners();
    }

    public Object handle(Invocation invocation) throws Throwable {
        try {
            Object returnedValue = mockHandler.handle(invocation);
            notifyMethodCall(invocation, returnedValue);
            return returnedValue;
        } catch (Throwable t){
            notifyMethodCallException(invocation, t);
            throw t;
        }
    }


    private void notifyMethodCall(Invocation invocation, Object returnValue) {
        for (InvocationListener listener : invocationListeners) {
            try {
                listener.reportInvocation(new NotifiedMethodInvocationReport(invocation, returnValue));
            } catch(Throwable listenerThrowable) {
                throw invocationListenerThrewException(listener, listenerThrowable);
            }
        }
    }

    private void notifyMethodCallException(Invocation invocation, Throwable exception) {
        for (InvocationListener listener : invocationListeners) {
            try {
                listener.reportInvocation(new NotifiedMethodInvocationReport(invocation, exception));
            } catch(Throwable listenerThrowable) {
                throw invocationListenerThrewException(listener, listenerThrowable);
            }
        }
    }

    public MockCreationSettings<T> getMockSettings() {
        return mockHandler.getMockSettings();
    }

    public InvocationContainer getInvocationContainer() {
        return mockHandler.getInvocationContainer();
    }

}
