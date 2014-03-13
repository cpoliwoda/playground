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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import p03raster.util.NodeUtil;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXwindow02cursorJumpOverList extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);

        int idCounter = 1;

        Circle circle01 = new Circle(30, Color.BEIGE);
//        //set position of the centrum
//        circle01.centerXProperty().set(100);
//        circle01.centerYProperty().set(180);
        //set position of the shape
        circle01.setLayoutX(100);
        circle01.setLayoutY(180);

        circle01.setId(Integer.toString(idCounter));
        idCounter++;

        ArrayList<Shape> circlesInSecColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle02 = new Circle(30 + i * 3, Color.BLUEVIOLET);
//            //set position of the centrum
//            circle02.centerXProperty().set(300);
//            circle02.centerYProperty().set(50 + 100 * i);
            //set position of the shape
            circle02.setLayoutX(300);
            circle02.setLayoutY(50 + 100 * i);
            circlesInSecColumn.add(circle02);

            circle02.setId(Integer.toString(idCounter));
            idCounter++;
        }

        ArrayList<Shape> circlesInThirdColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle03 = new Circle(20 + i * 2, Color.BLUE);
//            //set position of the centrum
//            circle03.centerXProperty().set(400);
//            circle03.centerYProperty().set(50 + 100 * i);
            //set position of the shape
            circle03.setLayoutX(400);
            circle03.setLayoutY(50 + 100 * i);
            circlesInSecColumn.add(circle03);

            circle03.setId(Integer.toString(idCounter));
            idCounter++;
        }

        ArrayList<Shape> allCircles = new ArrayList<>();
        allCircles.add(circle01);
        allCircles.addAll(circlesInSecColumn);
        allCircles.addAll(circlesInThirdColumn);

        //add elements to root element of scene
        root.getChildren().addAll(allCircles);

//       scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler(root, allCircles));
        //show window
        stage.show();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler(root, allCircles));

    }

}

class KeyMoveOverListEventHandler implements EventHandler<KeyEvent> {

    ArrayList<Shape> allShapes = new ArrayList<>();
    Circle cursor = new Circle(10, Color.BROWN);

    public KeyMoveOverListEventHandler(Group root, ArrayList<Shape> allCircles) {
        allShapes.addAll(allCircles);
        Shape firstShape = allCircles.get(0);
        root.getChildren().add(cursor);
//        cursor.setTranslateX(firstShape.getTranslateX());
//        cursor.setTranslateY(firstShape.getTranslateY());
//        cursor.setTranslateZ(firstShape.getTranslateZ());
//        cursor.setLayoutX(firstShape.getLayoutX());
//        cursor.setLayoutY(firstShape.getLayoutY());

        /* Attention:
         Depending on how the shape was positionized (center.. or translate..) we need to
         use a different "methode" to positionize our cursor !!! 
         */
//        Point2D coordinatesOfFirst = firstShape.localToParent(firstShape.getLayoutX(), firstShape.getLayoutY());
        Point2D coordinatesOfFirst = new Point2D(
                firstShape.getLayoutX(), firstShape.getLayoutY());
//                                firstShape.getTranslateX(), firstShape.getTranslateY());
//                (firstShape.getLayoutBounds().getMinX()+ firstShape.getLayoutBounds().getMaxX())/2.0,
//        (firstShape.getLayoutBounds().getMinY()+ firstShape.getLayoutBounds().getMaxY())/2.0);

        System.out.println("firstShape.getId() = " + firstShape.getId() + " , " + firstShape.getFill());
        System.out.println("coordinatesOfFirst = " + coordinatesOfFirst.getX() + " , " + coordinatesOfFirst.getY());
//        
//        cursor.setCenterX(coordinatesOfFirst.getX());
//        cursor.setCenterY(coordinatesOfFirst.getY());

        cursor.setLayoutX(coordinatesOfFirst.getX());
        cursor.setLayoutY(coordinatesOfFirst.getY());
    }

    @Override
    public void handle(KeyEvent event) {

        // if arrow key
        if ((event.getCode().equals(KeyCode.UP))
                || (event.getCode().equals(KeyCode.DOWN))
                || (event.getCode().equals(KeyCode.RIGHT))
                || (event.getCode().equals(KeyCode.LEFT))) {

            System.out.println(" a arrow key was pressed ");

            Shape shape = null;

            //find the shape under the cursor
            for (int i = 0; i < allShapes.size(); i++) {
                shape = allShapes.get(i);

                boolean containsCursorCenter = shape.contains(shape.sceneToLocal(cursor.getLayoutX(), cursor.getLayoutY()));

                if (containsCursorCenter) {
//                   shape.fillProperty().set(Color.YELLOWGREEN);
//                   System.out.println(i+" containsCursorCenter = "+containsCursorCenter);
                    break;
                }
            }

            if ((event.getCode().equals(KeyCode.UP))
                    || (event.getCode().equals(KeyCode.DOWN))) {

                int indexOfShape = allShapes.indexOf(shape);
                Shape nextShape = null;

                if (event.getCode().equals(KeyCode.UP)) {
                    if (indexOfShape > 0) {
                        nextShape = allShapes.get(indexOfShape - 1);
                    }
                } else {//DOWN
                    if (indexOfShape < allShapes.size() - 1) {
                        nextShape = allShapes.get(indexOfShape + 1);
                    }
                }

                if (nextShape != null) {
                    cursor.setLayoutX(nextShape.getLayoutX());
                    cursor.setLayoutY(nextShape.getLayoutY());
                }

            }

        }

    }

}
