package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Evaluation extends MysqlFetch {
    /**
     *
     * @param connection
     */
    public Evaluation(Connection connection) {
        super(connection);
    }


    /**
     * Take the evaluation
     * @param dataMap information of evaluation
     */
    @Override
    public void toDatabase(HashMap<String, String> dataMap) throws  SQLException {
        String query = "INSERT INTO evaluation VALUES(" +
                "'"+dataMap.get("guestUsername")+"'," +
                "'"+dataMap.get("hotelID")+"'," +
                "'"+dataMap.get("evaluationDay")+"'," +
                "'"+dataMap.get("vote")+"'," +
                "'"+dataMap.get("feedback")+"')";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }


    /**
     * View the evaluations of guests about a hotel
     * @param requirement hotelID
     * @return ArrayList of guests' evaluations about a hotel
     */
    @Override
    public ArrayList<HashMap<String, String>> fromDatabase(HashMap<String, String> requirement) throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();

        String query = "SELECT guest_username, vote, feedback, evaluation_day  " +
                "FROM book_room WHERE hotel_hotel_id='" + requirement.get("hotelID") + "'";

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
        return returnHashArray;
    }



    /**
     * Change the evaluation
     * @param requirement guestUsername,hotelID,evaluationDay
     * @param dataMap vote and feedback
     */
    @Override
    public void editRecord(HashMap<String, String> requirement, HashMap<String, String> dataMap) throws  SQLException {
        String query = "UPDATE evaluation SET" +
                "vote='"+dataMap.get("vote")+"'," +
                "feedback='"+dataMap.get("feedback")+"'" +

                "WHERE guest_username='"+requirement.get("guestUsername")+"' " +
                "AND hotel_hotel_id='"+requirement.get("hotelID")+"'" +
                "AND evaluation_day='"+requirement.get("evaluationDay")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    /**
     * Delete the evaluation
     * @param requirement guestUsername,hotelID,evaluationDay
     */
    @Override
    public void deleteRecord(HashMap<String, String> requirement) throws  SQLException {
        String query ="DELETE FROM evaluation " +
                "WHERE guest_username='"+requirement.get("guestUsername")+"' " +
                "AND hotel_hotel_id='"+requirement.get("hotelID")+"'" +
                "AND evaluation_day='"+requirement.get("evaluationDay")+"'";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);

    }


    /**
     * View hotels with the average vote bigger than 5
     * @return ArrayList hotelName,averageVote(>5),image,location of hotels
     */
    public ArrayList<HashMap<String, String>> viewHotelsWithAverageVoteBiggerThan5() throws  SQLException {
        ArrayList<HashMap<String, String>> returnHashArray = new ArrayList();
        String query = "SELECT hotel.name,hotel.image,hotel.location,AVG(evaluation.vote) FROM evaluation " +
                "INNER JOIN hotel ON evaluation.hotel_hotel_id=hotel.hotel_id GROUP BY hotel.name" +
                "HAVING AVG(evaluation.vote) > 5 ORDER BY AVG(evaluation.vote) DESC";

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

}
