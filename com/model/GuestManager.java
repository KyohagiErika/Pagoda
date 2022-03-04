package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GuestManager extends MysqlFetch {

    /**
     *
     * @param connection
     */
    public GuestManager(Connection connection) {
        super(connection);
    }


    /**
     * Login account or see information of the guest
     * @param requirement username and pass
     * @return ArrayList of guest information
     */
    @Override
    public ArrayList<HashMap<String, String>> fromDatabase( HashMap<String, String> requirement) throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query = "SELECT * FROM guest WHERE username='"+requirement.get("username")+"' AND pass='"+requirement.get("pass")+"'";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("username", rs.getString("username"));
            returnHashArrayItem.put("pass", rs.getString("pass"));
            returnHashArrayItem.put("lastName", rs.getString("last_name"));
            returnHashArrayItem.put("firstName", rs.getString("firstName"));
            returnHashArrayItem.put("phoneNumber", rs.getString("phone_number"));
            returnHashArrayItem.put("email", rs.getString("email"));
            returnHashArrayItem.put("cccd", rs.getString("cccd"));
            returnHashArrayItem.put("regDay", rs.getString("reg_day"));
            returnHashArray.add(returnHashArrayItem);
        }
        return returnHashArray;
    }


    /**
     * Register the guest account
     * @param dataMap information of guest
     */
    @Override
    public void toDatabase(HashMap<String,String> dataMap) throws  SQLException {
        String query = "INSERT INTO guest VALUES(" +
                "'"+dataMap.get("username")+"'," +
                "'"+dataMap.get("pass")+"'," +
                "'"+dataMap.get("lastName")+"'," +
                "'"+dataMap.get("firstName")+"'," +
                "'"+dataMap.get("phoneNumber")+"'," +
                "'"+dataMap.get("email")+"'," +
                "'"+dataMap.get("cccd")+"'," +
                "'"+dataMap.get("regDay")+"')";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);

    }



    /**
     * Update the information of the Guest
     * @param requirement username
     * @param dataMap new information
     */
    @Override
    public void editCccd(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query = "UPDATE guest SET " +
                "cccd='"+dataMap.get("cccd")+"'" +
                "WHERE username='"+requirement.get("username")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    @Override
    public void editLastName(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query = "UPDATE guest SET " +
                "last_name='"+dataMap.get("lastName")+"'" +
                "WHERE username='"+requirement.get("username")+"'";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    @Override
    public void editFirstName(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query = "UPDATE guest SET " +
                "firstName='"+dataMap.get("firstName")+"'" +
                "WHERE username='"+requirement.get("username")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    @Override
    public void editPhoneNumber(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query = "UPDATE guest SET " +
                "phone_number='"+dataMap.get("phoneNumber")+"'" +
                "WHERE username='"+requirement.get("username")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    @Override
    public void editEmail(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query = "UPDATE guest SET " +
                "email='"+dataMap.get("email")+"'" +
                "WHERE username='"+requirement.get("username")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    @Override
    public void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException {

    }


    /**
     * Delete the guest account
     * @param requirement username
     */
    @Override
    public void deleteRecord(HashMap<String, String> requirement) throws  SQLException {
        String query ="DELETE FROM guest " +
                "WHERE username='"+requirement.get("username")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }


    /**
     * Change the guest account password
     * @param requirement username and pass
     * @param dataMap new pass
     */
    public void changePass(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query ="UPDATE guest SET " +
                "pass='"+dataMap.get("pass")+"'" +
                "WHERE username='"+requirement.get("username")+"' AND pass='"+requirement.get("pass")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    /**
     * Check the guest username register existed or not
     * @param requirement username register
     * @return
     */
    public boolean checkNotExistedUsername(HashMap<String, String> requirement) throws  SQLException {
        String query="SELECT username FROM guest";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            if (requirement.get("username") == rs.getString("username")) {
                return false;
            }
        }
        return true;
    }

}

