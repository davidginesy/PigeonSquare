package application;

public class Bird extends Vehicle implements Runnable {

	
	
	public Bird(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
		super(layer, location, velocity, acceleration, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		//TODO
		
	
		
	}
	
	// TODO
	// Supprime un attractor si un oiseau le mange (pourri ou non). Voir Sprite.seek
	public void eat(Attractor att) {
		
	}
	public void seek() {
		
	}

}
