package az.pw.greedycoloring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import az.pw.Graph;

public class GreedyColoring {

	private Graph graph;
	private int[][] colors;
	private List<Degree> degrees;
	private boolean[][] adjacencyMatrix; 
	private int[] properColors;
	
	public GreedyColoring(Graph g) {
		graph = g;
		sortVertices();
		initializeColorsArray();
		startColoring();
	}
	
	private void sortVertices() {
		getDegrees();
		Collections.sort(degrees, new DegreeComparator());
//		System.out.println();
//		for(Degree d : degrees){
//			System.out.println("Degree: " + d.getDegree() + " Index: "+ d.getIndex());
//		}
	}

	private void getDegrees() {
		adjacencyMatrix = graph.getAdjacencyMatrix();
		degrees = new ArrayList<Degree>();

		for (int i = 0; i < adjacencyMatrix.length; i++) {
			degrees.add(new Degree(i,0));
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				if (adjacencyMatrix[i][j])
					degrees.get(i).incrementDegree();
			}
		}
	}
	
	private void initializeColorsArray(){
		colors = new int[graph.getNumberOfVertices()][graph.getNumberOfVertices()];
		for(int i = 0; i<adjacencyMatrix.length; i++){
			for(int j = 0; j< adjacencyMatrix[i].length; j++){
				if(adjacencyMatrix[i][j])
					colors[i][j] = -1;
				else
					colors[i][j] = -2;			
			}
		}
	}
	
	private void startColoring(){
		for(int i = graph.getNumberOfVertices()-1; i>= 0; i--){
			int currentVertex = degrees.get(i).getIndex();
			findMinimalColorForVertex(currentVertex);
		}
		parseAndPrintColors();
	}
	
	private void findMinimalColorForVertex(int index){
		int minimalColor = 0;
		boolean found = false;
		boolean breaked = false;
		
		while(!found){
			for(int i = 0; i<adjacencyMatrix[index].length; i++){
				if(colors[index][i] == minimalColor){
					breaked = true;
					minimalColor++;
					break;
				}
			}
			if(breaked)
				breaked = false;
			else
				found = true;
		}
		updateColorArray(index, minimalColor);
	}
	
	private void updateColorArray(int index, int color){
		for(int i = 0; i<colors.length; i++){
			if(colors[i][index] != -2){
				colors[i][index] = color;
			}
		}
	}
	
	private void parseAndPrintColors(){
		System.out.println();
		properColors = new int[graph.getNumberOfVertices()];
		for(int i = 0; i<colors.length; i++){
			for(int j =0; j<colors[i].length; j++){
				if(colors[j][i] >= 0){
					properColors[i] = colors[j][i];
					System.out.println("Vertex: " + graph.getVertices()[i] + " color: " + colors[j][i]);
					break;
				}
			}
		}
	}
	
	public int[] getProperColors(){
		return properColors;
	}
	
	public int getNumberOfColors(){
		if(properColors == null)
			return -1;
		int max = 0;
		for(int i = 0; i<properColors.length; i++){
			if(max < properColors[i]){
				max = properColors[i];
			}
		}
		return max  + 1;//zaczelismy od 0 ;O
	}
}
