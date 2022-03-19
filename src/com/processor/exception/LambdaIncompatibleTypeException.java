package com.processor.exception;

/**
 * This exception provides you an ability to manipulate the error of lambda expression
 */
public class LambdaIncompatibleTypeException extends Exception {

    public LambdaIncompatibleTypeException(String requireTypeName, String actualTypeName) {
        super("Incompatible lambda expression type: require "+requireTypeName+"; receive "+actualTypeName);
    }

}
