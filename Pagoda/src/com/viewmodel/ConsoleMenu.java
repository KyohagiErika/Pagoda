package com.viewmodel;

import java.util.Scanner;

/**
 * You can create a menu that works on console application.
 */
public class ConsoleMenu extends Menu {

    public void display() {
        System.out.println(title);
        int i = 1;
        for (Option option : options) {
            System.out.println((i++)+". "+option.getTitle());
        }
    }

    /**
     * Get user's input and forward the corresponding option.
     * @param inputMsg The display message to notice to user to input.
     */
    public void forwardUser(String inputMsg) {
        Scanner reader = new Scanner(System.in);
        int optionIndex;
        do {
            optionIndex = 0;
            if (inputMsg.compareTo("") != 0) {
                System.out.print(inputMsg + ": ");
            }
            if (reader.hasNextInt()) {
                optionIndex = reader.nextInt();
            }
            reader.nextLine();
        } while (!(optionIndex > 0 && optionIndex <= options.length));
        options[optionIndex-1].foward();
    }

    public ConsoleMenu(String title, Option[] options) {
        super(title,options);
    }
}
