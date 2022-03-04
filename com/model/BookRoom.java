package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class BookRoom extends MysqlFetch {

    /**
     *
     * @param connection
     */
    public BookRoom(Connection connection) { super(connection); }



    /**
     * Book a room in hotel
     * @param dataMap bookRoomID,guestUsername,roomID,hotelID,bookDay,checkIn,checkOut,paymentMethod,noteOfGuests
     */
    @Override
    public void toDatabase(HashMap<String, String> dataMap) throws  SQLException {
        String query = "INSERT INTO bookroom VALUES(" +
                "'" + dataMap.get("bookRoomID") + "'," +
                "'" + dataMap.get("guestUsername") + "'," +
                "'" + dataMap.get("roomID") + "'," +
                "'" + dataMap.get("hotelID") + "'," +
                "'" + dataMap.get("bookDay") + "'," +
                "'" + dataMap.get("checkIn") + "'," +
                "'" + dataMap.get("checkOut") + "'," +
                "'" + dataMap.get("paymentMethod") + "'," +
                "'" + dataMap.get("noteOfGuests") + "')";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }


    /**
     * Find the book-room
     * @param requirement bookRoomID or (guestUsername,roomID,hotelID,bookDay)
     * @return information of book-room
     */
    @Override
    public ArrayList<HashMap<String, String>> fromDatabase(HashMap<String, String> requirement) throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query;

        if (requirement.containsKey("bookRoomID"))
            query = "SELECT * FROM book_room WHERE book_room_id='" + requirement.get("bookRoomID") + "'";

        else {
            query = "SELECT * FROM book_room WHERE guest_username='" + requirement.get("guestUsername") + "'" +
                    "AND room_room_Id='" + requirement.get("roomID") + "' " +
                    "AND hotel_hotel_id='" + requirement.get("hotelID") + "' " +
                    " AND book_day='" + requirement.get("bookDay") + "' ";
        }

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("bookRoomID", rs.getString("book_room_id"));
            returnHashArrayItem.put("guestUsername", rs.getString("guest_username"));
            returnHashArrayItem.put("roomID", rs.getString("room_room_id"));
            returnHashArrayItem.put("hotelID", rs.getString("hotel_hotel_id"));
            returnHashArrayItem.put("bookDay", rs.getString("book_day"));
            returnHashArrayItem.put("checkIn", rs.getString("check_in"));
            returnHashArrayItem.put("checkOut", rs.getString("check_out"));
            returnHashArrayItem.put("paymentMethod", rs.getString("payment_method"));
            returnHashArrayItem.put("noteOfGuests", rs.getString("note_of_guests"));
            returnHashArray.add(returnHashArrayItem);
        }
        return returnHashArray;
    }



    /**
     * Change the book-room
     * @param requirement bookRoomID or (guestUsername,roomID,hotelID,bookDay)
     * @param dataMap new information HashMap of book-room
     */
    @Override
    public void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query;

        if (requirement.containsKey("bookRoomID")) {
            query = "UPDATE bookroom SET" +
                    "room_room_Id='" + dataMap.get("roomID") + "'," +
                    "hotel_hotel_id='" + dataMap.get("hotelID") + "'," +
                    "book_day='" + dataMap.get("bookDay") + "'," +
                    "check_in='" + dataMap.get("checkIn") + "'," +
                    "check_out='" + dataMap.get("checkOut") + "'," +
                    "payment_method='" + dataMap.get("paymentMethod") + "'," +
                    "note_of_guests='" + dataMap.get("noteOfGuests") + "'" +

                    "WHERE book_room_id='" + requirement.get("bookRoomID") + "'";
        }
        else {
            query = "UPDATE bookroom SET" +
                    "room_room_Id='" + dataMap.get("roomID") + "'," +
                    "hotel_hotel_id='" + dataMap.get("hotelID") + "'," +
                    "book_day='" + dataMap.get("bookDay") + "'," +
                    "check_in='" + dataMap.get("checkIn") + "'," +
                    "check_out='" + dataMap.get("checkOut") + "'," +
                    "payment_method='" + dataMap.get("paymentMethod") + "'," +
                    "note_of_guests='" + dataMap.get("noteOfGuests") + "'" +

                    "WHERE guest_username='" + requirement.get("guestUsername") + "'" +
                    "AND room_room_Id='" + requirement.get("bookRoomID") + "' " +
                    "AND hotel_hotel_id='" + requirement.get("hotelID") + "' " +
                    "AND book_day='" + requirement.get("bookDay") + "' ";
        }

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }



    /**
     * Cancel the book-room
     * @param requirement bookRoomID or (guestUsername,roomID,hotelID,bookDay)
     */
    @Override
    public void deleteRecord(HashMap<String, String> requirement) throws  SQLException {
        String query;

        if (requirement.containsKey("bookRoomID"))
            query = "DELETE FROM book_room WHERE book_room_id='" + requirement.get("bookRoomID") + "'";
        else {
            query = "DELETE FROM book_room WHERE guest_username='" + requirement.get("guestUsername") + "'" +
                    "AND room_room_Id='" + requirement.get("bookRoomID") + "' " +
                    "AND hotel_hotel_id='" + requirement.get("hotelID") + "' " +
                    "AND book_day='" + requirement.get("bookDay") + "' ";

        }
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    /**
     * Validate the checkIn, checkOut book-room
     * @param requirement hotelID, roomID, checkIn, checkOut
     * @return
     */
    public boolean validateCheckInCheckOutRoom(HashMap<String, String> requirement) throws  SQLException {
        ArrayList<String> bookDayRoom = new ArrayList<String>();
        LocalDate date = LocalDate.now();
        String today=date.toString();
        bookDayRoom.add(today);

        String query ="SELECT check_in, check_out FROM book_room WHERE hotel_hotel_id='"+requirement.get("hotelID")+"' " +
                "AND room_room_id='"+requirement.get("roomID")+"' ORDER BY check_out ASC";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            bookDayRoom.add(rs.getString("check_in"));
            bookDayRoom.add(rs.getString("check_out"));
        }

        if(requirement.get("checkIn").compareTo(bookDayRoom.get(bookDayRoom.size()-1))>0) return true;
        if(requirement.get("checkIn").compareTo(bookDayRoom.get(0))<0) return false;

        int i =0;
        int j =1;
        do {
            if(requirement.get("checkIn").compareTo(bookDayRoom.get(i))>0 &&
                    requirement.get("checkOut").compareTo(bookDayRoom.get(j))<0) return true;
            i+=2;
            j+=2;
        } while(j<bookDayRoom.size());
        return false;
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
}

