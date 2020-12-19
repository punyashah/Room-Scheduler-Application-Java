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
public class ReservationEntry {
    
    private final String faculty; //Declaring variable for faculty name
    private final String room; //Declaring variable for room name
    private final Date date; //Declaring variable for Date object
    private final int seats; //Declaring variable for number of seats required
    private final Timestamp timestamp; //Declaring variable for timestamp object
    
    public ReservationEntry(String faculty, String room, Date date, int seats, Timestamp timestamp){ //Constructor for setting the values of the variables above
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    
    public String getFaculty(){ //Getter function to get faculty name
        return faculty;
    }
    
    public String getRoom(){ //Getter function to get room assigned
        return room;
    }
    
    public Date getDate(){ //Getter function for the date variable
        return date;
    }
    
    public int getSeats(){ //Getter function for the number of seats
        return seats;
    }
    
    public Timestamp getTimestamp(){ //Getter function for the timestamp variable
        return timestamp;
    }
    
}
