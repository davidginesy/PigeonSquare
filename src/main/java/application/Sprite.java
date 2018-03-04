package application;

import java.util.List;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region {
	
	

    Vector2D location;
    Vector2D velocity;
    Vector2D acceleration;

    double maxForce = Settings.SPRITE_MAX_FORCE;
    double maxSpeed;

    Node view;

    // view dimensions
    double width;
    double height;
    double centerX;
    double centerY;
    double radius;

    double angle;

    Layer layer = null;

    public Sprite( Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height, double maxSpeed) {

        this.layer = layer; 
        
        this.maxSpeed = maxSpeed;

        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;

        this.view = createView();

        setPrefSize(width, height);

        // add view to this node
        getChildren().add( view);

        // add this node to layer
        layer.getChildren().add( this);

    }

    public abstract Node createView();

    
    public synchronized void deleteView(Node view) {
	    	getChildren().remove(view);
	    	layer.getChildren().remove(view);
    }
    
    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    public synchronized void move() {

        // set velocity depending on acceleration
        velocity.add(acceleration);

        // limit velocity to max speed
        velocity.limit(maxSpeed);

        // change location depending on velocity
        location.add(velocity);

        // angle: towards velocity (ie target)
        angle = velocity.heading2D();

        // clear acceleration
        acceleration.multiply(0);
    }
    /**
     * Move Sprite randomly
     */
    public List<Attractor> seekRandom(List<Attractor> target){
    	    	Random rdm = new Random();
    	    	int index = -1;
    	    	double d=9999;
    	    	Vector2D desired = new Vector2D(0, 0); 
    	    	for(int i = 0; i<target.size(); i++) {
    	    		
    	    	
    		        Vector2D tmp = Vector2D.subtract(target.get(i).getLocation(), location);
    		
    		        // The distance is the magnitude of the vector pointing from location to target.
    		
    		        if(tmp.magnitude() < d) {
    		        	d = tmp.magnitude();
    		        	desired = tmp;
    		        	desired.normalize();
    		        	index = i;
    		        	
    		        }
    	        }
    	        // If we are closer than 10 pixels...the food is eaten
    	        if (d < 10) {

    	           /** 
    	            * Supprime la view et l'objet Attractor lorsqu'il est touch�
    	            */
    	        	target.get(index).deleteView(target.get(index).view);
    	        	target.remove(index);
    	        } 
    	        // Otherwise, proceed at maximum speed.
    	        else {
    	            desired.multiply(maxSpeed);
    	            // The usual steering = desired - velocity
    	        Vector2D steer = Vector2D.subtract(desired, velocity);
    	        steer.x = -steer.x * rdm.nextFloat();
    	        steer.y = -steer.y * rdm.nextFloat();
    	        steer.limit(maxForce);
    	       
    	        applyForce(steer);
    	        }
    	        return target;

    }
    
    
    
    
    
    

    /**
     * Move sprite towards target
     */
    public void seek(List<Attractor> target) {
    	

    	double d=9999;
    	Vector2D desired = new Vector2D(0, 0); 
    	for(int i = 0; i<target.size(); i++) {
    		
    	
	        Vector2D tmp = Vector2D.subtract(target.get(i).getLocation(), location);
	
	        // The distance is the magnitude of the vector pointing from location to target.
	
	        if(tmp.magnitude() < d) {
	        	d = tmp.magnitude();
	        	desired = tmp;
	        	desired.normalize();
	        
	        }
        }
        
        desired.multiply(maxSpeed);
        // The usual steering = desired - velocity
        Vector2D steer = Vector2D.subtract(desired, velocity);
        steer.limit(maxForce);
       
        applyForce(steer);
        
        

        

    }

    /**
     * Update node position
     */
    public synchronized void display() {

        relocate(location.x - centerX, location.y - centerY);

        setRotate(Math.toDegrees( angle));

    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getLocation() {
        return location;
    }

    public void setLocation( double x, double y) {
        location.x = x;
        location.y = y;
    }

    public void setLocationOffset( double x, double y) {
        location.x += x;
        location.y += y;
    }

}