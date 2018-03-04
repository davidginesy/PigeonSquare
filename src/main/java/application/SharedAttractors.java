package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SharedAttractors {
	
	
	List<Attractor> syncAttractor = Collections.synchronizedList(new ArrayList<Attractor>());
	
	
	
	
	public SharedAttractors(List<Attractor> syncAttractor) {
		this.syncAttractor = syncAttractor;
	}

	public void addAttractor(Attractor a) {
		
		synchronized(syncAttractor) {
			syncAttractor.add(a);
		}		
	}
	

	
	public void deleteAttractor(int index) {
		synchronized(syncAttractor) {
			syncAttractor.remove(index);
		}
	}
		
	
	public List<Attractor> getAttractors() {
		synchronized(syncAttractor) {
			return syncAttractor;
		}		
	}	
}