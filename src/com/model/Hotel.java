package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Hotel extends MysqlFetch {

    public static String HotelIDGenerator() {
        return "JSOQ92HAKW";
    }

    /**
     * get connection
     * @param connection
     */
    public Hotel(Connection connection) {
        super(connection);
    }


    /**
     * Login account or see information of the hotel
     * @param requirement username and pass to login the hotel account / hotelID to see information of the hotel
     * @return ArrayList of hotel information HashMap
     */
    @Override
    public ArrayList<HashMap<String, String>> fromDatabase( HashMap<String, String> requirement) throws SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query;

        //login the hotel account with username and pass
        if (requirement.containsKey("username") && requirement.containsKey("pass"))
            query = "SELECT * FROM hotel WHERE username='"+requirement.get("username")+"' AND pass='"+requirement.get("pass")+"'";

            //see information of the hotel with hotelID
        else {
            query = "SELECT * FROM hotel WHERE hotelID='"+requirement.get("hotelID")+"'";
        }

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HashMap<String, String> returnHashArrayItem = new HashMap<>();
                returnHashArrayItem.put("lastName",rs.getString("last_name"));
                returnHashArrayItem.put("firstName", rs.getString("first_name"));
                returnHashArrayItem.put("phoneNumber",rs.getString("phone_number"));
                returnHashArrayItem.put("email",rs.getString("email"));
                returnHashArrayItem.put("cccd", rs.getString("cccd"));
                returnHashArrayItem.put("name",rs.getString("name"));
                returnHashArrayItem.put("description",rs.getString("description"));
                returnHashArrayItem.put("starRank",rs.getString("star_rank"));
                returnHashArrayItem.put("image",rs.getString("image"));
                returnHashArrayItem.put("location",rs.getString("location"));
                returnHashArrayItem.put("regDay",rs.getString("reg_day"));

                returnHashArray.add(returnHashArrayItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnHashArray;
    }




    /**
     * register  the hotel account
     * @param dataMap information HashMap of hotel
     */
    @Override
    public void toDatabase(HashMap<String,String> dataMap) throws SQLException {
        String query = "INSERT INTO hotel VALUES(" +
                "'"+dataMap.get("username")+"'," +
                "'"+dataMap.get("pass")+"'," +
                "'"+dataMap.get("lastName")+"'," +
                "'"+dataMap.get("firstName")+"'," +
                "'"+dataMap.get("phoneNumber")+"'," +
                "'"+dataMap.get("email")+"'," +
                "'"+dataMap.get("cccd")+"'," +
                "'"+dataMap.get("hotelID")+"'," +
                "'"+dataMap.get("name")+"'," +
                "'"+dataMap.get("description")+"'," +
                "'"+dataMap.get("starRank")+"' ," +
                "'"+dataMap.get("image")+"'," +
                "'"+dataMap.get("location")+"'," +
                "'"+dataMap.get("regDay")+"')";
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
        String query = "UPDATE hotel SET " +
                "last_name='"+dataMap.get("lastName")+"'," +
                "first_name='"+dataMap.get("firstName")+"'," +
                "phone_number='"+dataMap.get("phoneNumber")+"'," +
                "email='"+dataMap.get("email")+"'," +
                "cccd='"+dataMap.get("cccd")+"'," +
                "name='"+dataMap.get("name")+"'," +
                "description='"+dataMap.get("description")+"'," +
                "star_rank='"+dataMap.get("starRank")+"')," +
                "image='"+dataMap.get("image")+"'," +
                "location='"+dataMap.get("location")+"'," +

                "WHERE hotel_id='"+requirement.get("hotelID")+"'";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    /**
     * delete the hotel account
     * @param requirement username of hotel account
     */
    @Override
    public void deleteRecord(HashMap<String, String> requirement) {
        String query ="DELETE FROM hotel " +
                "WHERE hotel_id='"+requirement.get("username")+"'";
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
        String query = "UPDATE hotel SET " +
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
     * search hotels with location or having star bigger than starRank or both
     * @param requirement location or starRank of hotel or both
     * @return ArrayList hotels fitted with @param
     */
    public ArrayList<HashMap<String, String>> searchHotel(HashMap<String, String> requirement) {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String condition ;

        String condition1 = "location='" + requirement.get("location") + "'";
        String condition2 = "starRank>'" + requirement.get("star_rank") + "'";


        if (requirement.containsKey("location") && requirement.containsKey("star_rank")) {
            condition = condition1 + "AND" + condition2 ;
        } else if (requirement.containsKey("location")) {
            condition = condition1;
        } else {
            condition = condition2;
        }


        String query = "SELECT hotel_id,name,description,star_rank,image,location FROM hotel " +
                "WHERE " + condition + "ORDER BY star_rank DESC";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HashMap<String, String> returnHashArrayItem = new HashMap<>();
                returnHashArrayItem.put("hotelID",rs.getString("hotel_id"));
                returnHashArrayItem.put("name",rs.getString("name"));
                returnHashArrayItem.put("description",rs.getString("description"));
                returnHashArrayItem.put("starRank",rs.getString("star_rank"));
                returnHashArrayItem.put("image",rs.getString("image"));
                returnHashArrayItem.put("location",rs.getString("location"));
                returnHashArray.add(returnHashArrayItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnHashArray;
    }

    /**
     * check the hotel username register existed or not
     * @param requirement hotel username register
     * @return true/false
     */
    public boolean checkNotExistedUsername(HashMap<String, String> requirement) {
        String query="SELECT username FROM hotel";
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
