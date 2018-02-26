package application;

    import javafx.event.EventHandler;
    import javafx.scene.input.MouseEvent;


    public class MouseGestures {

        final ClickContext clickContext = new ClickContext();

        public void makeDraggable(final Sprite sprite) {

            sprite.setOnMousePressed(onMousePressedEventHandler);
           

        }

        EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                clickContext.x = event.getSceneX();
                clickContext.y = event.getSceneY();

            }
        };

       
        class ClickContext {

            double x;
            double y;

        }

    }