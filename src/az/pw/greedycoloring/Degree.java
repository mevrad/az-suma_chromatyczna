package az.pw.greedycoloring;

public class Degree {

	private Integer index;
	private Integer degree;
	
	public Degree(){
		index = 0;
		degree = 0;
	}
	
	public Degree(int index, int degree){
		this.index = index;
		this.degree = degree;
	}
	
	public Integer getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public Integer getDegree() {
		return degree;
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void incrementDegree(){
		degree++;
	}
}
