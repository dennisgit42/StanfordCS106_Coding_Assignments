import acm.program.*;
import javax.swing.*;
import java.awt.event.*;

public class nameSurfer extends Program implements nameSurferConstants {
	
	JTextField nameField;
	nameSurferDatabase database;
	private nameSurferGraph graph;
	
	public static void main(String[] args) {
		new nameSurfer().start(args);
	}
	
	public void init() {
		database = new nameSurferDatabase("E:\\Stanford CS 106\\Eclipse for Win\\nameSurfer\\bin\\names-data.txt");
		JLabel label = new JLabel("name");
		nameField = new JTextField(30);
		nameField.addActionListener(this);
		JButton graphButton = new JButton("Graph");
		JButton clearButton = new JButton("Clear");
		add (label, NORTH);
		add (nameField, NORTH);
		add (graphButton, NORTH);
		add (clearButton, NORTH);
		addActionListeners();	
		graph = new nameSurferGraph();
		graph.update();
		add (graph);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameField) {
			String name = nameField.getText();
			nameSurferEntry entry = database.findEntry(name);						
			if(entry != null) {
				graph.addEntry(entry);		
			}
		} 
		if (e.getActionCommand().equals("Graph")) {
			String name = nameField.getText();
			nameSurferEntry entry = database.findEntry(name);						
			if(entry != null) {
				graph.addEntry(entry);		
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			String name = nameField.getText();
			nameSurferEntry entry = database.findEntry(name);
			if (entry != null) {
				graph.deleteEntry(entry);
			}
		}
	}
	
}
