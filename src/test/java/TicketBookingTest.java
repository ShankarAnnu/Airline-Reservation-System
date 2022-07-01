import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class TicketBookingTest {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	public void testShowMenuFunction() {
		//Run run = new Run();
		Run.showMenu();
			
		String ss = "\nWelcome to book ticket!\n "
				+ "0:Exit\n "
				+ "1:Sign up as Administrators\n "
				+ "2:Sign up as Passengers\n "
				+ "3:Sign in as Administrators\n "
				+ "4:Sign in as Passengers\n "
				+ "5:Query FlightInfo without sign in\n";
		
		assertEquals(true,outputStreamCaptor.toString().trim().contains("Welcome"));
	}
	
	
	@Test
	public void testTravellersCheckPasssword() {
		
		Passenger t1 = new Passenger(123456,"JIM","PAM");

		assertEquals("PAM",t1.getPassword());
	}
	
	@Test
	public void testTravellersName() {
		
		Passenger t1 = new Passenger(123456,"JIM","PAM");

		assertEquals("JIM",t1.getName());
	}
	
	@Test
	public void testSeat_ID() {
		
		SeatAllocation s1 = new SeatAllocation('U',"S2");

		assertEquals("US2",s1.getSeat_ID());
	}	
}
