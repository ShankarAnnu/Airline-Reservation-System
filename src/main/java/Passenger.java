//import java.awt.print.Flight_detail;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Passenger {

	FlightInfo f = new FlightInfo();
	Date createDate = new Date();
	public static String Passenger_Name;
	public static int Passenger_ID;
	
	int passenger_ID = 0;
	
	String name = " ";
	String password = " ";
	boolean ifSignIn = false;
	String seat;
	
	
	public Passenger() {
		
	}

	public Passenger(int passenger_ID, String name, String password) {
		this.name = name;
		this.password = password; 
	}

	public int getPassenger_ID() {
		return passenger_ID;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public boolean getIfSignIn() {
		return ifSignIn;
	}
	
	public void setIfSignIn(boolean b) {
	ifSignIn = b;
	}
	
	public void setPassenger_ID(int passenger_ID) {
		this.passenger_ID = passenger_ID;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void register(String filepath) throws IOException {
		int check = 0;
		String path = filepath;
		FileWriter fw = new FileWriter(path, true);
		BufferedWriter br = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(br);
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter your name");
		String name = input.next();
		System.out.println("Enter your password");
		String password = input.next();

		int passenger_ID = (int)(Math.random()*90000 + 100000); //Every passenger registering in the Airline Reservation System should have unique passenger ID.

		check = checkID(passenger_ID);
		while(check==1) {
			int temp = (int)(Math.random()*90000 + 100000);
			passenger_ID = temp;
			check = checkID(passenger_ID);
		}
		pw.println(name+","+password+","+passenger_ID);
		pw.println(name+","+password);
		
		pw.flush();
		pw.close();

		System.out.println("Congratulations, you have signed up as a passenger!");
		System.out.printf("Your ID is %d \n", passenger_ID);
		
	}

	public String signIn(String filepath) throws IOException{
		Scanner input = new Scanner(System.in);
		Passenger t = new Passenger();
		
		System.out.println("Enter your username");
		String searchName = input.next();
		t.setName(searchName);
		Passenger_Name = t.getName();
//		System.out.println(Passenger_Name);
		
		System.out.println("Enter your password");
		String searchPassword = input.next();
		
		String resultRow = "Wrong ID or password!";
		BufferedReader br = new BufferedReader(new FileReader("people.csv"));
		String line;
		boolean isValidUser = false;
		while((line = br.readLine())!=null) {
			String[] values = line.split(",");
			if((values[0].contentEquals(Passenger_Name))&&(values[1].contentEquals(searchPassword))) {
				System.out.println("✅ That's the correct username and password!!!");
				isValidUser = true;
				action();
				break;
			}
		}
		if(!isValidUser) {
			System.out.println("🚫 Wrong username or password.... Please try again.");
		}
		br.close();
		return resultRow;
	}
	
	public void queryOrders(String filepath) {
		//add verification
		String path = filepath;
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			while((line = br.readLine())!=null) {
				String values[] = line.split(",");
				System.out.println(values);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static String[] queryAllFlights(String filepath) throws IOException {
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
		br.close();
		String[] recordsArray = new String[records.size()];
		records.toArray(recordsArray);
		return recordsArray;
	}
	
	public static String[] queryByInfo(String filepath) throws IOException {
		String path = filepath;
		ArrayList<String> records = new ArrayList<String>();
		String resultRow = "No such flight!";
		Scanner input = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new FileReader(path));
		int n = 1;
		while(n==1) {
		System.out.println("Enter the source 🛫"); 
		String source = input.next();
		System.out.println("Enter the destination 🛬");
		String destination = input.next();

		String line;

			if (!source.equals(destination)) {
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					if ((values[3].contentEquals(source)) && (values[4].contentEquals(destination))) {
						resultRow = line;
						records.add(resultRow);
						n = 0;
					}

				}
			}
			else{
				System.out.println("Please try again.");
			}
		}
		br.close();
		String[] recordsArray = new String[records.size()];
		records.toArray(recordsArray);
		return recordsArray;
	}
	

	private static void showMenu() {
	   	System.out.printf("\nWelcome to the customer menu!! 🧖🏻‍♂️\n%s%s%s%s%s",
	                 			"0:Exit ❌\n",
	    			  			"1:query the flight\n",
	    			  			"2:reserve the flight\n",
	    			  			"3:unsubscribe the flight\n",
								"4:query my orders");  
	} 	  
	public static void action() throws IOException {      	  
	    int opt = 0;
	    Passenger t = new Passenger();
		Scanner input = new Scanner(System.in);
		String[] data = new String[100];
		Run.Timeout();
		Run.Timeout();
		showMenu();
		opt = input.nextInt();
		do {
			switch (opt) {
	                case 0: continue;
	      			case 1:	data = queryByInfo("publishedFlights.csv");
	      						for(int i=0;i<data.length;i++) {
	      						System.out.println(data[i]);
	      					}
	       			case 2: t.makeBooking();
	       					break;
	       					
	       			case 3: t.cancelBooking("orders.csv", Passenger_Name);
	       					break;
	       					
					case 4: data = t.viewMyOrders("orders.csv");
							for(int i=0;i<data.length;i++) {
		  						System.out.println(data[i]);
		  					}
							break;
							
							
			default : System.out.println("No such command");
		}
		Run.Timeout();    
	    showMenu();
	    opt = input.nextInt();
	    }while (opt != 0);
	}
	
	public void makeBooking() throws IOException{
		Scanner input = new Scanner(System.in);
		String cardDetail;
		System.out.println("Are you sure you want to make a booking?(y/n)"); //The booking is only added if it does not exist previously.
		char response = input.next().charAt(0);
		if (response == 'y') {

			System.out.println("Enter the ID of the flight you want to book:\n");
			String ID = input.next();
			f.setFlightID(ID);

			System.out.println("Please enter your card number");
			String number = input.next();
			System.out.println("Do you want to save your card details(y/n)?");
			char save = input.next().charAt(0);
			if(save == 'y') {
				cardDetail = number;
			}
			else {
				cardDetail = "N/A";
			}
			Booking booking = new Booking(Passenger_Name, f.getFlightID(),cardDetail);
		}
	}
	
	public String[] viewMyOrders(String filepath) throws IOException {
		String path = filepath;
		ArrayList<String> records = new ArrayList<String>(); //If the passenger wants to check any bookings, they can only check the bookings which belong to them.
		String resultRow = "No orders so far!";
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while((line = br.readLine())!=null) {
			String[] values = line.split(",");
			if((values[0].contentEquals(Passenger_Name))) {
				resultRow = line;
				records.add(resultRow);
			}
		}
		br.close();
		String[] recordsArray = new String[records.size()];
		records.toArray(recordsArray);
		return recordsArray;
	}
	
	public void cancelBooking(String filepath, String name) {
		
	   	int positionOfTerm = 2;
		int positionID = positionOfTerm - 1;
		int positionName = positionOfTerm -2;
		
		Scanner input = new Scanner(System.in);
		String tempFile = "temp.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
			
		String currentLine;
		String data[];
			
		System.out.println("Enter the ID of the flight you want to cancel"); //Passenger can only cancel the flight if there exist a booking respective to their name.
		String removeTerm = input.next();
			
		try {
			FileWriter filewrite = new FileWriter(tempFile,true);
			BufferedWriter buffwrite = new BufferedWriter(filewrite);
			PrintWriter printwrite = new PrintWriter(buffwrite);
				
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
				
			while((currentLine = br.readLine()) != null) {
				data = currentLine.split(",");
				if(!((data[positionName].equalsIgnoreCase(name)&&data[positionID].equalsIgnoreCase(removeTerm)))) {
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
			File dump = new File(filepath);
			newFile.renameTo(dump);
			System.out.println("Your booking has been successfully cancelled!");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
			
	}
	public int checkID(int ID) throws IOException {
		
		String passengerID = String.valueOf(ID);
    	int check = 0;
    	
    	BufferedReader br = new BufferedReader(new FileReader("publishedFlights.csv"));
    	String line;
    	while((line=br.readLine()) != null) {
    		String[] values = line.split(",");
    		if(values[2].contentEquals(passengerID)) {
    			check = 1;
    		}
    	}
    	br.close();
    	return check;
	}
}




