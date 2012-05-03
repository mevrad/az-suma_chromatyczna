package az.pw.inputparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import az.pw.Graph;

public class ParseInputGraph {

	private Graph graph;
	
	public ParseInputGraph(String filePath){
		parse(filePath);
	}
	
	private void parse(String filePath){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			if(line != null)
				processFirstLine(line);
			while((line = reader.readLine()) != null){
				processLine(line);
			}
			reader.close();
			graph.printAdjacencyMatrix();
			verifyInput();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void processFirstLine(String line) {
		String[] vertices = line.split(" ");
		graph = new Graph(vertices.length);
		graph.setVertices(vertices);
	}

	private void processLine(String line){
		String[] connections = line.split(" ");
		int currentVertexIndex = graph.getIndex(connections[0]);
		for(int i=1; i<connections.length; i++){
			int adjacentVertexIndex = graph.getIndex(connections[i]);
			graph.setVertex(currentVertexIndex, adjacentVertexIndex);
		}
	}
	
	private void verifyInput(){
		if(!graph.checkIfMatrixIsSymetric()){
			System.out.println("ERROR: Matrix is not symetric");
		}
	}
	
	public Graph getGraph(){
		return graph;
	}
}
