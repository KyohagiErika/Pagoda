package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Guest {

    public static ArrayList<HashMap> login(Connection connection, String username, String pass) {
        ArrayList<HashMap> returnHashArray = new ArrayList<HashMap>();
        String query = "SELECT * FROM guest WHERE username='"+username+"' AND pass='"+pass+"'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HashMap<String, String> returnHashArrayItem = new HashMap<String, String>();
                returnHashArrayItem.put("firstName", rs.getString("firstName"));
                returnHashArrayItem.put("lastName",rs.getString("last_name"));
                returnHashArray.add(returnHashArrayItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnHashArray;
    }

    public static void register(Connection connection, HashMap<String,String> userData) {
        String query = "INSERT INTO guest VALUES(" +
                "'"+userData.get("username")+"'," +
                "'"+userData.get("pass")+"'," +
                "'"+userData.get("lastName")+"'," +
                "'"+userData.get("firstName")+"'," +
                "'"+userData.get("phoneNumber")+"'," +
                "'"+userData.get("email")+"'," +
                "'"+userData.get("cccd")+"'," +
                "'"+userData.get("reg_day")+"')";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
