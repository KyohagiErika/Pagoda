package com.view;

import com.model.Evaluation;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleMenu;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class FeedBackMenu extends PagodaAppMenu {
    /**
     * @param application The application to which the menu belongs.
     */
    public FeedBackMenu(PagodaApp application) {
        super(application, "Feedback", new Option[] {
                new Option("Review Feedback",(obj -> {
                    new SeeFeedback(application).display(new Evaluation(application.getConnection()));
                    application.clrscr();
                    new FeedBackMenu(application) {{
                        display();
                        forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    }};
                    return null;
                })),

                new Option("Send Feedback",(obj -> {
                    new FeedBack(application).display(new Evaluation(application.getConnection()));
                    application.clrscr();
                    new FeedBackMenu(application) {{
                        display();
                        forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    }};
                    return null;
                })),

                new Option("Delete Feedback",(obj -> {
                    new DeleteFeedback(application).display(new Evaluation(application.getConnection()));
                    application.clrscr();
                    new FeedBackMenu(application) {{
                        display();
                        forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    }};
                    return null;
                })),

                new Option("Back", (obj -> {
                    application.clrscr();
                    new Guest(application) {{
                        display();
                        forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    }};
                    return null;
                }))
        });
    }
}
