/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack01;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

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
        circle01.centerXProperty().set(100);
        circle01.centerYProperty().set(180);

        //add element to root element of scene
        root.getChildren().add(circle01);
//
//        //set the position of the circle depending on clicking position INSIDE the CIRCLE
//        circle01.onMousePressedProperty().set(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                circle01.centerXProperty().set(event.getSceneX());
//                circle01.centerYProperty().set(event.getSceneY());
//            }
//        }
//        );
////        //set the position of the circle depending on clicking position INSIDE the WINDOW/scene
//        scene.onMousePressedProperty().set(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                circle01.centerXProperty().set(event.getSceneX());
//                circle01.centerYProperty().set(event.getSceneY());
//            }
//        }
//        );
//
        //        //set the position of the circle depending on the position of the mouse INSIDE the WINDOW/scene
        scene.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                circle01.centerXProperty().set(event.getSceneX());
                circle01.centerYProperty().set(event.getSceneY());
            }
        }
        );
        
        //show window
        stage.show();
    }

}
