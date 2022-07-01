import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.JOptionPane;

public class AdminDetails {
	String userName = " ";
	String password = " ";
	
	FlightInfo f = new FlightInfo();
	
		
	public AdminDetails() {}
	
	public static void register(String filepath) throws IOException{
		String path = filepath;
		FileWriter fw = new FileWriter(path, true);
		BufferedWriter br = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(br);
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter your name without space");
		String name = input.next();
		
		System.out.println("Enter your password(without space)");
		String password = input.next();
		
		
		pw.println(name+","+password);
		pw.flush();
		pw.close();
		System.out.println("Congratulations, you have signed up as an admin!");
	}
	
	public String signIn(String filepath) throws IOException{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter your username");
		String searchName = input.next();
		
		System.out.println("Enter your password");
		String searchPassword = input.next();
		
		String resultRow = "Wrong ID or password!";
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line;
		boolean isValidUser = false;
		while((line = br.readLine()) != null) {
			String[] values = line.split(",");
			if((values[0].contentEquals(searchName))&&(values[1].contentEquals(searchPassword))) {
				System.out.println("Thats the correct username and password!!!");
				isValidUser = true;
				action();
				break;
			}
		}
		if (!isValidUser) {
			System.out.println("\nWrong username or password.... Please try again.");
		}
		br.close();
		return resultRow;
	}
	
	public static void action() throws IOException {
		Scanner input = new Scanner(System.in);
		int value = 0;
		boolean GetCommand = false;
		AdminDetails a = new AdminDetails();

		
		while(!GetCommand) {
			Run.Timeout();
			showMenu();
			value = input.nextInt();
			if(value>6) {
				System.out.println("No such option!");
			}
			else GetCommand = true;
		}
		
		do {
			switch(value) {
			case 0 : continue;
			case 1 : Run.Timeout();
					 a.createFlight();
					 break;
			case 2: Run.Timeout();
					String[] data = new String[100];
					data = a.viewAllOrders("orders.csv");
					System.out.println(data.length);
					for(int i=0;i<data.length;i++) {
						System.out.println(data[i]);
					}
					break;
			case 3: System.out.println("Enter the ID of the flight you want to delete");
					String removeTerm = input.next();
					a.deleteFlight("publishedFlights.csv", removeTerm);
					break;
			case 4: Run.Timeout();
					a.publishFlight("publishedFlights.csv");
					break;
			default: break;
			}
			showMenu();
			value = input.nextInt();
		} while(value!=0);
	}
	
    private static void showMenu() {
        System.out.printf("\nWelcome to the admin menu!!\n%s%s%s%s%s",
                          "0:Exit\n",
                          "1:Creat flight\n",
                          "2:View all orders\n",
                          "3:Delete flight\n",
                          "4:Publish flights\n");
    }
    
