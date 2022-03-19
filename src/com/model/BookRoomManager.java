package com.model;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.Serialization;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookRoomManager extends MysqlFetch {
    public static String BookRoomIDGenerator() {
        return Serialization.getRandomIDString(10);
    }

    /**
     *
     * @param connection
     */
    public BookRoomManager(Connection connection) { super(connection); }


    /**
     * Book a room in hotel
     * @param dataMap bookRoomID,guestUsername,roomID,hotelID,bookDay,checkIn,checkOut,paymentMethod,noteOfGuests
     */
    @Override
    public void toDatabase(HashMap<String, String> dataMap) throws  SQLException {
        String query = "INSERT INTO book_room VALUES(" +
                "'" + dataMap.get("bookRoomID") + "'," +
                "'" + dataMap.get("guestUsername") + "'," +
                "'" + dataMap.get("bookDay") + "'," +
                "'" + dataMap.get("checkIn") + "'," +
                "'" + dataMap.get("checkOut") + "'," +
                "'" + dataMap.get("paymentMethod") + "'," +
                "'" + dataMap.get("noteOfGuests") + "',"+
                "'" + dataMap.get("roomID") + "'," +
                "'" + dataMap.get("hotelID") + "')" ;

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
                    "AND room_room_id='" + requirement.get("roomID") + "' " +
                    "AND room_hotel_hotel_id='" + requirement.get("hotelID") + "' " +
                    " AND book_day='" + requirement.get("bookDay") + "' ";
        }

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("bookRoomID", rs.getString("book_room_id"));
            returnHashArrayItem.put("guestUsername", rs.getString("guest_username"));
            returnHashArrayItem.put("bookDay", rs.getString("book_day"));
            returnHashArrayItem.put("checkIn", rs.getString("check_in"));
            returnHashArrayItem.put("checkOut", rs.getString("check_out"));
            returnHashArrayItem.put("paymentMethod", rs.getString("payment_method"));
            returnHashArrayItem.put("noteOfGuests", rs.getString("note_of_guests"));
            returnHashArrayItem.put("roomID", rs.getString("room_room_id"));
            returnHashArrayItem.put("hotelID", rs.getString("room_hotel_hotel_id"));
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
                    " room_room_id='" + dataMap.get("roomID") + "'," +
                    " room_hotel_hotel_id='" + dataMap.get("hotelID") + "'," +
                    " book_day='" + dataMap.get("bookDay") + "'," +
                    " check_in='" + dataMap.get("checkIn") + "'," +
                    " check_out='" + dataMap.get("checkOut") + "'," +
                    " payment_method='" + dataMap.get("paymentMethod") + "'," +
                    " note_of_guests='" + dataMap.get("noteOfGuests") + "'" +

                    " WHERE book_room_id='" + requirement.get("bookRoomID") + "'";
        }
        else {
            query = "UPDATE bookroom SET" +
                    " room_room_id='" + dataMap.get("roomID") + "'," +
                    " room_hotel_hotel_id='" + dataMap.get("hotelID") + "'," +
                    " book_day='" + dataMap.get("bookDay") + "'," +
                    " check_in='" + dataMap.get("checkIn") + "'," +
                    " check_out='" + dataMap.get("checkOut") + "'," +
                    " payment_method='" + dataMap.get("paymentMethod") + "'," +
                    " note_of_guests='" + dataMap.get("noteOfGuests") + "'" +

                    " WHERE guest_username='" + requirement.get("guestUsername") + "'" +
                    " AND room_room_id='" + requirement.get("bookRoomID") + "' " +
                    " AND room_hotel_hotel_id='" + requirement.get("hotelID") + "' " +
                    " AND book_day='" + requirement.get("bookDay") + "' ";
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
                    "AND room_room_id='" + requirement.get("bookRoomID") + "' " +
                    "AND room_hotel_hotel_id='" + requirement.get("hotelID") + "' " +
                    "AND book_day='" + requirement.get("bookDay") + "' ";
        }
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    /**
     * Validate CheckIn CheckOut BookRoom
     * @param hotelID
     * @param roomID
     * @param checkIn
     * @param checkOut
     * @return
     * @throws SQLException
     */
    public boolean validateCheckInCheckOutRoom(String hotelID, String roomID ,LocalDateTime checkIn, LocalDateTime checkOut) throws  SQLException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String stringToday = LocalDateTime.now().format(dateTimeFormatter);
        LocalDateTime today = LocalDateTime.parse(stringToday,dateTimeFormatter);
        //validate
        //right form

        LocalDateTime subCheckOut = checkOut;
        if(subCheckOut.minusHours(2).isBefore(checkIn) || subCheckOut.minusHours(2).isEqual(checkIn)) {
            System.out.println("You need to book the room in 2 hours or more!");
            return false;
        }
        if(today.isAfter(checkIn)) {
            System.out.println("The check-in day needs being after now!");
            return false;
        }
        ArrayList<LocalDateTime> dateTimeLine = new ArrayList<LocalDateTime>();
        String query = "SELECT check_in,check_out " +
                "FROM book_room WHERE room_hotel_hotel_id = '"+ hotelID +"' " +
                "AND room_room_id = '"+ roomID +"' " +
                "AND check_out > '"+LocalDateTime.now().format(dateTimeFormatter)+"' ORDER BY check_in ASC";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            dateTimeLine.add(LocalDateTime.parse(rs.getString("check_in"),dateTimeFormatter).minusHours(2));
            dateTimeLine.add(LocalDateTime.parse(rs.getString("check_out"),dateTimeFormatter).plusHours(2));
        }
        if(dateTimeLine.size()==0) return true;
        if(checkIn.isAfter(dateTimeLine.get(dateTimeLine.size()-1))) return true;

        int i =0;
        int j =1;
        if(today.isBefore(dateTimeLine.get(0))) {
            dateTimeLine.add(0,today);
        } else {
            dateTimeLine.add(1,today);
            i+=2;
            j+=2;
        }

        do {
            if(!(checkIn.isBefore(dateTimeLine.get(i))) && !(checkOut.isAfter(dateTimeLine.get(j)))) return true;
                i+=2;
                j+=2;
        } while(i<dateTimeLine.size()-1);
        System.out.println("Invalid check-in and check-out day !");
        System.out.println();
        return false;
    }


    /**
     * See customers' information have booked rooms in that hotel
     * @param requirement hotelID
     * @return ArrayList customers' information
     */
    public  ArrayList<HashMap<String, String>> seeCustomerInformation(HashMap<String, String> requirement) throws SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();

        String query = "SELECT book_room.room_room_id,book_room.book_day,book_room.check_in,book_room.check_out," +
                "guest.last_name,guest.first_name,guest.phone_number,guest.email,guest.cccd FROM book_room " +
                "INNER JOIN guest ON book_room.guest_username=guest.username " +
                "WHERE book_room.room_hotel_hotel_id = '"+requirement.get("hotelID")+"' " +
                "ORDER BY book_room.check_in ASC";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("roomID", rs.getString("book_room.room_room_id"));
            returnHashArrayItem.put("bookDay", rs.getString("book_room.book_day"));
            returnHashArrayItem.put("checkIn", rs.getString("book_room.check_in"));
            returnHashArrayItem.put("checkOut", rs.getString("book_room.check_out"));
            returnHashArrayItem.put("lastName", rs.getString("guest.last_name"));
            returnHashArrayItem.put("firstName", rs.getString("guest.first_name"));
            returnHashArrayItem.put("phoneNumber", rs.getString("guest.phone_number"));
            returnHashArrayItem.put("email", rs.getString("guest.email"));
            returnHashArrayItem.put("cccd", rs.getString("guest.cccd"));
            returnHashArray.add(returnHashArrayItem);
        }
        return returnHashArray;
    }

    /**
     * See BookRooms Of Guest
     * @param requirement guestUsername
     * @return ArrayList bookrooms of guest
     * @throws SQLException
     */
    public  ArrayList<HashMap<String, String>> seeBookRoomsOfGuest(HashMap<String, String> requirement) throws SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();

        String query = "SELECT book_room_id,room_room_id,room_hotel_hotel_id,book_day,check_in,check_out,payment_method,note_of_guests " +
                "FROM book_room WHERE guest_username = '"+requirement.get("guestUsername")+"' ORDER BY check_in ASC";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("bookroomID", rs.getString("book_room_id"));
            returnHashArrayItem.put("roomID", rs.getString("room_room_id"));
            returnHashArrayItem.put("hotelID", rs.getString("room_hotel_hotel_id"));
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
     * See BookRoomList Of A Room
     * @param requirement hotelID, roomID
     * @return BookRoomList Of A Room
     * @throws SQLException
     */
    public  ArrayList<HashMap<String, String>> seeBookRoomListOfRoom(HashMap<String, String> requirement) throws SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query = "SELECT book_room_id,check_in,check_out " +
                "FROM book_room WHERE room_hotel_hotel_id = '"+requirement.get("hotelID")+"' " +
                "AND room_room_id = '"+requirement.get("roomID")+"' " +
                "AND check_out > '"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"' ORDER BY check_out ASC";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            HashMap<String, String> returnHashArrayItem = new HashMap<>();
            returnHashArrayItem.put("bookroomID", rs.getString("book_room_id"));
            returnHashArrayItem.put("checkIn", rs.getString("check_in"));
            returnHashArrayItem.put("checkOut", rs.getString("check_out"));
            returnHashArray.add(returnHashArrayItem);
        }
        return returnHashArray;
    }

    public Integer seePricePerNightOfRoom(String hotelID, String roomID) throws SQLException {
        Integer pricePerNight = 0;
        String query = "SELECT room.price_per_night " +
                "FROM book_room INNER JOIN room ON room.room_id = book_room.room_room_id AND room.hotel_hotel_id = book_room.room_hotel_hotel_id " +
                "WHERE book_room.room_hotel_hotel_id = '"+hotelID+"' " +
                "AND book_room.room_room_id = '"+roomID+"' ";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            pricePerNight = rs.getInt("room.price_per_night");
        }
        return pricePerNight;
    }
}

