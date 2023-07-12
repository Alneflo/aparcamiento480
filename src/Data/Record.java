package Data;

import java.time.LocalDateTime;

import Vehicles.Vehicle;

public class Record {
	private Vehicle vehicle;
	private LocalDateTime enter;
	private LocalDateTime exit;
	
	public Record() {
		
	}
	
	public Record(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setEnterTime() {
		enter = LocalDateTime.now();
	}
	
	public void setExitTime() {
		exit = LocalDateTime.now();
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public LocalDateTime getEnterTime() {
		return enter;
	}
	
	public LocalDateTime getExitTime() {
		return exit;
	}
	
}
