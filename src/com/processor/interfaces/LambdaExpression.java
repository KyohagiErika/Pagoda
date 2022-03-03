package com.processor.interfaces;

import com.processor.exception.LambdaIncompatibleTypeException;

/**
 * Provide you ability to write lambda expressions.
 */
public interface LambdaExpression {
    /**
     * You can initialize this method by using lambda expression.
     * @param obj Optional parameter for the expression.
     * @return Optional return value for the expression.
     * @throws LambdaIncompatibleTypeException Throw when the type of parameter or return doesn't fit your requirement.
     */
    Object express(Object obj) throws LambdaIncompatibleTypeException;
}
