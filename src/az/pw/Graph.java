package az.pw;

public class Graph {
	private String[] vertices;
	private boolean[][] adjacencyMatrix;
	private int numberOfVertices;
	
	public Graph(int dimension){
		setVertices(new String[dimension]);
		setAdjacencyMatrix(new boolean [dimension][dimension]);
		setNumberOfVertices(dimension);
	}
	

	public boolean[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public void setAdjacencyMatrix(boolean[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}


	public String[] getVertices() {
		return vertices;
	}


	public void setVertices(String[] vertices) {
		this.vertices = vertices;
	}
	
	public int getIndex(String v){
		int index = -1;
		for(int i = 0; i<vertices.length; i++){
			if(vertices[i].equals(v))
				return i;
		}
		return index;
	}
	
	public void setVertex(int x, int y){
		adjacencyMatrix[x][y] = true;
	}
	
	public void printAdjacencyMatrix(){
		System.out.print("  \t");
		for(String s : vertices){
			System.out.print(s + "\t");
		}
		for(int i = 0; i<adjacencyMatrix.length; i++){
			System.out.print("\n" + vertices[i] + "\t");
			for(int j =0; j<adjacencyMatrix[i].length; j++){
				System.out.print(adjacencyMatrix[i][j] + "\t");
			}
		}
		System.out.println();
	}


	public int getNumberOfVertices() {
		return numberOfVertices;
	}


	public void setNumberOfVertices(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
	} 
	
	public boolean checkIfMatrixIsSymetric(){
		boolean symetric = true;
		
		for(int i = 0; i< adjacencyMatrix.length; i++){
			for(int j =0; j<adjacencyMatrix[i].length; j++){
				if(adjacencyMatrix[i][j] != adjacencyMatrix[j][i]){
					symetric = false;
					System.out.println("not symetric!!: i: "+i+" j: "+j);
				}
			}
		}
		return symetric;
	}
	
}
