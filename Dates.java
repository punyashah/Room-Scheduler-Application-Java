import java.sql.Date;
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
public class Dates {
    
    private static Connection connection;
    private static ArrayList<Date> dates = new ArrayList<Date>(); //Declaring a dates ArrayList to store the dates
    private static PreparedStatement addDate;
    private static PreparedStatement getDatesList;
    private static ResultSet resultSet;
    
    public static void addDate(Date date){
        connection = DBConnection.getConnection();
        try
        {
            addDate = connection.prepareStatement("insert into dates (date) values (?)"); //SQL Query to insert date value in the date tables
            addDate.setDate(1, date);
            addDate.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<Date> getAllDates(){
        connection = DBConnection.getConnection();
        dates.clear();
        try
        {
            getDatesList = connection.prepareStatement("select date from dates order by date"); //SQL Query to select dates from the database
            resultSet = getDatesList.executeQuery();
            
            while(resultSet.next())
            {
                dates.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return dates; //Returning the dates ArrayList
    } 
}
