package costcalculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class FileHandler {

	public static ArrayList<String> readfile(File f) throws IOException {
		ArrayList<String> strings = new ArrayList<>();

		BufferedReader bfr = new BufferedReader(new FileReader(f));

		String line = "";

		while ((line = bfr.readLine()) != null)
			strings.add(line);

		bfr.close();
		return strings;

	}
	
	public static ArrayList<String> readfile(String f) throws IOException{
		return readfile(new File(f));
	}

	public static void save(HashMap<String, Part> overwrittenpartbyname,File f) throws IOException {
		BufferedWriter bfw = new BufferedWriter(new FileWriter(f));
		boolean first = true;
		for (Part part : overwrittenpartbyname.values()) {
			if(!first)bfw.newLine();
			bfw.write(part.getName()+"\t"+part.getPrice());
			first = false;
		}
		bfw.close();
	}
	public static void save(HashMap<String, Part> overwrittenpartbyname,String f) throws IOException {
		save(overwrittenpartbyname, new File(f));
	}

	public static void reset() throws IOException {
		copy("finalparts.txt");
		copy("middleparts.txt");
		copy("finaloverwriteparts.txt");
		System.exit(0);
	}
	
	private static void copy(String name) throws IOException{
		InputStream s = FileHandler.class.getResourceAsStream(name);
		FileOutputStream fos = new FileOutputStream(name);
		byte[] buffer = new byte[1028];
		int read = 0;

		while ((read = s.read(buffer)) != -1) {
			fos.write(buffer, 0, read);
		}

		s.close();
		fos.close();
	}

}
