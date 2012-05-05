package az.pw.secondalgorithm;

import java.util.Arrays;

import az.pw.Graph;
import az.pw.greedycoloring.GreedyColoring;

public class SecondAlgorithm {
	
	private Graph graph;
	
	
	public SecondAlgorithm(Graph g){
		graph = g;
		System.out.println("\n\n\nSecond algorithm\n\n\n");
		Graph newGraph = prepareNewGraph();
		GreedyColoring gc = new GreedyColoring(newGraph);
		int numberOfColorsForHn = gc.getNumberOfColors();
		calculateNumberOfColors(numberOfColorsForHn);
	}
	
	private Graph prepareNewGraph(){
		boolean[][] newAdjencyMatrix = prepareNewAdjacencyMatrix();
		String[] newVertices = prepareNewVertices();
		
		Graph newGraph = new Graph(newVertices.length);
		newGraph.setVertices(newVertices);
		newGraph.setAdjacencyMatrix(newAdjencyMatrix);
		newGraph.printAdjacencyMatrix();
		if(!graph.checkIfMatrixIsSymetric()){
			System.out.println("ERROR: Matrix is not symetric");
		}
		return newGraph;
	}
	
	private boolean[][] prepareNewAdjacencyMatrix(){
		boolean[][] adjacencyMatrix = graph.getAdjacencyMatrix();
		
		int newDimensions =  adjacencyMatrix.length * adjacencyMatrix.length + adjacencyMatrix.length;
		System.out.println(newDimensions);
		boolean[][] newAdjacencyMatrix = new boolean[newDimensions][newDimensions];
		//fill new matrix with true
		for(boolean[] row : newAdjacencyMatrix)
			Arrays.fill(row, true);
		//no loops - fill diagonal with false
		for(int i = 0; i<newAdjacencyMatrix.length; i++){
			newAdjacencyMatrix[i][i] = false;
		}
		//insert original matrix into the new one
		for(int i = 0; i<adjacencyMatrix.length; i++){
			for(int j=0; j<adjacencyMatrix[i].length; j++){
				newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}	
		
		return newAdjacencyMatrix;
	}
	
	private String[] prepareNewVertices(){
		String[] vertices = graph.getVertices();
		int newDimensions = vertices.length * vertices.length + vertices.length;
		String[] newVertices = new String[newDimensions];
		for(int i = 0; i<newDimensions; i++){
			if(i<vertices.length)
				newVertices[i] = vertices[i];
			else
				newVertices[i] = "K"+i;
		}
		return newVertices;
	}
	
	private int calculateNumberOfColors(int numOfColorsForHn){
		int numOfColors = 0;
		int p = graph.getNumberOfVertices();
		System.out.println("Number of vertices:" + p);
		System.out.println("Number of colors for hn: " + numOfColorsForHn);
		int part1 = ((p*p)*(p*p+1))/2;
		System.out.println("Part1: "+part1);
		numOfColors = (numOfColorsForHn - part1)/(p*p);
	//	numOfColors = (numOfColorsForHn - ((p*p*(p*p+1))/2))/p*p;
		System.out.println("Number of colors: " + numOfColors);
		return numOfColors;
	}
	
}
