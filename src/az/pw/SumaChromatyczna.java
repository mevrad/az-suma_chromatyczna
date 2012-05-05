package az.pw;

import javax.swing.JFrame;

import az.pw.greedycoloring.GreedyColoring;
import az.pw.gui.GUI;
import az.pw.inputparser.ParseInputGraph;
import az.pw.secondalgorithm.SecondAlgorithm;

public class SumaChromatyczna {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		ParseInputGraph p = new ParseInputGraph("/Users/bartosz/Documents/programming/eclipse_workspaces/pw/az/SumaChromatyczna/data/petersen");
//		Graph graph = p.getGraph();
//		GreedyColoring gc = new GreedyColoring(graph);
//		int[] properColorsGc = gc.getProperColors();
//		new SecondAlgorithm(graph);
//		
	//	UserInterface ui = new UserInterface(graph, properColorsGc);
		//ui.setVisible(true);
		new GUI();
	}

}
