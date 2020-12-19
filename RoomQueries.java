import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.List; 
import java.util.ArrayList; 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Punya
 */
public class RoomQueries {
    private static Connection connection;
    private static PreparedStatement getRooms; 
    private static PreparedStatement insertNewRoom;
    private static PreparedStatement dropRoomFromRooms;
    private static ResultSet resultSet;
    private static PreparedStatement getRoomsList;
    private static ArrayList<String> rooms = new ArrayList<String>();
    
    public static ArrayList<String> getAllPossibleRooms(int seats){
        connection = DBConnection.getConnection();
        rooms.clear();
        try{
            getRooms = connection.prepareStatement("Select name, seats from rooms where seats >= ? order by seats"); //SQL Query to get seats in accordance with the condition given
            getRooms.setInt(1, seats);
            resultSet = getRooms.executeQuery(); //Adding the contents of the SQL Query to resultSet
            while(resultSet.next()){
                rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
        return rooms;
    }
    
    public static void addRoom(String name, int seats){
        connection = DBConnection.getConnection();
        try{
           insertNewRoom = connection.prepareStatement("INSERT INTO Rooms (Name, Seats) VALUES (?, ?)"); //SQL Query to insert room name and number of seats in the rooms table
           insertNewRoom.setString(1, name);
           insertNewRoom.setInt(2, seats);
           insertNewRoom.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
    }
    
    public static void dropRoom(String name){
        connection = DBConnection.getConnection();
        try{
            dropRoomFromRooms = connection.prepareStatement("DELETE FROM Rooms WHERE Name=?"); //SQL Query to delete a given room from the rooms table
            dropRoomFromRooms.setString(1, name);
            dropRoomFromRooms.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
    } 
     public static ArrayList<String> getAllRooms(){
        connection = DBConnection.getConnection();
        rooms.clear();
        try
        {
            getRoomsList = connection.prepareStatement("select name from rooms order by name"); //SQL Query to select dates from the database
            resultSet = getRoomsList.executeQuery();
            
            while(resultSet.next())
            {
                rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms; //Returning the rooms ArrayList
    } 
}
