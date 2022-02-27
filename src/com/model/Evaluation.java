package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Evaluation extends MysqlFetch {
    /**
     * get connection
     * @param connection
     */
    public Evaluation(Connection connection) {
        super(connection);
    }


    /**
     * take the evaluation
     * @param dataMap information HashMap of evaluation
     */
    @Override
    public void toDatabase(HashMap<String, String> dataMap) {
        String query = "INSERT INTO evaluation VALUES(" +
                "'"+dataMap.get("guestUsername")+"'," +
                "'"+dataMap.get("hotelID")+"'," +
                "'"+dataMap.get("evaluationDay")+"'," +
                "'"+dataMap.get("vote")+"'," +
                "'"+dataMap.get("feedback")+"',";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * view the evaluation of guests about a hotel
     * @param requirement hotelID
     * @return ArrayList of guests' evaluation about a hotel
     */
    @Override
    public ArrayList<HashMap<String, String>> fromDatabase(HashMap<String, String> requirement) {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();

        String query = "SELECT guest_username, vote, feedback, evaluation_day  " +
                "FROM book_room WHERE hotel_hotel_id='" + requirement.get("hotelID") + "'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HashMap<String, String> returnHashArrayItem = new HashMap<>();
                returnHashArrayItem.put("guestUsername", rs.getString("guest_username"));
                returnHashArrayItem.put("vote", rs.getString("vote"));
                returnHashArrayItem.put("feedback", rs.getString("feedback"));
                returnHashArrayItem.put("evaluationDay", rs.getString("evaluation_day"));
                returnHashArray.add(returnHashArrayItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnHashArray;
    }


    /**
     * change the evaluation
     * @param requirement guestUsername,hotelID,evaluationDay
     * @param dataMap vote and feedback
     */
    @Override
    public void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) {
        String query = "UPDATE evaluation SET" +
                "vote='"+dataMap.get("vote")+"'," +
                "feedback='"+dataMap.get("feedback")+"'," +

                "WHERE guest_username='"+requirement.get("guestUsername")+"' " +
                "AND hotel_hotel_id='"+requirement.get("hotelID")+"'" +
                "AND evaluation_day='"+requirement.get("evaluationDay")+"'";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete the evaluation
     * @param requirement guestUsername,hotelID,evaluationDay
     */
    @Override
    public void deleteRecord(HashMap<String, String> requirement) {
        String query ="DELETE FROM evaluation " +
                "WHERE guest_username='"+requirement.get("guestUsername")+"' " +
                "AND hotel_hotel_id='"+requirement.get("hotelID")+"'" +
                "AND evaluation_day='"+requirement.get("evaluationDay")+"'";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * view hotels with the average vote bigger than 5
     * @return ArrayList hotelName,averageVote(>5),image,location of hotels
     */
    public ArrayList<HashMap<String, String>> viewHotelsWithAverageVoteBiggerThan5() {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query = "SELECT hotel.name,hotel.image,hotel.location,AVG(evaluation.vote) FROM evaluation " +
                "INNER JOIN hotel ON evaluation.hotel_hotel_id=hotel.hotel_id GROUP BY hotel.name" +
                "HAVING AVG(evaluation.vote) > 5 ORDER BY AVG(evaluation.vote) DESC";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HashMap<String, String> returnHashArrayItem = new HashMap<>();
                returnHashArrayItem.put("hotelName", rs.getString("hotel.name"));
                returnHashArrayItem.put("averageVote", rs.getString("AVG(evaluation.vote)"));
                returnHashArrayItem.put("image", rs.getString("hotel.image"));
                returnHashArrayItem.put("location", rs.getString("hotel.location"));
                returnHashArray.add(returnHashArrayItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnHashArray;
    }
}
