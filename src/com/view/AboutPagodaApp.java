package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AboutPagodaApp extends ConsolePage {
    public AboutPagodaApp(PagodaApp application) {
        super(application, "About Pagoda");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        try {
            Scanner sc = new Scanner(new FileInputStream("about.txt"), StandardCharsets.UTF_8);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
    }
}
