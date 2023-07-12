import java.io.BufferedReader;
import java.io.InputStreamReader;

import Vehicles.NonResidentVehicle;
import Vehicles.OfficialVehicle;
import Vehicles.ResidentVehicle;
import Vehicles.VehicleList;

public class Utilities {
	
	public Utilities() {
		
	}
	
	//Reads a text entered by the user
	
	public static String readInput(String text) {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		boolean repeat;
		
		do {
			repeat = false;
			try {
				System.out.print(text);
				input = sc.readLine();
			}catch(Exception e) {
				System.err.println("Error en el texto de entrada");
				sc = new BufferedReader(new InputStreamReader(System.in));
				repeat = true;
			}
		}while(repeat);
		
		return input;
	}
	
	//Checks if a license plate is valid (4 numbers followed by 3 uppercase letters)
	
	public static boolean validLicensePlate(String licensePlate) {
		return licensePlate.matches("[0-9]{4}[A-Z]{3}");
	}
	
	//Check if License Plate is already registered in each list
	
	public static boolean isOfficial(VehicleList<OfficialVehicle> vehicleList, String licensePlate) {
		return vehicleList.getVehicleByPlate(licensePlate) != null;
	}
	
	public static boolean isNotResident(VehicleList<NonResidentVehicle> vehicleList, String licensePlate){
		return vehicleList.getVehicleByPlate(licensePlate) != null;
	}
	
	public static boolean isResident(VehicleList<ResidentVehicle> vehicleList, String licensePlate) {
		return vehicleList.getVehicleByPlate(licensePlate) != null;
	}
	
	//Waits for the user to press Enter to continue the program
	
	public static void enterToContinue() {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("(Presiona Intro para continuar)");
			sc.readLine();
		}catch(Exception e) {
			sc = new BufferedReader(new InputStreamReader(System.in));
		}
		
		for(int i=1; i <= 50; i++) {
			System.out.print("\n");
		}
		
	}
	
}
