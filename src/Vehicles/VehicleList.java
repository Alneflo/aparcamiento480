package Vehicles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Data.Report;

public class VehicleList<T extends Vehicle>{
	
	private ArrayList<T> list;

	public VehicleList() {
		list = new ArrayList<>();
	}
	
	public ArrayList<T> getList(){
		return list;
	}
	
	public boolean addVehicle(T v) {
		if(!alreadyInList(v)) {
			return list.add(v);
		}else {
			System.err.println("A vehicle with the same License Plate has been already registered.");
			return false;
		}
		
	}
	
	public boolean removeVehicle(T vehi) {
		T removed = null;
		for(T v : list) {
			if (v.compareTo(vehi)==0) {
				removed = v;
			}
		}
		return list.remove(removed);
	}
	
	private boolean alreadyInList(T v) {
		for(Vehicle vehi : list) {
			if (vehi.compareTo(v)==0) {
				return true;
			}
		}
		
		return false;
	}
	
	public void generateReport(ArrayList<Report> r, String fileName) {
		File f = new File("informes\\");
		FileWriter fw = null;
		f.mkdirs();
		
		try {
			f = new File(f.getAbsolutePath() + "\\" + fileName + ".txt");
			f.createNewFile();
			
			fw = new FileWriter(f);
			fw.write("Matrícula\tTiempo Estacionado(min)\tCantidad a pagar\n");
			for(Report rep : r) {
				if(rep.getVehicle() instanceof ResidentVehicle) {
					fw.write(rep.toString() + "\n");
				}
			}
			fw.close();
		}catch(IOException e) {
			System.err.println("No se pudo crear el archivo");
		}
	}
	
	public T getVehicleByPlate(String licensePlate) {
		int i = 0;
		T found = null;
		
		while(found == null && i<list.size()) {
			if(list.get(i).getLicensePlate().equals(licensePlate)) {
				found = list.get(i);
			}
			i++;
		}
		
		return found;
		
	}
	
	public void saveResidents() {
		//Serialize residents
	}

}
