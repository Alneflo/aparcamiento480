package Vehicles;

public class NonResidentVehicle extends Vehicle{

	private static final double PRICE_MINUTE = 0.02;
	
	public NonResidentVehicle() {
		
	}
	
	public NonResidentVehicle(String licensePlate) {
		super(licensePlate);
	}
	
	@Override
	public double getPriceMinute() {
		return PRICE_MINUTE;
	}
}
