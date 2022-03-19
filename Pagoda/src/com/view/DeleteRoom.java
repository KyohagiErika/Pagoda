package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class DeleteRoom  extends ConsolePage {
    public DeleteRoom(PagodaApp application) {
        super(application, "Delete Room");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Delete Your Room");
        application.pause();
    }
}
