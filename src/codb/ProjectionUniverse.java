package codb;

import java.util.ArrayList;
import java.util.List;


public class ProjectionUniverse {
	private List<Projection> projections;

	public ProjectionUniverse(){
		projections = new ArrayList<Projection>();
	}
	
	public void add(Projection projection){
		projections.add(projection);
	}
	
	public Projection get(int index){
		return projections.get(index);
	}
}
