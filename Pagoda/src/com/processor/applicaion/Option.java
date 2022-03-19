package com.processor.applicaion;

import com.processor.interfaces.LambdaExpression;
import com.processor.exception.LambdaIncompatibleTypeException;

/**
 * This class specify the option you may have in your application.
 */
public class Option {
    private String title;
    private LambdaExpression forwardExpression;

    /**
     * @return The option's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method forward user to another features when user selects the option.
     */
    public void forward() {
        try {
            forwardExpression.express(null);
        } catch (LambdaIncompatibleTypeException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * @param title Title of the option.
     * @param forwardExpression A lambda expression that will affect the forward() method.
     */
    public Option(String title, LambdaExpression forwardExpression) {
        this.title = title;
        this.forwardExpression = forwardExpression;
    }
}
