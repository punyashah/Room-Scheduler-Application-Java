import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Faculty
{
    private static Connection connection; //Variable for database connection
    private static ArrayList<String> faculty = new ArrayList<String>(); //ArrayList to store faculty names
    private static PreparedStatement addFaculty;
    private static PreparedStatement getFacultyList;
    private static ResultSet resultSet;
    
    public static void addFaculty(String name)
    {
        connection = DBConnection.getConnection(); //Connecting to the database using the DBConnection class
        try //Trying to connect to the database
        {
            addFaculty = connection.prepareStatement("insert into faculty (name) values (?)"); //SQL Query to insert faculty name into the faculty table
            addFaculty.setString(1, name); //Setting the unknown value to the faculty name provided
            addFaculty.executeUpdate(); //Executing the update to the table
        }
        catch(SQLException sqlException) //Throwing an error if there is a problem connecting to the database
        {
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
        
    }
    
    public static ArrayList<String> getFacultyList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> faculty = new ArrayList<String>();
        try
        {
            getFacultyList = connection.prepareStatement("select name from faculty order by name"); //Getting all the names from the faculty list
            resultSet = getFacultyList.executeQuery();
            
            while(resultSet.next())
            {
                faculty.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.out.println("Could not open the database!");
            System.exit(1);
        }
        return faculty;
        
    }
    
    
}

