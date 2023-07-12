package Vehicles;

public class ResidentVehicle extends Vehicle{

	private static final double PRICE_MINUTE = 0.002;
	
	public ResidentVehicle(String licensePlate) {
		super(licensePlate);
	}
	
	@Override
	public double getPriceMinute() {
		return PRICE_MINUTE;
	}
	
}
