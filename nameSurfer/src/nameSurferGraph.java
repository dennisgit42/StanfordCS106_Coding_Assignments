/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import acm.util.*;
import java.awt.*;


public class nameSurferGraph extends GCanvas
	implements nameSurferConstants, ComponentListener {

	private ArrayList<nameSurferEntry> entriesDisplayed = new ArrayList<nameSurferEntry>();
	private HashMap<nameSurferEntry, Color> colorsToNames = new HashMap<nameSurferEntry, Color>();
	
	private double xNextDecade;
	private double maxY;
	private double minY;
	private int nNamesPlotted = 0;
	private GLabel pointLabel;
	private String rankStr;

	
	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public nameSurferGraph() {
		addComponentListener(this);
		
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {		
		entriesDisplayed.removeAll(entriesDisplayed);
		update();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(nameSurferEntry entry) {
		if (entriesDisplayed.contains(entry)) return;
		entriesDisplayed.add(entry);
		nNamesPlotted++;
		colorsToNames.put(entry, getColor());
		update();
	}
	
	public void deleteEntry(nameSurferEntry entry) {
		entriesDisplayed.remove(entry);
		colorsToNames.remove(entry);
		update();
	}
	
	private Color getColor() {
		if (nNamesPlotted > 10) nNamesPlotted = 1;
		switch(nNamesPlotted) {
		case 1: return Color.RED;
		case 2: return Color.BLACK;
		case 3: return Color.BLUE;
		case 4: return Color.GREEN;
		case 5: return Color.CYAN;
		case 6: return Color.DARK_GRAY;
		case 7: return Color.MAGENTA;
		case 8: return Color.LIGHT_GRAY;
		case 9: return Color.ORANGE;
		case 10: return Color.PINK;		
		}
		return null;
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		xNextDecade = getWidth() / NDECADES;
		minY = GRAPH_MARGIN_SIZE;
		maxY = getHeight() - GRAPH_MARGIN_SIZE;
		for (int i = 0; i < NDECADES; i++) {
			double x1 = 5 + (xNextDecade) * i;
			double y1 = 0;
			double x2 = 5 + (xNextDecade) * i;
			double y2 = getHeight();
			GLine decadeLine = new GLine(x1, y1, x2, y2);
			int year = 1900 + i * 10;
			GLabel decadeLabel = new GLabel( "" + year , x2, y2 - GRAPH_MARGIN_SIZE);
			decadeLabel.move(0, decadeLabel.getHeight());
			add(decadeLine);
			add(decadeLabel);
		}
		GLine topBorder = new GLine(0, minY, getWidth(), minY);
		GLine bottomBorder = new GLine(0, maxY, getWidth(), maxY);
		add (topBorder);
		add (bottomBorder);
		
		System.out.println("xNextDecade is: " + xNextDecade);
		for (int i = 0 ; i < entriesDisplayed.size(); i++) {
			plotLine(entriesDisplayed.get(i));
		}
	}
	
	private void plotLine(nameSurferEntry entry) {
		for (int i = 0; i < NDECADES - 1; i++) {			
			double x1 = getPointX(i);
			double y1 = getPointY(i, entry);
			rankStr = getRankStr(i, entry);
			pointLabel = new GLabel(entry.getName() + " " + rankStr, x1, y1);
			pointLabel.setColor(colorsToNames.get(entry));
			add (pointLabel);
			
			double x2 = getPointX(i + 1);
			double y2 = getPointY(i + 1, entry);
			rankStr = getRankStr(i + 1, entry);
			pointLabel = new GLabel(entry.getName() + " " + rankStr, x2, y2);
			pointLabel.setColor(colorsToNames.get(entry));
			add (pointLabel);

			GLine line = new GLine(x1, y1, x2, y2);
			line.setColor(colorsToNames.get(entry));
			add(line);		
			
		}
	}
	
	private double getPointX(int i) {
		return 5 + (xNextDecade) * i;
		
	}
	
	private double getPointY(int i, nameSurferEntry entry) {
		if (entry.getRank(i) == 0) {
			return maxY; 
		} else {		
			double rankPercentile = (double) entry.getRank(i) / MAX_RANK;
			double yPixelsFromTop = rankPercentile * (maxY - minY);
			double y = (entry.getRank(i) == 0) ? maxY : (minY + yPixelsFromTop);
			System.out.println("Y coor of index " + i + " is " + y);
			return y;
		}
	}
	
	private String getRankStr(int index, nameSurferEntry entry) {
		if (entry.getRank(index) == 0) {
			rankStr = "*";
		} else {
			rankStr = "" + entry.getRank(index);
		}
		return rankStr;
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
