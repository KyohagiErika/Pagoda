package com.pagoda;

import com.viewmodel.application.PagodaApp;
import com.viewmodel.exception.PagodaAppException;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            PagodaApp application = new PagodaApp();
            application.start();
        } catch (PagodaAppException | IOException e) {
            System.out.println(e.getMessage());
            new Scanner(System.in).nextLine();
            System.exit(0);
        }
    }
}
