package com.viewmodel.application;

import com.processor.applicaion.ConsoleMenu;
import com.processor.applicaion.Option;

import java.util.Scanner;

public class PagodaAppMenu extends ConsoleMenu {
    public static final String PAGODA_MENU_DEFAULT_INPUT_MESSAGE = "Input your choice";

    @Override
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
        options[optionIndex-1].forward();
    }

    public PagodaAppMenu(PagodaApp application, String title, Option[] options) {
        super(application, title, options);
    }

}
