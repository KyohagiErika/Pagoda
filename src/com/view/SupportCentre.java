package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SupportCentre extends ConsolePage {
    public SupportCentre(PagodaApp application) {
        super(application, "Support Centre");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        try {
            Scanner sc = new Scanner(new FileInputStream("QnA.txt"), StandardCharsets.UTF_8);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
    }
}
