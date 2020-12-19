/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Punya
 */
public class RoomEntry {
    private final String name; //Declaring variable for room name
    private final int seats; //Declaring variable for number of seats
    
    public RoomEntry(String name, int seats){ //Constructor to set name and seats
        this.name = name;
        this.seats = seats;
    }

    public String getName(){ //Getter function for room name
        return name;
    }
    
    public int getSeats(){ //Getter function for the number of seats
        return seats;
    }
}
