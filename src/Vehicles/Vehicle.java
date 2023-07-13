package Vehicles;

public abstract class Vehicle implements Comparable<Vehicle>{
	private String licensePlate;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	public abstract double getPriceMinute();
	
	public abstract String getName();

	@Override
	public int compareTo(Vehicle o) {
		return this.getLicensePlate().compareTo(o.getLicensePlate());
	}
	
}
