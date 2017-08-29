package costcalculator;

import java.util.ArrayList;
import java.util.HashMap;

public class Middlepart implements Part{

	private ArrayList<String> parts;
	private String name;
	
	public Middlepart(String name,ArrayList<String> parts) {
		this.name = name;
		this.parts = parts;
	}
	
	@Override
	public int getPrice() {
		int price = 0;
		for (String part : parts) {
			price += PartsHandler.getPartshandler().getPartbyName(part).getPrice();
			
		}
		return price;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void getParts(HashMap<Finalpart, Integer> parts) {
		for (String part : this.parts) {
			PartsHandler.getPartshandler().getPartbyName(part).getParts(parts);
		}
	}

}
