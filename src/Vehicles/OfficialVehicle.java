package Vehicles;

public class OfficialVehicle extends Vehicle{
	
	private static final double PRICE_MINUTE = 0;
	private static final String NAME = "Vehículo de residente";
	
	public OfficialVehicle() {
		
	}
	
	public OfficialVehicle(String licensePlate) {
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
