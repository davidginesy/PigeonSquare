package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application /*implements Runnable*/{

    static Random random = new Random();
    
    long start;
    long elapsedTimeMillis;
    long lifeTime =1500;
    
    Layer playfield;
    
    List<Attractor> allAttractors = new ArrayList<>();

    List<AttractorOld> allAttractorsOld = new ArrayList<>();
    List<AbstractBird> allBird = new ArrayList<>();
    SharedAttractors sharedList = new SharedAttractors(allAttractors);

    //List<Bird> allBird = new ArrayList<>();
    //SharedAttractors sharedList = new SharedAttractors(allAttractors);

    AnimationTimer gameLoop;

    Vector2D mouseLocation = new Vector2D( 0, 0);

    Scene scene;

    MouseGestures mouseGestures = new MouseGestures();

    @Override
    public void start(Stage primaryStage) {

        // create containers
        BorderPane root = new BorderPane();

        // playfield for our Sprites
        playfield = new Layer( Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        // entire game as layers
        Pane layerPane = new Pane();

        layerPane.getChildren().addAll(playfield);

        root.setCenter(layerPane);

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        // add content
        prepareGame();

        // add mouse location listener
        addListeners();
        
        // run animation loop
        startGame();
        



    }

    private void prepareGame() {

        // add bird
    		for( int i = 0; i < 5; i++) {
            addSpecificBird(2);
        }
     	for( int i = 0; i < 5; i++) {
            addSpecificBird1(3);
        }
     	for( int i = 0; i < 5; i++) {
            addSpecificBird2(6);
        }
    	
    	
        /*for( int i = 0; i < Settings.BIRD_COUNT; i++) {
            addBird();
        }*/
    	
    	
        //allBird.forEach(Sprite::display);
        // add attractors
       /* for( int i = 0; i < Settings.ATTRACTOR_COUNT; i++) {
            addAttractors();
        }*/


    }

    
    private void startGame() {
    	
    	//System.out.println("nb thread : "+Thread.activeCount());
    	allBird.forEach(Sprite::display);
    allAttractors.forEach(Sprite::display);

	  
	    	
	    	
	    	// start game
	        gameLoop = new AnimationTimer() {
	
				@Override
				public void handle(long now) {
					
					allBird.forEach(Sprite::display);
			        allAttractors.forEach(Sprite::display);
			        
					for(int i = 0; i<allAttractors.size(); i++) {
						
						if(allAttractors.get(i).checkEaten(allBird)) {
							
							//addAttractorsOld(allAttractors.get(0).location.x,allAttractors.get(0).location.y);
							
							allAttractors.get(0).deleteView(allAttractors.get(0).view);
							allAttractors.remove(0);
							
							sharedList.deleteAttractor(0);
						}
						if(!allAttractors.isEmpty()) {
							if((Calendar.getInstance().getTimeInMillis()-allAttractors.get(i).millisStart)>lifeTime) {
								
								addAttractorsOld(allAttractors.get(0).location.x,allAttractors.get(0).location.y);
								
								allAttractors.get(0).deleteView(allAttractors.get(0).view);
								allAttractors.remove(0);
							
							}
						}	
					}
					
					for(int i = 0; i<allAttractorsOld.size(); i++) {
						if((Calendar.getInstance().getTimeInMillis()-allAttractorsOld.get(i).millisStart)>lifeTime) {
							
							allAttractorsOld.get(i).deleteView(allAttractorsOld.get(i).view);
							allAttractorsOld.remove(i);
							
							
						}
					}
					
				}
	        	
	        };
	        gameLoop.start();

    }
    
    
    
    
    
    
    
  
    /**
     * Add single vehicle to list of bird and to the playfield
     */
    private void addBird() {

        Layer layer = playfield;

        // random location
        double x = random.nextDouble() * layer.getWidth();
        double y = random.nextDouble() * layer.getHeight();

        // dimensions
        double width = 50;
        double height = width / 2.0;

        // create bird data
        Vector2D location = new Vector2D( x,y);
        Vector2D velocity = new Vector2D( 0,0);
        Vector2D acceleration = new Vector2D( 0,0);

        // create sprite and add to layer
        Bird bird = new Bird( layer, location, velocity, acceleration, width, height, sharedList, Settings.SPRITE_MAX_FORCE);
        allBird.add(bird);
        Thread t = new Thread(bird);
        t.start();
        // register vehicle
       

    }

    private void addSpecificBird(int maxSpeed) {

        Layer layer = playfield;

        // random location
        double x = random.nextDouble() * layer.getWidth();
        double y = random.nextDouble() * layer.getHeight();

        // dimensions
        double width = 50;
        double height = width / 2.0;

        // create bird data
        Vector2D location = new Vector2D( x,y);
        Vector2D velocity = new Vector2D( 0,0);
        Vector2D acceleration = new Vector2D( 0,0);

        // create sprite and add to layer
        Bird bird = new Bird( layer, location, velocity, acceleration, width, height, sharedList, maxSpeed);
        allBird.add(bird);
        Thread t = new Thread(bird);
        t.start();
        // register vehicle
      
    }
    
    private void addSpecificBird1(int maxSpeed) {

        Layer layer = playfield;

        // random location
        double x = random.nextDouble() * layer.getWidth();
        double y = random.nextDouble() * layer.getHeight();

        // dimensions
        double width = 50;
        double height = width / 2.0;

        // create bird data
        Vector2D location = new Vector2D( x,y);
        Vector2D velocity = new Vector2D( 0,0);
        Vector2D acceleration = new Vector2D( 0,0);

        // create sprite and add to layer
        Bird1 bird = new Bird1( layer, location, velocity, acceleration, width, height, sharedList, maxSpeed);
        allBird.add(bird);
        Thread t = new Thread(bird);
        t.start();
        // register vehicle
       

    }
    
    private void addSpecificBird2(int maxSpeed) {

        Layer layer = playfield;

        // random location
        double x = random.nextDouble() * layer.getWidth();
        double y = random.nextDouble() * layer.getHeight();

        // dimensions
        double width = 50;
        double height = width / 2.0;

        // create bird data
        Vector2D location = new Vector2D( x,y);
        Vector2D velocity = new Vector2D( 0,0);
        Vector2D acceleration = new Vector2D( 0,0);

        // create sprite and add to layer
        Bird2 bird = new Bird2( layer, location, velocity, acceleration, width, height, sharedList, maxSpeed);
        allBird.add(bird);
        Thread t = new Thread(bird);
        t.start();
        // register vehicle
       

    }
    
    
    private void addAttractors(double x, double y) {

        Layer layer = playfield;

        /*// center attractor
        double x = layer.getWidth() / 2;
        double y = layer.getHeight() / 2;
*/
        // dimensions
        double width = 25;
        double height = 25;

        // create attractor data
        Vector2D location = new Vector2D( x,y);
        Vector2D velocity = new Vector2D( 0,0);
        Vector2D acceleration = new Vector2D( 0,0);

        // create attractor and add to layer
        Attractor attractor = new Attractor( layer, location, velocity, acceleration, width, height);

        // register sprite
        allAttractors.add(attractor);
        sharedList.addAttractor(attractor);
        allAttractors.forEach(Sprite::display);
    }

    private void addAttractorsOld(double x, double y) {

        Layer layer = playfield;

        /*// center attractor
        double x = layer.getWidth() / 2;
        double y = layer.getHeight() / 2;
*/
        // dimensions
        double width = 25;
        double height = 25;

        // create attractor data
        Vector2D location = new Vector2D( x,y);
        Vector2D velocity = new Vector2D( 0,0);
        Vector2D acceleration = new Vector2D( 0,0);

        // create attractor and add to layer
        AttractorOld attractorOld = new AttractorOld( layer, location, velocity, acceleration, width, height);

        // register sprite
        allAttractorsOld.add(attractorOld);
        //sharedList.addAttractorOld(attractor);
        allAttractorsOld.forEach(Sprite::display);
    }
    
    private void addListeners() {

        // capture mouse position
        scene.addEventFilter(MouseEvent.ANY, e -> {
            mouseLocation.set(e.getX(), e.getY());
        });

        /*// move attractors via mouse
        for( Attractor attractor: allAttractors) {
            mouseGestures.Onclick(attractor);
        }*/
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            addAttractors(e.getX(), e.getY());
            start = System.currentTimeMillis();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}