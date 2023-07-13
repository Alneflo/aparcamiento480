package Vehicles;

public class ResidentVehicle extends Vehicle{

	private static final double PRICE_MINUTE = 0.002;
	private static final String NAME = "Vehículo oficial";
	
	public ResidentVehicle(String licensePlate) {
		super(licensePlate);
	}
	
	@Override
	public double getPriceMinute() {
		return PRICE_MINUTE;
	}
	
	@Override
	public String getName(){
		return NAME;
	}
	
}
