package application;

import java.util.List;

import org.omg.CORBA.SystemException;

public class Bird extends AbstractBird implements Runnable {

	//Message msg;
	SharedAttractors sharedAttractors;

	
	public Bird(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height,SharedAttractors sharedList) {
		super(layer, location, velocity, acceleration, width, height);
		this.sharedAttractors = sharedList;
	}



	
	
	@Override
	public void run() {
		while(true) {
			//List<Bird> allBird = sharedAttractors.getBirds();
			List<Attractor> allAttractors = sharedAttractors.getAttractors();
			seek(allAttractors);
			move();
			try {
				
				
				//System.out.println(Thread.currentThread().getName());
				
				Thread.sleep(16);
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		/*synchronized(msg.allAttractors) {
    		
			// Access shared variables and other shared resources
    		//allAttractors = allBird.get(0).seekRandom( allAttractors);
	    		while(true) {
	    			msg.allAttractors = msg.allBird.get(1).seek(msg.allAttractors);
	    			msg.allBird.get(1).move();
	    			//if(!allAttractors.isEmpty()) allBird.forEach(Sprite::move);
	    			msg.allBird.get(1).display();
	    			msg.allAttractors.forEach(Sprite::display);
	    			System.out.println(msg.allAttractors.toString());
	    			System.out.println("dans le pigeon 2");
	    			try {
	    					wait(16);
	    				} catch (InterruptedException e) {
	    					e.printStackTrace();
	    				}
	    		}
		}*/
	}
		
	
		

	
		
	

}
