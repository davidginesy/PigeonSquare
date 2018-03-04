package application;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bird2 extends AbstractBird implements Runnable {

	//Message msg;
	SharedAttractors sharedAttractors;

	
	public Bird2(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height,SharedAttractors sharedList, double maxSpeed) {
		super(layer, location, velocity, acceleration, width, height,  maxSpeed);
		this.sharedAttractors = sharedList;
	}
	
	 @Override
	    public Node createView() {
	    	Rectangle rectangle = new Rectangle();
	    	rectangle.setHeight(height);
	    	rectangle.setWidth(height);
	    	rectangle.setStroke(Color.ORANGE);
	        rectangle.setFill(Color.BLACK);
	    	return Utils.createArrowImageView(width, Color.ORANGE);
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
		
		
	}
		
	
		

	
		
	

}
