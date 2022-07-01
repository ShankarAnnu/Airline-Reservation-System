import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;

public class FlightInfo {
	
	private String flightID;
	private String start_Time;
	private String arrival_Time;
	private String source;
	private String destination;
	private String Date_of_Departure;
	private int no_of_seats;
	private int price;
	private int filled;
	 
	private ArrayList<SeatAllocation> seats = new ArrayList<SeatAllocation>();
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	
	public enum Status {UNPUBLISHED, AVAILABLE}
	private Status flightStatus;
	private void makeseat(int n) {
		 
	}
	
	public FlightInfo() {}
	
	public FlightInfo(String flightID_, String start_Time_, String arrival_Time_, String source_, String destination_, 
			      String departureDate_, int no_of_seats_, int price_, int filled_) {
		flightID = flightID_; //Each airline flight must have unique flight number.
		start_Time = start_Time_;
		arrival_Time = arrival_Time_;
		source = source_;
		destination = destination_;
		Date_of_Departure = departureDate_;
		no_of_seats = no_of_seats_;
		price = price_;
		filled = filled_;
		flightStatus = Status.UNPUBLISHED; 
		
		for (int i=1; i <= no_of_seats/6; i++) {  //Each airline flight can have maximum of 60 seats.
			for (int j=1; j<7; j++) {
				String s = j + "";
				SeatAllocation seatAllocation = new SeatAllocation((char)(65+i),s);
				this.seats.add(seatAllocation);
			}
		}
	}
	public String getFlightID() {
		return flightID;
	}
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}
	public String getStart_Time() {
		return start_Time;
	}
	public void setStart_Time(String start_Time) {
		this.start_Time = start_Time;
	}
	public String getArrivalTime() {
		return arrival_Time;
	}
	public void setArrivalTime(String arrival_Time) {
		this.arrival_Time = arrival_Time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureDate() {
		return Date_of_Departure;
	}
	public void setDepartureDate(String Date_of_Departure) {
		this.Date_of_Departure = Date_of_Departure;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getFilled() {
		return filled;
	}
	public void setFilled(int filled) {
		this.filled = filled;
	}
	public Status getFlightStatus() {
		return flightStatus;  
	}
	public void setFlightStatus(Status flightStatus) {
		this.flightStatus = flightStatus;
	}
	public int getseatCapacity() {
		return no_of_seats;
	}
	public void setseatCapacity(int no_of_seats) {
		this.no_of_seats = no_of_seats;
	}
	
}
