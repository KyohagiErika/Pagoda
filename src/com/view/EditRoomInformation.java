package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class EditRoomInformation extends ConsolePage {
    public EditRoomInformation(PagodaApp application) {
        super(application, "Edit Room Information here");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Edit Room Information here");
        application.pause();
    }
}
