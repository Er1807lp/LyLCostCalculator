package costcalculator;

import java.util.HashMap;

public class Finalpart implements Part{

	private int price;
	private String name;
	
	public Finalpart(String name,int price) {
		this.price = price;
		this.name = name;
	}
	
	@Override
	public int getPrice() {
		
		return price;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void getParts(HashMap<Finalpart,Integer> parts) {
		if(parts.containsKey(this)){
			parts.put(this, parts.get(this)+1);
		}else{
			parts.put(this, 1);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}

}
