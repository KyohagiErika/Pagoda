package com.processor.applicaion;

import com.processor.interfaces.LambdaExpression;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.processor.enumeration.ConsoleInputMode;

/**
 * Class for managing console input.
 */
public class ConsoleInput {
    private ConsoleInputMode mode;

    /**
     * Read user input.
     * @param inputMsg The message to display to user.
     * @return User input string.
     */
    public String input(String inputMsg) {
        System.out.print(inputMsg + ": ");
        if (mode == ConsoleInputMode.PASSWORD) {
            return new String(System.console().readPassword("***********"));
        }
        return System.console().readLine();
    }


    /**
     * Read user input with validate. The input process will repeat until user enter a valid input.
     * @param inputMsg The message to display to user.
     * @param validateExpression A validation with the return type of boolean.
     * @return User input string.
     * @throws LambdaIncompatibleTypeException Throw when the type of parameter or return doesn't fit your requirement.
     */
    public String input(String inputMsg, LambdaExpression validateExpression) throws LambdaIncompatibleTypeException {
        while (true) {
            String inputData = input(inputMsg);
            Object validateResult = validateExpression.express(inputData);
            if (validateResult instanceof Boolean) {
                if ((boolean) validateResult) {
                    return inputData;
                }
            } else {
                throw new LambdaIncompatibleTypeException("Boolean",validateResult.getClass().getTypeName());
            }
        }
    }

    /**
     * Create a default console input.
     */
    public ConsoleInput() {
        this.mode = ConsoleInputMode.TEXT;
    }

    /**
     * Create a console input with a specified input mode.
     * @param mode Console input mode.
     */
    public ConsoleInput(ConsoleInputMode mode) {
        this.mode = mode;
    }

}
