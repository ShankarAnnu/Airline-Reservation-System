
public class SeatAllocation {
	private String seat_ID = "";
	private boolean ifBooked = false;
	
	public SeatAllocation(char s1, String s2) {
		this.seat_ID = s1 + s2;
		this.ifBooked = false;
	}
	
	public String getSeat_ID() {
		return seat_ID;
	}
	
	public void setSeat_ID(String s) {
		this.seat_ID = s;
	}
	
	public boolean getIfBooked() {
		return ifBooked;
	}
	
	public void setIfBooked(boolean b) {
		this.ifBooked = b;
	}

} 
 