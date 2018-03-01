package application;

public class Bird extends AbstractBird implements Runnable {

	Message msg;
	

	
	public Bird(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
		super(layer, location, velocity, acceleration, width, height);
		// TODO Auto-generated constructor stub
	}



	
	
	@Override
	public void run() {
		synchronized(msg.allAttractors) {
    		
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
		}
	}
		
	
		

	
		
	

}