    public void createFlight() throws IOException {
    	int checker=0;
    	Scanner input = new Scanner(System.in);
        System.out.println("Please input FlightID:"); //The flights are generated only by the administrators.
        String flightID = input.next();
        checker = checkID(flightID);
        while(checker == 1) {
        	System.out.println("FlightInfo with this ID already exists, give a new ID!");
        	String temp = input.next();
        	flightID = temp;
        	checker = checkID(flightID);
        }
        f.setFlightID(flightID);
        
        System.out.println("Please input start time in 24 hour format: (HH:MM format)");
        String start_Time = input.next();
        f.setStart_Time(start_Time);
        
        System.out.println("Please input arrival time in 24 hour format: (HH:MM format)"); //Flight departure time should not be after the arrival time.
        String arrival_Time = input.next();
        f.setArrivalTime(arrival_Time);
        
        System.out.println("Please input the source:");
        String source = input.next();
        if (arrival_Time.contentEquals(f.getStart_Time())) {
        	System.out.println("Arrival time should be after the Start time, enter a different Arrival time !");
        	String temp1 = input.next();
            arrival_Time = temp1;
        }
        f.setSource(source);
        
        System.out.println("Please input the destination:"); //The departure city of airline flight should be different from its arrival city.
        String destination = input.next();
        if (destination.contentEquals(f.getSource())) {
        	System.out.println("Source and destination should be different, enter a different destination!");
        	String temp = input.next();
            destination = temp;
        }
        f.setDestination(destination);
        
        System.out.println("Please input departure date in format (DD/MM/YYYY) :");
        String Date_of_Departure = input.next();
        f.setDepartureDate(Date_of_Departure);
        
        System.out.println("Please input price:");
        int price = input.nextInt();
        f.setPrice(price);
        
        System.out.println("Please input seat capacity:");
        int no_of_seats = input.nextInt();
        f.setseatCapacity(no_of_seats);
        
        System.out.println("Enter the number of booked seats");
        int filled = input.nextInt();
        f.setFilled(filled);
        
        f.setFlightStatus(FlightInfo.Status.UNPUBLISHED);
        System.out.println("The flight details entered are"
        		+ "\nFlight ID: " + f.getFlightID()
        		+ "\nFlight start time : " + f.getStart_Time()
        		+ "\nFlight arrival time: " + f.getArrivalTime()
        		+ "\nFlight source: " + f.getSource()
        		+ "\nFlight destination: " + f.getDestination()
        		+ "\nFlight departure date: " + f.getDepartureDate());
        
        System.out.println("\n\nThe new flight has not been published to the file.\nPlease check the publish option to publish to the file");
        
        FlightInfo f1 = new FlightInfo(flightID, start_Time, arrival_Time, source, destination, Date_of_Departure, price, no_of_seats, filled);
        
    }
    
    public void publishFlight(String filepath) throws IOException {
		String path = filepath;
		FileWriter fw = new FileWriter(path, true);
		BufferedWriter br = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(br);
		
		String ID = f.getFlightID();
		String start = f.getStart_Time();
		String arrival = f.getArrivalTime();
		String source = f.getSource();
		String destination = f.getDestination();
		String date = f.getDepartureDate();
		int capacity = f.getseatCapacity();
		int price = f.getPrice();
		int filled = f.getFilled();
		
		pw.println(ID+ "," +start+"," +arrival+ "," +source+ "," +destination+ "," +date+ "," +capacity+ "," +price+ "," +filled);
		pw.flush();
		pw.close();
		
		System.out.println("Congratulations, you have published the flight" );
		f.setFlightStatus(FlightInfo.Status.AVAILABLE);
		
    }
    
    public void deleteFlight(String filepath, String removeTerm) {
    	int positionOfTerm = 1;
		int position = positionOfTerm - 1;
		String tempFile = "temp.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		String currentLine;
		String data[];
		
		try {
			FileWriter filewrite = new FileWriter(tempFile,true);
			BufferedWriter buffwrite = new BufferedWriter(filewrite);
			PrintWriter printwrite = new PrintWriter(buffwrite);
			
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null) {
				data = currentLine.split(",");
				if(!(data[position].equalsIgnoreCase(removeTerm))) {
					printwrite.println(currentLine);
				}
			}
			printwrite.flush();
			printwrite.close();
			fr.close();
			br.close();
			buffwrite.close();
			filewrite.close();
			
			oldFile.delete();
			System.out.println("The flight number " + removeTerm + " has been deleted.");
			File dump = new File(filepath);
			newFile.renameTo(dump);
		}
		catch(Exception e) {
			 
		}
    	    	
    }
    
    public String[] viewAllOrders(String filepath) throws IOException {
		String path = filepath;
		ArrayList<String> records = new ArrayList<String>();
		String resultRow = "No flights!";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while((line = br.readLine())!=null) {
			String[] values = line.split(",");
			if(values[0]!=null) {
				resultRow = line;
				records.add(resultRow);
			}
		}
		String[] recordsArray = new String[records.size()];
		records.toArray(recordsArray);
		return recordsArray;
    }
    
    public static int checkID(String flightID ) throws IOException {
    	int check = 0;
    	BufferedReader br = new BufferedReader(new FileReader("publishedFlights.csv"));
    	String line;
    	while((line=br.readLine())!=null) {
    		String[] values = line.split(",");
    		if(values[0].contentEquals(flightID)) {
    			check = 1;
    		}
    	}
    	return check;
    }
}
