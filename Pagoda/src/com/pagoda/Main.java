package com.pagoda;

import com.viewmodel.application.PagodaApp;
import com.viewmodel.exception.PagodaAppException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            PagodaApp application = new PagodaApp();
            application.start();
        } catch (PagodaAppException e) {
            System.out.println(e.getMessage());
            new Scanner(System.in).nextLine();
            System.exit(e.getExitCode());
        }
    }
}
