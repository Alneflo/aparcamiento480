
import java.util.ArrayList;

import Data.Record;
import Data.Report;
import Vehicles.NonResidentVehicle;
import Vehicles.OfficialVehicle;
import Vehicles.ResidentVehicle;
import Vehicles.VehicleList;

public class ParkingManager {
	
	private VehicleList<OfficialVehicle> officialVList;
	private VehicleList<ResidentVehicle> residentVList;
	private VehicleList<NonResidentVehicle> nonResidentVList;
	
	private ArrayList<Record> recordList;
	private ArrayList<Report> reportList;
	
	private double price;
	
	public ParkingManager() {
		officialVList = new VehicleList<>();
		nonResidentVList = new VehicleList<>();
		residentVList = new VehicleList<>();
		recordList = new ArrayList<Record>();
		reportList = new ArrayList<Report>();
		
		price = 0;
	}
	
	//Depending on if the license plate belongs to a resident vehicle, an official one or a non resident vehicle it will record the entry to the parking differently
	
	public int recordEntry(String licensePlate) {
		Record r = null;
		int licenseBelonging;
		
		if(Utilities.validLicensePlate(licensePlate)) {
			if(!hasEntered(licensePlate)) {
				licenseBelonging = residentOrNonOrOfficial(licensePlate);
				switch(licenseBelonging) {
					case 1:
						r = new Record(residentVList.getVehicleByPlate(licensePlate));
						setEnterRecord(r);
					break;
					case 0:
						r = new Record(officialVList.getVehicleByPlate(licensePlate));
						setEnterRecord(r);
					break;
					case -1:
						if(!Utilities.isNotResident(nonResidentVList, licensePlate)) {
							nonResidentVList.addVehicle(new NonResidentVehicle(licensePlate));
						}
						r = new Record(nonResidentVList.getVehicleByPlate(licensePlate));
						setEnterRecord(r);
					break;
				}
				return licenseBelonging;
				
			}else {
				return 41; //Already entered Error
			}
		}else {
			return 40; //Invalid license plate Error
		}
		
		
	}
	
	public void setEnterRecord(Record r) {
		recordList.add(r);
		recordList.get(recordList.indexOf(r)).setEnterTime();
	}
	
	public void setExitRecord(Record r) {
		recordList.get(recordList.indexOf(r)).setExitTime();
	}
	
	//Depending on if the license plate belongs to a resident vehicle, an official one or a non resident vehicle it will record the exit of the parking differently
	
	public int recordExit(String licensePlate) {
		boolean repeat = true;
		Record r;
		Report rP;
		int licenseBelonging;
		
		if(Utilities.validLicensePlate(licensePlate)) {
			if(hasEntered(licensePlate)) {
				r = getLastRecordByPlate(licensePlate);
				if(r!=null) {
					r.setExitTime();
				}
				licenseBelonging = residentOrNonOrOfficial(licensePlate);
				switch(licenseBelonging) {
					case 1:
						do {
							rP = getReportByRecord(r);
							if(rP!=null) {
								rP.addTime(r);
								repeat = false;
							}else {
								newReport(r);
							}
						}while(repeat);
						setExitRecord(r);
					break;
					case 0:
						setExitRecord(r);
					break;
					case -1:
						rP = new Report(r.getVehicle());
						setExitRecord(r);
						rP.addTime(r);
						price = rP.getAmount();
					break;
				}
				return licenseBelonging;
			}else {
				return 42;
			}
		}else {
			return 40;
		}
		
	}
	
	public double getPrice() {
		return price;
	}
	
	public void removeNonResident(String licensePlate) {
		nonResidentVList.removeVehicle((NonResidentVehicle)getLastRecordByPlate(licensePlate).getVehicle());
		recordList.remove(getLastRecordByPlate(licensePlate));
	}
	
	//Register a single vehicle
	
	public int registerVehicle(String licensePlate, boolean official) {
		
		if(!alreadyStored(licensePlate)) {
			if(Utilities.validLicensePlate(licensePlate)) {
				if(official) {
					officialVList.addVehicle(new OfficialVehicle(licensePlate));
					return 0;
				}else{
					residentVList.addVehicle(new ResidentVehicle(licensePlate));
					return 1;
				}
			}
		}else {
			return 43;
		}
		
		return -2;//This code means that the other part has to do nothing
	}
	
	//Add a new report to the List of reports
	
	public boolean newReport(Record r) {
		return reportList.add(new Report(r.getVehicle()));
	}
	
	//Starts a new month resetting the list of official vehicles and resetting the time of the Reports that have been made
	
	public void startMonth() {
		ArrayList<OfficialVehicle> officialInsideParking = new ArrayList<>();
		
		for(OfficialVehicle vehicle : officialVList.getList()) {
			for(Record r : recordList) {
				if(r.getVehicle().compareTo(vehicle) == 0 && r.getExitTime() != null) {
					officialInsideParking.add(vehicle);
				}
			}
		}
		
		for(OfficialVehicle v : officialInsideParking) {
			officialVList.removeVehicle(v);
		}
		
		for(Report rp : reportList) {
			rp.resetTime();
		}
		
	}
	
	public ArrayList<Report> getReportList(){
		return reportList;
	}
	
	//Creates a report file with the name entered by text
	
	public int createReport(String name) {
		if(name != "0") {
			residentVList.generateReport(reportList, name);
			return 1;
		}
		return 0;
	}
	
	//Checks if a vehicle with the matching license plate has already entered the parking
	
	public boolean hasEntered(String licensePlate) {
		for(Record r : recordList) {
			if(r.getVehicle().getLicensePlate().equals(licensePlate)) {
				if(r.getEnterTime() != null && r.getExitTime() == null) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Returns a 1 if the license plate belongs to a Resident, a 0 if it is an Official vehicle and a -1 if it is a non resident vehicle
	
	public int residentOrNonOrOfficial(String licensePlate) {
		if(Utilities.isResident(residentVList, licensePlate)) {
			return 1;
		}else if(Utilities.isOfficial(officialVList, licensePlate)) {
			return 0;
		}else {
			return -1;
		}
	}
	
	//Returns the last record registered with a matching license plate than the one entered
	
	public Record getLastRecordByPlate(String licensePlate) {
		Record temp = null;
		for(Record r : recordList) {
			if(r.getVehicle().getLicensePlate().equals(licensePlate)) {
				temp = r;
			}
		}
		
		return temp;
	}
	
	//Returns the report that has the same vehicle as the record entered
	
	public Report getReportByRecord(Record rec) {
		for(Report r : reportList) {
			if(r.getVehicle().equals(rec.getVehicle())) {
				return r;
			}
		}
		return null;
	}
	
	public boolean alreadyStored(String licensePlate) {
		return Utilities.isOfficial(officialVList, licensePlate) || Utilities.isResident(residentVList, licensePlate) || Utilities.isNotResident(nonResidentVList, licensePlate);
	}
	
	public String getError(int code) {
		switch(code) {
		case 40:
		return ErrorStrings.error40();
		case 41:
		return ErrorStrings.error41();
		case 42:
		return ErrorStrings.error42();
		case 43:
		return ErrorStrings.error43();
		case 44:
		return ErrorStrings.error44();
		default:
		return "";
		}
	}
	
}
