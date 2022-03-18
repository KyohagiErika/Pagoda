package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SubscriptionManager extends MysqlFetch {
    /**
     * @param connection
     */
    public SubscriptionManager(Connection connection) {
        super(connection);
    }

    @Override
    public void toDatabase(HashMap<String, String> dataMap) throws SQLException {
        String query = "INSERT INTO subscription VALUES ('"+dataMap.get("hotelId")+"','"+dataMap.get("guestUsername")+"')";
        connection.createStatement().executeUpdate(query);
    }

    @Override
    public ArrayList<HashMap<String, String>> fromDatabase(HashMap<String, String> requirement) throws SQLException {
        ArrayList<HashMap<String, String>> returnList = new ArrayList<>();
        String query = "SELECT hotel.hotel_id, hotel.name, hotel.description, hotel.star_rank, hotel.image, hotel.location " +
                "FROM subscription INNER JOIN hotel ON subscription.hotel_hotel_id = hotel.hotel_id " +
                "WHERE subscription.guest_username='"+requirement.get("guestUsername")+"'";
        if (requirement.containsKey("hotelId")) {
            query += " AND hotel_hotel_id='"+requirement.get("hotelId")+"'";
        }
        ResultSet rs = connection.createStatement().executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("hotelID",rs.getString("hotel.hotel_id"));
            returnHashArrayItem.put("name",rs.getString("hotel.name"));
            returnHashArrayItem.put("description",rs.getString("hotel.description"));
            returnHashArrayItem.put("starRank",rs.getString("hotel.star_rank"));
            returnHashArrayItem.put("image",rs.getString("hotel.image"));
            returnHashArrayItem.put("location",rs.getString("hotel.location"));
            returnList.add(returnHashArrayItem);
        }
        return returnList;
    }

    @Override
    public void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException {

    }

    @Override
    public void deleteRecord(HashMap<String, String> requirement) throws SQLException {
        String query = "DELETE FROM subscription WHERE guest_username='"+requirement.get("guestUsername")+"' AND hotel_hotel_id='"+requirement.get("hotelId")+"'";
        connection.createStatement().executeUpdate(query);
    }
}
