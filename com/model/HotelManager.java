package com.model;

import com.processor.applicaion.Serialization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class HotelManager extends MysqlFetch {
    public static String HotelIDGenerator() {
        return Serialization.getRandomIDString(10);
    }

    /**
     *
     * @param connection
     */
    public HotelManager(Connection connection) {
        super(connection);
    }


    /**
     * Login account or see information of the hotel
     * @param requirement username and pass to login the hotel account
     * @return ArrayList of hotel information
     */
    @Override
    public ArrayList<HashMap<String, String>> fromDatabase( HashMap<String, String> requirement) throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query = "SELECT * FROM hotel WHERE username='"+requirement.get("username")+"'" +
                "AND pass='"+requirement.get("pass")+"'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("username", rs.getString("username"));
            returnHashArrayItem.put("pass", rs.getString("pass"));
            returnHashArrayItem.put("lastName", rs.getString("last_name"));
            returnHashArrayItem.put("firstName", rs.getString("first_name"));
            returnHashArrayItem.put("phoneNumber", rs.getString("phone_number"));
            returnHashArrayItem.put("email", rs.getString("email"));
            returnHashArrayItem.put("cccd", rs.getString("cccd"));
            returnHashArrayItem.put("hotelID", rs.getString("hotel_id"));
            returnHashArrayItem.put("name", rs.getString("name"));
            returnHashArrayItem.put("description", rs.getString("description"));
            returnHashArrayItem.put("starRank", rs.getString("star_rank"));
            returnHashArrayItem.put("image", rs.getString("image"));
            returnHashArrayItem.put("location", rs.getString("location"));
            returnHashArrayItem.put("regDay", rs.getString("reg_day"));

            returnHashArray.add(returnHashArrayItem);
        }

        return returnHashArray;
    }


    /**
     * Register the hotel account
     * @param dataMap information of hotel
     */
    @Override
    public void toDatabase(HashMap<String,String> dataMap) throws  SQLException {
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
                "'"+dataMap.get("starRank")+"'," +
                "'"+dataMap.get("image")+"'," +
                "'"+dataMap.get("location")+"'," +
                "'"+dataMap.get("regDay")+"')";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);


    }



    /**
     * Update the information of the hotel
     * @param requirement hotelID of hotel
     * @param dataMap new information of hotel
     */
    @Override
    public void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {

        String condition1 = "last_name='"+dataMap.get("lastName")+"'";
        String condition2 = "first_name='"+dataMap.get("firstName")+"'";
        String condition3 = "phone_number='"+dataMap.get("phoneNumber")+"'";
        String condition4 = "email='"+dataMap.get("email")+"'";
        String condition5 = "cccd='"+dataMap.get("cccd")+"'";
        String condition6 = "name='"+dataMap.get("name")+"'";
        String condition7 = "description='"+dataMap.get("description")+"'";
        String condition8 = "star_rank='"+dataMap.get("starRank")+"'";
        String condition9 = "image='"+dataMap.get("image")+"'";
        String condition10 = "location='"+dataMap.get("location")+"'";

        String condition0 = condition1 + "," + condition2 + "," + condition3 + "," +
                condition4 + "," + condition5 + "," + condition6 + "," +
                condition7 + "," + condition8 + "," + condition9 + "," +
                condition10;

        String condition = "";
        if (dataMap.containsKey("lastName") && dataMap.containsKey("firstName") &&
                dataMap.containsKey("phoneNumber") && dataMap.containsKey("email") &&
                dataMap.containsKey("cccd") && dataMap.containsKey("name") &&
                dataMap.containsKey("description") && dataMap.containsKey("starRank") &&
                dataMap.containsKey("image") && dataMap.containsKey("location") ) {
            condition = condition0 ;
        } else if (dataMap.containsKey("lastName")) {
            condition = condition1;
        } else if (dataMap.containsKey("firstName")) {
            condition = condition2;
        } else if (dataMap.containsKey("phoneNumber")) {
            condition = condition3;
        } else if (dataMap.containsKey("email")) {
            condition = condition4;
        } else if (dataMap.containsKey("cccd")) {
            condition = condition5;
        } else if (dataMap.containsKey("name")) {
            condition = condition6;
        } else if (dataMap.containsKey("description")) {
            condition = condition7;
        } else if (dataMap.containsKey("starRank")) {
            condition = condition8;
        } else if (dataMap.containsKey("image")) {
            condition = condition9;
        } else if (dataMap.containsKey("location")) {
            condition = condition10;
        }


        String query = "UPDATE hotel SET " + condition +

                " WHERE hotel_id='"+requirement.get("hotelID")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }



    /**
     * Delete the hotel account
     * @param requirement username of hotel account
     */
    @Override
    public void deleteRecord(HashMap<String, String> requirement) throws  SQLException {
        String query ="DELETE FROM hotel " +
                "WHERE hotel_id='"+requirement.get("hotelID")+"'";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    /**
     * Change the hotel account password
     * @param requirement username and pass
     * @param dataMap new pass
     */
    public void changePass(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query = "UPDATE hotel SET " +
                "pass='"+dataMap.get("pass")+"'" +
                " WHERE username='"+requirement.get("username")+"' AND pass='"+requirement.get("pass")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }


    /**
     * Search hotels with location/name
     * @param requirement location/name
     * @return ArrayList hotels fitted with @param
     */
    public ArrayList<HashMap<String, String>> searchHotel(HashMap<String, String> requirement) throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        ArrayList<HashMap<String, String>> allHotel = browseAllHotels();
        if (requirement.containsKey("location")) {
            allHotel.forEach((hotel) -> {
                if (hotel.get("location").toLowerCase().contains(requirement.get("location").toLowerCase(Locale.ROOT))) {
                    returnHashArray.add(hotel);
                }
            });
        } else {
            allHotel.forEach((hotel) -> {
                if (hotel.get("name").toLowerCase().contains(requirement.get("name").toLowerCase(Locale.ROOT))) {
                    returnHashArray.add(hotel);
                }
            });
        }
        return returnHashArray;
    }

    /**
     * Check the hotel username register existed or not
     * @param requirement username register
     * @return
     */
    public boolean checkNotExistedUsername(HashMap<String, String> requirement) throws  SQLException {
        String query="SELECT username FROM hotel";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            if (requirement.get("username") == rs.getString("username")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Browse All Hotels
     * @return ArrayList hotels
     * @throws SQLException
     */
    public ArrayList<HashMap<String, String>> browseAllHotels () throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();

        String query = "SELECT hotel_id, name, description, star_rank, image, location FROM hotel";
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
        return returnHashArray;
    }

    public ArrayList<HashMap<String, String>> viewHotelsWithStarRankBiggerThanOrEqual4 () throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();

        String query = "SELECT hotel_id, name, description, star_rank, image, location FROM hotel " +
                "WHERE star_rank >= 4 ORDER BY star_rank DESC ";
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
        return returnHashArray;
    }
}
