/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p01oneCircle;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import p01oneCircle.util.Util;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXwindow02withMouseEvent extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);

        Circle circle01 = new Circle(50, Color.BEIGE);
        //set position of the centrum
        circle01.setLayoutX(100);
        circle01.setLayoutY(180);

        //add element to root element of scene
        root.getChildren().add(circle01);
//
//        //set the position of the circle depending on clicking position INSIDE the CIRCLE
//        circle01.onMousePressedProperty().set(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                circle01.setLayoutX(event.getSceneX());
//                circle01.setLayoutY(event.getSceneY());
//            }
//        }
//        );
//        
////        //set the position of the circle depending on CLICKING position INSIDE the WINDOW/scene
//        scene.onMousePressedProperty().set(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                circle01.setLayoutX(event.getSceneX());
//                circle01.setLayoutY(event.getSceneY());
//            }
//        }
//        );
// 
////        //set the position of the circle depending on the MOVING position of the mouse INSIDE the WINDOW/scene
//        scene.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                circle01.setLayoutX(event.getSceneX());
//                circle01.setLayoutY(event.getSceneY());
//            }
//        }
//        );
//        //set the position of the circle depending on the click position of the mouse INSIDE the WINDOW/scene
//        //with an animation make it look smother
//        scene.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//
//                TranslateTransition tt = new TranslateTransition(Duration.millis(2000), circle01);
//                //need to remove the current position of circle to move to click position
//                tt.setToX(event.getSceneX() - circle01.getLayoutX());
//                tt.setToY(event.getSceneY() - circle01.getLayoutY());
//                tt.setCycleCount(1);
//                tt.setAutoReverse(false);
//
//                tt.play();
//            }
//        }
//        );

//        // ABOVE WORKS 
//        circle01.pickOnBoundsProperty().set(true);
//        
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new MousePickInfoEventHandler());

        //show window
        stage.show();

//        Util.printPositionBoundingStuff(circle01);
    }

}

class MousePickInfoEventHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        System.out.println(" event scene x,y = " + event.getSceneX() + " , " + event.getSceneY());

        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
//        f (event.equals(MouseEvent.MOUSE_ENTERED)) {

            Node node = event.getPickResult().getIntersectedNode();
            System.out.println(" node = " + node);

            if (node != null) {
                boolean nodeContainsPoint = node.contains(event.getSceneX(), event.getSceneY());

                if (nodeContainsPoint) {
                    System.out.println(" nodeContainsPoint = " + event.getSceneX() + " , " + event.getSceneY());
                }
            }
        }//clicked

    }

}
