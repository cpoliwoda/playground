/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p02twoCircles;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import oneCircle.keyhandler.ArrowKeysEventHandler;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXwindow02withEvents extends Application {

//Shape activatedShape = null;
    public static ObjectProperty<Shape> activeShapeProperty = new SimpleObjectProperty<Shape>();

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

        Circle circle02 = new Circle(30, Color.BLUEVIOLET);
        //set position of the centrum
        circle02.centerXProperty().set(200);
        circle02.centerYProperty().set(280);

        //add element to root element of scene
        root.getChildren().add(circle02);

        ArrowKeysEventHandler arrowKeyHandler = new ArrowKeysEventHandler(circle01, 10);

        circle01.onMousePressedProperty().set(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println(" activatedShape = circle01 ");
//                activeShapeProperty.set( circle01);
                arrowKeyHandler.setShape(circle01);
            }
        });

        circle02.onMousePressedProperty().set(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println(" activatedShape = circle02 ");
//                activeShapeProperty.set( circle02);
                arrowKeyHandler.setShape(circle02);
            }
        });

        // NEED observation on activatedShape elsewhere it is alwasy null -> ERROR
        scene.onKeyPressedProperty().set(arrowKeyHandler);

        //show window
        stage.show();
    }

}
