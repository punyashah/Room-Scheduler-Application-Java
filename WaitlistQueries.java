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
public class WaitlistQueries {
    private static Connection connection;
    private static PreparedStatement getWaitlistDate;
    private static PreparedStatement getWaitlistFaculty;
    private static PreparedStatement getWaitlist;
    private static PreparedStatement addWaitlist;
    private static PreparedStatement deleteWaitlist;
    private static ResultSet resultSet;
    
    public static ArrayList<WaitlistEntry> getWaitlistByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlistByDate = new ArrayList<WaitlistEntry>();
        try{
            getWaitlistDate = connection.prepareStatement("Select faculty, date, seats, timestamp from waitlist where date=?"); //SQL Query to select content from the waitlist table according to the date
            getWaitlistDate.setDate(1, date);
            resultSet = getWaitlistDate.executeQuery();
            while(resultSet.next()){
                WaitlistEntry entry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlistByDate.add(entry);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not connect to the database!");
            System.exit(1);
        }
        return waitlistByDate;
    }
    public static ArrayList<WaitlistEntry> getWaitlist(){
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlistByDate = new ArrayList<WaitlistEntry>();
        try{
            getWaitlist = connection.prepareStatement("Select faculty, date, seats, timestamp from waitlist order by date, timestamp"); //SQL Query to select content from the waitlist table
            resultSet = getWaitlist.executeQuery();
            while(resultSet.next()){
                WaitlistEntry entry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlistByDate.add(entry);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not connect to the database!");
            System.exit(1);
        }
        return waitlistByDate;
    }
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty){
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlistByFaculty = new ArrayList<WaitlistEntry>();
        try{
            getWaitlistFaculty = connection.prepareStatement("Select faculty, date, seats, timestamp from waitlist where faculty=?"); //SQL Query to select content from the waitlist table according to the given faculty name
            getWaitlistFaculty.setString(1, faculty);
            resultSet = getWaitlistFaculty.executeQuery();
            while(resultSet.next()){
                WaitlistEntry entry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlistByFaculty.add(entry);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
        return waitlistByFaculty;
    }
    
    public static void addWaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp){
        connection = DBConnection.getConnection();
        try{
            addWaitlist = connection.prepareStatement("INSERT INTO WAITLIST (Faculty, Date, Seats, Timestamp) VALUES (?, ?, ?, ?)"); //SQL QUery to insert values into the waitlist table
            addWaitlist.setString(1, faculty);
            addWaitlist.setDate(2, date);
            addWaitlist.setInt(3, seats);
            addWaitlist.setTimestamp(4, timestamp);
            addWaitlist.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not connect to the database!");
            System.exit(1);
        }
    }
    
    public static void deleteWaitlistEntry(String faculty, Date date){
        connection = DBConnection.getConnection();
        try{
            deleteWaitlist = connection.prepareStatement("DELETE FROM WAITLIST WHERE Faculty=? AND Date=? "); //SQL Query to delete values from the waitlist table
            deleteWaitlist.setString(1, faculty);
            deleteWaitlist.setDate(2, date);
            deleteWaitlist.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
    }
}
