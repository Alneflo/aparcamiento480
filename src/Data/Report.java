package Data;

import java.time.Duration;

import Vehicles.Vehicle;

public class Report {
	
	private Vehicle vehicle;
	private long timeMinutes;
	private double amountMoney;
	
	public Report() {
		
	}
	
	public Report(Vehicle vehicle) {
		this.vehicle = vehicle;
		timeMinutes = 0;
		amountMoney = 0;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void addTime(Record r) {
		timeMinutes += Duration.between(r.getEnterTime(), r.getExitTime()).toMinutes();
		setAmount();
	}
	
	private void setAmount() {
		amountMoney = timeMinutes*vehicle.getPriceMinute();
	}
	
	public long getTime() {
		return timeMinutes;
	}
	
	public double getAmount() {
		return amountMoney;
	}
	
	public void resetTime() {
		timeMinutes = 0;
	}
	
	@Override
	public String toString() {
		return vehicle.getLicensePlate() + "\t\t" + timeMinutes + "\t\t\t" + amountMoney;
	}
	
}
