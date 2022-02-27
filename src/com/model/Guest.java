package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Guest extends MysqlFetch {

    /**
     * get connection
     * @param connection
     */
    public Guest(Connection connection) {
        super(connection);
    }


    /**
     * Login account or see information of the guest
     * @param requirement username and pass of guest account
     * @return ArrayList of guest information HashMap
     */
    @Override
    public ArrayList<HashMap<String, String>> fromDatabase( HashMap<String, String> requirement) {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query = "SELECT * FROM guest WHERE username='"+requirement.get("username")+"' AND pass='"+requirement.get("pass")+"'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HashMap<String, String> returnHashArrayItem = new HashMap<>();
                returnHashArrayItem.put("lastName",rs.getString("last_name"));
                returnHashArrayItem.put("firstName", rs.getString("firstName"));
                returnHashArrayItem.put("phoneNumber",rs.getString("phone_number"));
                returnHashArrayItem.put("email",rs.getString("email"));
                returnHashArrayItem.put("cccd", rs.getString("cccd"));
                returnHashArrayItem.put("regDay",rs.getString("reg_day"));
                returnHashArray.add(returnHashArrayItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnHashArray;
    }


    /**
     * register  the guest account
     * @param dataMap information HashMap of guest
     */
    @Override
    public void toDatabase(HashMap<String,String> dataMap) {
        String query = "INSERT INTO guest VALUES(" +
                "'"+dataMap.get("username")+"'," +
                "'"+dataMap.get("pass")+"'," +
                "'"+dataMap.get("lastName")+"'," +
                "'"+dataMap.get("firstName")+"'," +
                "'"+dataMap.get("phoneNumber")+"'," +
                "'"+dataMap.get("email")+"'," +
                "'"+dataMap.get("cccd")+"'," +
                "'"+dataMap.get("reg_day")+"')";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * update the information of the Guest
     * @param requirement username of guest account
     * @param dataMap new information HashMap of guest
     */
    @Override
    public void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) {
        String query = "UPDATE guest SET " +
                "last_name='"+dataMap.get("lastName")+"'," +
                "firstName='"+dataMap.get("firstName")+"'," +
                "phone_number='"+dataMap.get("phoneNumber")+"'," +
                "email='"+dataMap.get("email")+"'," +
                "cccd='"+dataMap.get("cccd")+"'," +
                "WHERE username='"+requirement.get("username")+"'";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * delete the guest account
     * @param requirement username of guest account
     */
    @Override
    public void deleteRecord(HashMap<String, String> requirement) {
        String query ="DELETE FROM guest " +
                "WHERE username='"+requirement.get("username")+"'";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * change the guest account password
     * @param requirement username and pass of guest account
     * @param dataMap new pass of guest account
     */
    public void changePass(HashMap<String, String> requirement, HashMap<String, String> dataMap) {
        String query ="UPDATE guest SET " +
                "pass='"+dataMap.get("pass")+"'," +
                "WHERE username='"+requirement.get("username")+"' AND pass='"+requirement.get("pass")+"'";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * check the guest username register existed or not
     * @param requirement guest username register
     * @return true/false
     */
    public boolean checkNotExistedUsername(HashMap<String, String> requirement) {
        String query="SELECT username FROM guest";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()) {
                if(requirement.get("username")==rs.getString("username")) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}

