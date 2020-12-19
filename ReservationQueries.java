import java.sql.Connection; 
 import java.sql.DriverManager; 
 import java.sql.PreparedStatement; 
 import java.sql.ResultSet; 
 import java.sql.SQLException; 
 import java.util.List; 
 import java.util.ArrayList;
 import java.sql.Date;
 import java.sql.Timestamp;
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Punya
 */
public class ReservationQueries {
    private static Connection connection;
    private static PreparedStatement getReservationsDate;
    private static PreparedStatement getReservationsFaculty;
    private static PreparedStatement getRoomsReserved;
    private static PreparedStatement reservationsByRoom;
    private static PreparedStatement addReservation;
    private static PreparedStatement getAllReservations;
    private static PreparedStatement deleteReservation;
    private static ResultSet resultSet;

    public static ArrayList<ReservationEntry> getReservationsByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationsByDate = new ArrayList<ReservationEntry>();
        try{
            getReservationsDate = connection.prepareStatement("Select faculty, room, date, seats, timestamp from reservations where date=?"); //SQL Query to get content from the reservations table according to the given date
            getReservationsDate.setDate(1, date);
            resultSet = getReservationsDate.executeQuery();
            while(resultSet.next()){
                ReservationEntry entry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservationsByDate.add(entry);
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not connect to the database!");
            System.exit(1);
        }
        return reservationsByDate;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByRoom(String room){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationsByRoomList = new ArrayList<ReservationEntry>();
        try{
            reservationsByRoom = connection.prepareStatement("Select faculty, room, date, seats, timestamp from reservations where room=?"); //SQL Query to get content from the reservations table according to the given date
            reservationsByRoom.setString(1, room);
            resultSet = reservationsByRoom.executeQuery();
            while(resultSet.next()){
                ReservationEntry entry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservationsByRoomList.add(entry);
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not connect to the database!");
            System.exit(1);
        }
        return reservationsByRoomList;
    }
    
    public static ArrayList<ReservationEntry> getReservations(){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<ReservationEntry>();
        try{
            getAllReservations = connection.prepareStatement("Select * from RESERVATIONS order by faculty"); //SQL Query to get content from the reservations table according to the given date
            resultSet = getAllReservations.executeQuery();
            while(resultSet.next()){
                ReservationEntry entry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservations.add(entry);
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not connect to the database!");
            System.exit(1);
        }
        return reservations;
    }
    
    
    public static ArrayList<String> getRoomsReservedByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<String> reservedByDate = new ArrayList<String>();
        try{
            getRoomsReserved = connection.prepareStatement("Select room from reservations where date=?"); //SQL Query to get rooms from the reservation table according to the given date
            getRoomsReserved.setDate(1, date);
            resultSet = getRoomsReserved.executeQuery();
            while(resultSet.next()){
                reservedByDate.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not connect to the database!");
            System.exit(1);
        }
        return reservedByDate;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationsByFaculty = new ArrayList<ReservationEntry>();
        try{
            getReservationsFaculty = connection.prepareStatement("Select faculty, room, date, seats, timestamp from reservations where faculty=?"); //SQL Query to get content from the reservations table according to the given faculty name
            getReservationsFaculty.setString(1, faculty);
            resultSet = getReservationsFaculty.executeQuery();
            while(resultSet.next()){
                ReservationEntry entry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reservationsByFaculty.add(entry);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not connect to database!");
            System.exit(1);
        }
        return reservationsByFaculty;
    }
    
    public static void addReservationEntry(String faculty, String room, Date date, int seats, Timestamp timestamp){
        connection = DBConnection.getConnection();
        try{
            addReservation = connection.prepareStatement("INSERT INTO RESERVATIONS (Faculty, Room, Date, Seats, Timestamp) VALUES (?, ?, ?, ?, ?)"); //SQL Query to insert into the reservations table
            addReservation.setString(1, faculty);
            addReservation.setString(2, room);
            addReservation.setDate(3, date);
            addReservation.setInt(4, seats);
            addReservation.setTimestamp(5, timestamp);
            addReservation.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
    }
    
    public static void deleteReservationEntry(String faculty, Date date){
        connection = DBConnection.getConnection();
        try{
            deleteReservation = connection.prepareStatement("DELETE FROM RESERVATIONS WHERE Faculty=? AND Date=?"); //SQL Query to delete from reservations table
            deleteReservation.setString(1, faculty);
            deleteReservation.setDate(2, date);
            deleteReservation.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
    }
}
