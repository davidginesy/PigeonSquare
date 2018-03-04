package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedAttractors {
	
	List<Attractor> syncAttractor = Collections.synchronizedList(new ArrayList<Attractor>());
	List<Bird> syncBird = Collections.synchronizedList(new ArrayList<Bird>());
	
	public SharedAttractors(List<Attractor> syncAttractor,List<Bird> syncBird ) {
		this.syncAttractor = syncAttractor;
		this.syncBird = syncBird;
	}

	public void addAttractor(Attractor a) {
		
		synchronized(syncAttractor) {
			syncAttractor.add(a);
		}
		
	}
	
	public void addBird(Bird b, int index) {
		synchronized(syncBird) {
			syncBird.remove(index);
			syncBird.add(b);
		}
		
	}
	
	public void deleteAttractor(int index) {
		synchronized(syncAttractor) {
			syncAttractor.remove(index);
		}
		
	}
	public synchronized void deleteBird(int index) {
		syncBird.remove(index);
	}
	
	public List<Attractor> getAttractors() {
		synchronized(syncAttractor) {
			return syncAttractor;
		}		
	}
	
	public List<Bird> getBirds() {
		synchronized(syncBird) {
			return syncBird;
		}
		
	}
	
	
	
	
}
