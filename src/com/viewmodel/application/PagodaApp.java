package com.viewmodel.application;

import com.processor.applicaion.ConsoleApplication;
import com.view.Home;
import com.viewmodel.exception.PagodaAppError;
import com.viewmodel.exception.PagodaAppException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Pagoda Application
 */
public class PagodaApp extends ConsoleApplication {
    private Connection connection;

    /**
     * Get database connection.
     * @return Database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void start() {
        Home home = new Home(this);
        home.display();
        home.forwardUser(PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
    }

    /**
     * The finalization process
     * @throws PagodaAppException
     */
    private void finalizing() throws PagodaAppException {
        try {
            connection.close();
        } catch (SQLException sqle) {
            throw new PagodaAppException(PagodaAppError.SQL_CONNECTION_ERROR);
        }
    }

    @Override
    public void close() {
        try {
            finalizing();
        } catch (PagodaAppException e) {
            System.out.println(DEFAULT_ERROR_MESSAGE);
            pause();
            writeLog(e);
        }
        super.close();
    }

    /**
     * Create Pagoda Application.
     * @throws PagodaAppException
     */
    public PagodaApp() throws PagodaAppException, IOException {
        super("Pagoda App","error.txt",1,0,0);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pagoda","root","Lethanhlong03");
        } catch (ClassNotFoundException cnfe) {
            throw new PagodaAppException(PagodaAppError.JDBC_DRIVER_CLASS_NOT_FOUND);
        } catch (SQLException sqle) {
            throw new PagodaAppException(PagodaAppError.SQL_CONNECTION_ERROR);
        }
    }

}
