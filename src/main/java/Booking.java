import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Booking {
	private String path ;
	private String name;
	private String flight_ID;
	private String card;

	public Booking(String name, String flight_ID, String card) throws IOException {
		this.path = "orders.csv";
		this.name = name;
		this.flight_ID = flight_ID;
		this.card = card;
		FileWriter fw = new FileWriter(path, true);
		BufferedWriter br = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(br);
				
		pw.println(name+","+flight_ID+ "," +card);
		pw.flush();
		pw.close();
		System.out.println("Congratulations, you have made the booking! ✅");
	}

	public String getNAME(){
		return this.name;
	}
	public void setNAME(String name){
		this.name = name;
	}
	public String getPATH(){
		return this.path;
	}
	public void setPATH(String path){
		this.path = path;
	}
	public String getCARD(){
		return this.card;
	}
	public void setCARD(String card){
		this.card = card;
	}
	public String getflight_ID(){
		return this.flight_ID;
	}
	public void setflightID(String flight_ID){
		this.flight_ID = flight_ID;
	}
}
