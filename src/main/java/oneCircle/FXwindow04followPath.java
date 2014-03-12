/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oneCircle;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXwindow04followPath extends Application {

    ArrayList<Point2D> path = new ArrayList<>();

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
        // TODO follow PATH

//        //adding a position of the circle depending on clicking position INSIDE the WINDOW/scene
        scene.onMousePressedProperty().set(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Point2D point = new Point2D(event.getSceneX(), event.getSceneY());
                path.add(point);

                System.out.println("setting ponit into path: " + point.getX() + " , " + point.getY());
            }
        }
        );

        scene.onKeyPressedProperty().set(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                // clear the path
                if (event.getCode().equals(KeyCode.SPACE)) {
                    path.clear();
                    System.out.println("path cleared");

                }// set the circle on the position that are stored in the path variable
                else if (event.getCode().equals(KeyCode.ENTER)) {
                    System.out.println("start path");

                    // start only if there is a position stored in path
                    if (!path.isEmpty()) {
                        System.out.println("path not empty");

//                        Animation pathAnimation = new TranslateTransition();
                        
                        
                        for (int i = 0; i < path.size(); i++) {

                            TranslateTransition tt = new TranslateTransition(Duration.millis(2000), circle01);
                            //need to remove the current position of circle to move to click position
                            tt.setToX(path.get(i).getX() - circle01.getCenterX());
                            tt.setToY(path.get(i).getY() - circle01.getCenterY());
                            tt.setCycleCount(1);
                            tt.setAutoReverse(false);

                            tt.play();

//                            //set the current point of the circle
//                            circle01.setCenterX(path.get(i).getX());
//                            circle01.setCenterY(path.get(i).getY());
                            System.out.println("setting point " + i + " : " + path.get(i).getX() + " , " + path.get(i).getY());

//                            try {
//                                //wait before setting the next point
//                                TimeUnit.SECONDS.wait(1);
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(FXwindow04followPath.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                        }

                    }
                }

            }
        });

        //show window
        stage.show();
    }

}
