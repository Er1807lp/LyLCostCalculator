package costcalculator;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8847517730095911474L;
	final String[] columnNames = new String[] { "Name", "Cost", "Edited" };
	ArrayList<String[]> rowData = new ArrayList<>(); 

	public String getColumnName(int col) {

		try {
			return columnNames[col];
		} catch (Exception e) {
			return "Error";
		}
	}

	public int getRowCount() {
		return rowData.size() + 1;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getValueAt(int row, int col) {
		if (row == 0) {
			return getColumnName(col);
		}
		try {
			return rowData.get(row-1)[col];
		} catch (Exception e) {
			return "Error";
		}
	}

	public boolean isCellEditable(int row, int col) {
		if(row==0){
			return false;
		}else if(col!=1){
			return false;
		}
		return true;
	}

	public void init(ArrayList<String[]> rowdata) {
		this.rowData = rowdata;
		fireTableDataChanged();
	}

	
	
	public void setValueAt(Object value, int row, int col) {
		rowData.get(row-1)[col] = (String) value;
		PartsHandler.getPartshandler().getOverwrittenpartbyname().put(rowData.get(row-1)[0], new Finalpart(rowData.get(row-1)[0], Integer.parseInt(rowData.get(row-1)[1])));
		PartsHandler.getPartshandler().saveChanges();
		init(PartsHandler.getPartshandler().getFinals());
	}

}
