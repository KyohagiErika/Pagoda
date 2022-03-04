package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager extends MysqlFetch{

    /**
     *
     * @param connection
     */
    public RoomManager(Connection connection) {
        super(connection);
    }


    /**
     * Add the new room information
     * @param dataMap roomID,name,roomSize,pricePerNight,capacity,image,hotelID of new room
     */
    @Override
    public void toDatabase(HashMap<String, String> dataMap) throws  SQLException {
        String query = "INSERT INTO room VALUES(" +
                "'"+dataMap.get("roomID")+"'," +
                "'"+dataMap.get("name")+"'," +
                "'"+dataMap.get("roomSize")+"'," +
                "'"+dataMap.get("pricePerNight")+"'," +
                "'"+dataMap.get("capacity")+"'," +
                "'"+dataMap.get("image")+"'," +
                "'"+dataMap.get("hotelID")+"')";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }


    /**
     * Check the information room
     * @param requirement hotelID and roomID of room
     * @return information of room
     */
    @Override
    public ArrayList<HashMap<String, String>> fromDatabase(HashMap<String, String> requirement) throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query = "SELECT * FROM room WHERE room_id='"+requirement.get("roomID")+"'" +
                "AND hotel_hotel_id='"+requirement.get("hotelID")+"'";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("roomID", rs.getString("room_id"));
            returnHashArrayItem.put("name",rs.getString("name"));
            returnHashArrayItem.put("roomSize",rs.getString("room_size"));
            returnHashArrayItem.put("pricePerNight",rs.getString("price_per_night"));
            returnHashArrayItem.put("capacity",rs.getString("capacity"));
            returnHashArrayItem.put("image",rs.getString("image"));
            returnHashArrayItem.put("hotelID",rs.getString("hotel_hotel_id"));
            returnHashArray.add(returnHashArrayItem);
        }
        return returnHashArray;
    }

    @Override
    public void editCccd(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException {

    }

    @Override
    public void editLastName(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException {

    }

    @Override
    public void editFirstName(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException {

    }

    @Override
    public void editPhoneNumber(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException {

    }

    @Override
    public void editEmail(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException {

    }


    /**
     * Update the information of room
     * @param requirement hotelID and roomID of room
     * @param dataMap new information of room
     */
    @Override
    public void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws SQLException {
        String query = "UPDATE room SET " +
                "name='"+dataMap.get("name")+"'," +
                "room_size='"+dataMap.get("roomSize")+"'," +
                "price_per_night='"+dataMap.get("pricePerNight")+"'," +
                "capacity='"+dataMap.get("capacity")+"'," +
                "image='"+dataMap.get("image")+"'" +

                "WHERE room_id='"+requirement.get("roomID")+"' " +
                "AND hotel_hotel_id='"+requirement.get("hotelID")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);



    }


    /**
     * Delete room out of hotel
     * @param requirement roomID and hotelID
     */
    @Override
    public void deleteRecord(HashMap<String, String> requirement) throws  SQLException {
        String query ="DELETE FROM room " +
                "WHERE room_id='"+requirement.get("roomID")+"'" +
                "AND hotel_hotel_id='"+requirement.get("hotelID")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    /**
     * View all the room of  a hotel
     * @param requirement hotelID of hotel
     * @return ArrayList information rooms of a hotel
     */
    public ArrayList<HashMap<String, String>> viewRoom(HashMap<String, String> requirement) throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query = "SELECT room_id,name,room_size,price_per_night,capacity,image " +
        "FROM room WHERE hotel_hotel_id='"+requirement.get("hotelID")+"'" ;

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("roomID", rs.getString("room_id"));
            returnHashArrayItem.put("name",rs.getString("name"));
            returnHashArrayItem.put("roomSize",rs.getString("room_size"));
            returnHashArrayItem.put("pricePerNight",rs.getString("price_per_night"));
            returnHashArrayItem.put("capacity", rs.getString("capacity"));
            returnHashArrayItem.put("image", rs.getString("image"));
            returnHashArray.add(returnHashArrayItem);
        }
        return returnHashArray;
    }

    /**
     * Search room with location, capacity and pricePerNight from a to b
     * @param requirement location or capacity or pricePerNightFrom/pricePerNightTo
     * @return ArrayList room fitted with condition
     */
    public ArrayList<HashMap<String, String>> searchRoom(HashMap<String, String> requirement) throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String condition ;

        String condition1 = "hotel.location='" + requirement.get("location") + "'";
        String condition2 = "room.price_per_night >='" + requirement.get("pricePerNightFrom") + "'" +
                "AND room.price_per_night<='" + requirement.get("pricePerNightTo") + "'";
        String condition3 = "room.capacity='" + requirement.get("capacity") + "'";


        if (requirement.containsKey("location") && requirement.containsKey("pricePerNightFrom") && requirement.containsKey("capacity")) {
            condition = condition1 + "AND" + condition2 + "AND" + condition3;
        } else if (requirement.containsKey("location") && requirement.containsKey("pricePerNightFrom") ) {
            condition = condition1 + "AND" + condition2;
        } else if (requirement.containsKey("location") && requirement.containsKey("capacity")) {
            condition = condition1 + "AND" + condition3;
        } else if(requirement.containsKey("pricePerNightFrom") && requirement.containsKey("capacity")) {
            condition = condition2 + "AND" + condition3;
        } else if(requirement.containsKey("location")) {
            condition = condition1;
        } else if (requirement.containsKey("pricePerNightFrom")) {
            condition = condition2;
        } else {
            condition = condition3;
        }


        String query = "SELECT hotel.name,hotel.star_rank,hotel.location,room.name," +
                "room.room_size,room.price_per_night,room.capacity" +
                "FROM room INNER JOIN hotel ON hotel.hotel_id=room.hotel_hotel_id " +
                "WHERE " + condition + "ORDER BY room.price_per_night DESC";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("hotelName", rs.getString("hotel.name"));
            returnHashArrayItem.put("starRank", rs.getString("hotel.star_rank"));
            returnHashArrayItem.put("location", rs.getString("hotel.location"));
            returnHashArrayItem.put("roomName", rs.getString("room.name"));
            returnHashArrayItem.put("roomSize", rs.getString("room.room_size"));
            returnHashArrayItem.put("pricePerNight", rs.getString("room.price_per_night"));
            returnHashArrayItem.put("capacity", rs.getString("room.capacity"));
            returnHashArray.add(returnHashArrayItem);
        }

        return returnHashArray;
    }


}
