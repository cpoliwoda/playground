/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p03raster;

import java.util.ArrayList;
import java.util.Comparator;
import p02twoCircles.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXwindow01withContent extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);

        Circle circle01 = new Circle(10, Color.BEIGE);
        //set position of the centrum
        circle01.centerXProperty().set(100);
        circle01.centerYProperty().set(180);

        ArrayList<Shape> circlesInSecColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle02 = new Circle(30, Color.BLUEVIOLET);
            //set position of the centrum
            circle02.centerXProperty().set(300);
            circle02.centerYProperty().set(50 + 100 * i);
            circlesInSecColumn.add(circle02);
        }

        ArrayList<Shape> circlesInThirdColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle03 = new Circle(20, Color.BLUE);
            //set position of the centrum
            circle03.centerXProperty().set(400);
            circle03.centerYProperty().set(50 + 100 * i);
            circlesInSecColumn.add(circle03);
        }

        ArrayList<Shape> allCircles = new ArrayList<>();
        allCircles.add(circle01);
        allCircles.addAll(circlesInSecColumn);
        allCircles.addAll(circlesInThirdColumn);

        
        //add elements to root element of scene
        root.getChildren().addAll(allCircles);

        //show window
        stage.show();
    }

}
