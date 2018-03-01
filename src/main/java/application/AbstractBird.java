package application;

import javafx.scene.shape.Rectangle;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class AbstractBird extends Sprite {

    public AbstractBird(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(layer, location, velocity, acceleration, width, height);
    }

    @Override
    public Node createView() {
    	Rectangle rectangle = new Rectangle();
    	rectangle.setHeight(height);
    	rectangle.setWidth(height);
    	rectangle.setStroke(Color.RED);
        rectangle.setFill(Color.BLACK);
    	return Utils.createArrowImageView(width);
    }

}