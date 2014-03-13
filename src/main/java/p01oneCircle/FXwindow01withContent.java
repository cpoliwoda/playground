/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p01oneCircle;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import p01oneCircle.util.Util;

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

        Circle circle01 = new Circle(50, Color.BEIGE);

        /* Attention:        Shape are positionized 
         - first via Layout..
         - second via Translation..
         - or via special method like in case of circle center...
         NOTICE: use Layout.. to positionize your shapes !!!!
         */
//        if no position is set via layout.. (center..) OR translate.. the shape is 
//        positionized into 0,0 the left upper corner
//        if center.. AND translate.. is set the final position is the sum of this values
        //set position of the shape via layout
        circle01.setLayoutX(100);
        circle01.setLayoutY(180);

//        //set translation of the circle
//        circle01.setTranslateX(100);
//        circle01.setTranslateY(180);
//        //set position of the centrum
//        circle01.centerXProperty().set(100);
//        circle01.centerYProperty().set(180);
//
//        Circle circle02 = new Circle(30, Color.ROYALBLUE);
//        circle02.centerXProperty().set(200);
//        circle02.centerYProperty().set(360);
        //add element to root element of scene
        root.getChildren().add(circle01);
//        root.getChildren().add(circle02);

        //show window
        stage.show();
        
        Util.printPositionBoundingStuff(circle01);

    }

//    public void printPositionBoundingStuff(Circle circle01) {
//        System.out.println("center x,y = " + circle01.getCenterX() + " , " + circle01.getCenterY());
//        System.out.println("translate x,y,z = " + circle01.getTranslateX() + " , " + circle01.getTranslateY() + " , " + circle01.getTranslateZ());
//        System.out.println("Layout x,y = " + circle01.getLayoutX() + " , " + circle01.getLayoutY());
//        System.out.println("LocalToParentTransform x,y,z = " + circle01.getLocalToParentTransform().getTx()
//                + " , " + circle01.getLocalToParentTransform().getTy() + " , " + circle01.getLocalToParentTransform().getTz());
//        System.out.println("LocalToSceneTransform x,y,z = " + circle01.getLocalToSceneTransform().getTx()
//                + " , " + circle01.getLocalToSceneTransform().getTy() + " , " + circle01.getLocalToSceneTransform().getTz());
//        System.out.println("boundsInParentProperty().get() = " + circle01.boundsInParentProperty().get().toString());
//        System.out.println("circle01.getLayoutBounds().getMaxX ..Y ..Z= " + circle01.getLayoutBounds().getMaxX()
//                + " , " + circle01.getLayoutBounds().getMaxY() + " , " + circle01.getLayoutBounds().getMaxZ());
//        System.out.println("circle01.getLayoutBounds().getMinX ..Y ..Z= " + circle01.getLayoutBounds().getMinX()
//                + " , " + circle01.getLayoutBounds().getMinY() + " , " + circle01.getLayoutBounds().getMinZ());
//    }

}
