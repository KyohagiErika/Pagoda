package com.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class MysqlFetch {
    protected Connection connection;

    public abstract void toDatabase(HashMap<String, String> dataMap) throws SQLException;

    public abstract ArrayList<HashMap<String, String>> fromDatabase(HashMap<String, String> requirement) throws SQLException;

    public abstract void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException;

    public abstract  void deleteRecord(HashMap<String, String> requirement) throws SQLException;

    /**
     *
     * @param connection
     */
    public MysqlFetch(Connection connection) {
        this.connection = connection;
    }
}
