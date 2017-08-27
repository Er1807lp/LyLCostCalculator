package costcalculator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PartsHandler {

	private HashMap<String, Part> partbyname = new HashMap<>();
	private HashMap<String, Part> overwrittenpartbyname = new HashMap<>();
	private static final String finalparts = "finalparts.txt";
	private static final String middleparts = "middleparts.txt";
	private static final String overwriteparts = "finaloverwriteparts.txt";

	private static PartsHandler partshandler;

	public static PartsHandler getPartshandler() {
		if (partshandler == null) {
			partshandler = new PartsHandler();
			try {
				partshandler.loadFinalparts(FileHandler.readfile(finalparts), false);
				partshandler.loadMiddleparts(FileHandler.readfile(middleparts));
				partshandler.loadFinalparts(FileHandler.readfile(overwriteparts), true);
			} catch (Exception e) {
				System.out.println("Error");
			}
		}

		return partshandler;
	}

	public Part getPartbyName(String name) {
		if (overwrittenpartbyname.containsKey(name)) {
			return overwrittenpartbyname.get(name);
		} else if (partbyname.containsKey(name)) {
			return partbyname.get(name);
		}
		System.out.println("Item "+name+" not found");
		return null;
	}

	public HashMap<String, Part> getOverwrittenpartbyname() {
		return overwrittenpartbyname;
	}

	public void loadFinalparts(ArrayList<String> str, boolean overwrite) {
		for (String string : str) {

			String[] arr2 = string.split("\t");

			if (overwrite) {
				overwrittenpartbyname.put(arr2[0], new Finalpart(arr2[0], Integer.parseInt(arr2[1])));
			} else {
				partbyname.put(arr2[0], new Finalpart(arr2[0], Integer.parseInt(arr2[1])));
			}
		}
	}

	public void loadMiddleparts(ArrayList<String> str) {
		for (String string : str) {

			String[] arr2 = string.split("\t");
			ArrayList<String> parts = new ArrayList<>();
			for (int j = 1; j < arr2.length - 1; j++) {
				if (!arr2[j].equals("---")) {
					String[] arr3 = arr2[j].split(" x ");

					for (int k = 0; k < Integer.parseInt(arr3[0]); k++) {
						parts.add(arr3[1]);
					}
				}
			}
			partbyname.put(arr2[0], new Middlepart(arr2[0], parts));
		}
	}

	public ArrayList<String[]> getFinals() {
		ArrayList<String[]> arr = new ArrayList<>();
		HashMap<String, String> temp = new HashMap<>();
		ArrayList<String[]> temp2 = new ArrayList<>();
		for (Part part : partbyname.values()) {
				temp.put(part.getName(), "False");
		}
		for (Part part : overwrittenpartbyname.values()) {
			temp.put(part.getName(), "True");
		}
		for (String part : temp.keySet()) {
			if(getPartbyName(part)instanceof Finalpart)
			temp2.add(new String[]{part,getPartbyName(part).getPrice()+"",temp.get(part)});
		}
		Collections.sort(temp2, new Comparator<String[]>() {
			@Override
			public int compare(String[] arg0, String[] arg1) {
				return  arg0[0].compareTo(arg1[0]);
			}
	    });
		arr.addAll(temp2);
		temp2.clear();
		for (String part : temp.keySet()) {
			if(getPartbyName(part)instanceof Middlepart)
			temp2.add(new String[]{part,getPartbyName(part).getPrice()+"",temp.get(part)});
		}
		Collections.sort(temp2, new Comparator<String[]>() {
			@Override
			public int compare(String[] arg0, String[] arg1) {
				return  arg0[0].compareTo(arg1[0]);
			}
	    });
		arr.addAll(temp2);
		return arr;
	}

	public void saveChanges() {
		try {
			FileHandler.save(overwrittenpartbyname,overwriteparts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
