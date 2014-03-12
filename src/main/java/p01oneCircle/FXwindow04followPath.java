/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p01oneCircle;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
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
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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
//                System.out.println("circle01 position start: " + circle01.getCenterX() + " , " + circle01.getCenterY());
//                System.out.println("circle01 position start: " + circle01.getLayoutX() + " , " + circle01.getLayoutY());
                System.out.println("circle01 position start: " + circle01.getTranslateX() + " , " + circle01.getTranslateY());

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
                        PathTransition pathTransition = new PathTransition();

                        Path animationPath = new Path();

//                        //animation from current position to all position in path
//                        //starting always from curent position and NOT from last animation end position !!!
//                        for (int i = 0; i < path.size(); i++) {
//                             animationPath.getElements().add(new MoveTo(
//                                     circle01.getCenterX(),
//                                     circle01.getCenterY()));
//                              animationPath.getElements().add(new LineTo(
//                                    path.get(i).getX(), 
//                                    path.get(i).getY() ));
//                        }
                        //animation from current position to over all position in path to last one in path
                        //  the POSITION of circle is  changed !! updated in the drawing thread NOT in this one !!!
                        for (int i = 0; i < path.size(); i++) {
                            if (i == 0) {
                                animationPath.getElements().add(new MoveTo(
                                        circle01.getCenterX(),
                                        circle01.getCenterY()));
//                                         circle01.getLayoutX(),
//                                        circle01.getLayoutY()));
                                animationPath.getElements().add(new LineTo(
                                        path.get(i).getX(),
                                        path.get(i).getY()));
                            } else {
                                animationPath.getElements().add(new MoveTo(
                                        path.get(i - 1).getX(),
                                        path.get(i - 1).getY()));
                                animationPath.getElements().add(new LineTo(
                                        path.get(i).getX(),
                                        path.get(i).getY()));
                            }

                        }

                        pathTransition.setDuration(Duration.millis(10000));
                        pathTransition.setNode(circle01);
                        pathTransition.setPath(animationPath);
                        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
                        pathTransition.setCycleCount(1);
                        pathTransition.setAutoReverse(false);

                        pathTransition.play();

                        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("pathTransition.setOnFinished(new EventHandler<ActionEvent>() {");
//                        System.out.println("circle01 position end: " + circle01.getCenterX() + " , " + circle01.getCenterY());
//                        System.out.println("circle01 position end: " + circle01.getLayoutX() + " , " + circle01.getLayoutY());
                                System.out.println("circle01 position end: " + circle01.getTranslateX() + " , " + circle01.getTranslateY());
                            }
                        });

                    }
                }

            }
        });

        //show window
        stage.show();
    }

}
