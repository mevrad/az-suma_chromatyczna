package az.pw.greedycoloring;

import java.util.Comparator;

public class DegreeComparator implements Comparator<Degree>{

	@Override
	public int compare(Degree o1, Degree o2) {
		return o1.getDegree().compareTo(o2.getDegree()); 
	}

}
