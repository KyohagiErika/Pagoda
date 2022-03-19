package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class DeleteAccount extends ConsolePage {
    public DeleteAccount(PagodaApp application) {
        super(application, "Delete Account");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Delete Your Account");
        application.pause();
    }
}
