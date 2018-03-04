package application;

import javafx.scene.shape.Rectangle;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class AbstractBird extends Sprite {
	


    public AbstractBird(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height, double maxSpeed) {
        super(layer, location, velocity, acceleration, width, height, maxSpeed);
        
    }

	@Override
	public Node createView() {
		
		return null;
	}

   

	
    

}