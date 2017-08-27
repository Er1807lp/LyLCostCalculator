package costcalculator;
import java.awt.ScrollPane;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6763417701685410664L;
	private JPanel contentPane;
	private JTable table;
	private TableModel tabelmodel;
	private JTextPane textPane;

	/**
	 * Create the frame.
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabelmodel = new TableModel();
		table = new JTable(tabelmodel);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getLastIndex()==0)return;
				Part p = PartsHandler.getPartshandler().getPartbyName(tabelmodel.rowData.get(e.getLastIndex()-1)[0]);
				HashMap<Finalpart, Integer> cost = new HashMap<>();
				p.getParts(cost);
				textPane.setText("");
				String text = "";
				for (Finalpart part : cost.keySet()) {
					text+=part.getName()+" x "+cost.get(part)+System.lineSeparator();
				}
				textPane.setText(text);
			}
		});
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 11, 296, 539);
		scrollPane.add(table);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(312, 11, 262, 539);
		contentPane.add(textPane);
		
		
	}

	public JTextPane getTextPane() {
		return textPane;
	}
	
	public TableModel getTabelmodel() {
		return tabelmodel;
	}
}
