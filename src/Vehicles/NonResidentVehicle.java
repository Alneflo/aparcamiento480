package Vehicles;

public class NonResidentVehicle extends Vehicle{

	private static final double PRICE_MINUTE = 0.02;
	private static final String NAME = "Vehículo no residente";
	
	public NonResidentVehicle() {
		
	}
	
	public NonResidentVehicle(String licensePlate) {
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
