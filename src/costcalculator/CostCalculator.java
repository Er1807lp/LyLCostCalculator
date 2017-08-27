package costcalculator;
import java.io.IOException;

public class CostCalculator {

	public static void main(String[] args) throws IOException {
		Gui frame = null;

		try {
			frame = new Gui();
			frame.getTabelmodel().init(PartsHandler.getPartshandler().getFinals());
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
