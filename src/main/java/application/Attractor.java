package application;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Attractor extends Sprite {
	
	long millisStart = Calendar.getInstance().getTimeInMillis();

    public Attractor(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(layer, location, velocity, acceleration, width, height,0);
    }

    @Override
    public Node createView() {

        double radius = width / 2;

        Circle circle = new Circle( radius);

        circle.setCenterX(radius);
        circle.setCenterY(radius);

        circle.setStroke(Color.GREEN);
        circle.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.3));

        return circle;
    }
    
    
    
    
    //this method check if the current attractor was eat by a bird
    public boolean checkEaten(List<AbstractBird> allBird) {

    	for(int i = 0; i<allBird.size(); i++) {
    		
    	
	        Vector2D tmp = Vector2D.subtract(allBird.get(i).getLocation(), location);
	
	        // The distance is the magnitude of the vector pointing from location to target.
	
	     // If we are closer than 10 pixels...the food is eaten
	        if(tmp.magnitude() < 10) {
	        	return true;
	        }
        }
       
        return false; 

    }


}