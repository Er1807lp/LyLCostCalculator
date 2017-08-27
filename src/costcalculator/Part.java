package costcalculator;
import java.util.HashMap;

public interface Part {

	
	public int getPrice();
	
	public String getName();
	
	public void getParts(HashMap<Finalpart,Integer> parts);
	
}
