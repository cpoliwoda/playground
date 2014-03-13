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
public class FXwindow03cursorJumpOverList extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);

        int idCounter = 1;

        Circle circle01 = new Circle(30, Color.BEIGE);
        //set position of the shape
        circle01.setLayoutX(100);
        circle01.setLayoutY(180);

        circle01.setId(Integer.toString(idCounter));
        idCounter++;

        ArrayList<Shape> circlesInSecColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle02 = new Circle(30 + i * 3, Color.BLUEVIOLET);
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

        //show window
        stage.show();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler03(root, allCircles));

    }

}

class KeyMoveOverListEventHandler03 implements EventHandler<KeyEvent> {

    ArrayList<Shape> allShapes = new ArrayList<>();
    Circle cursor = new Circle(10, Color.BROWN);
    ArrayList<ArrayList<Shape>> allColumns = new ArrayList<>();
    int range = 50;

    public KeyMoveOverListEventHandler03(Group root, ArrayList<Shape> allCircles) {
        allShapes.addAll(allCircles);
        Shape firstShape = allCircles.get(0);
        root.getChildren().add(cursor);

        /* Attention:
         Depending on how the shape was positionized (center.. or translate..) we need to
         use a different "methode" to positionize our cursor !!! 
         */
        Point2D coordinatesOfFirst = new Point2D(
                firstShape.getLayoutX(), firstShape.getLayoutY());

//        System.out.println("firstShape.getId() = " + firstShape.getId() + " , " + firstShape.getFill());
//        System.out.println("coordinatesOfFirst = " + coordinatesOfFirst.getX() + " , " + coordinatesOfFirst.getY());
        cursor.setLayoutX(coordinatesOfFirst.getX());
        cursor.setLayoutY(coordinatesOfFirst.getY());

        //
        // sort the the shapes into columns
        //
        //start with new first column and pick the first shape as basis for comparison
        allColumns.add(new ArrayList<>());
        int columnIndex = 0;
        Shape firstShapeInAcolumn = this.allShapes.get(0);

        //check now all other shapes if they are in range 
        //if yes add to latest column
        //if not create next new column and add to it and select new shape for comparison 
        for (int i = 1; i < this.allShapes.size(); i++) {
            Shape nextShape = this.allShapes.get(i);

            //check if next shape is in x direction near enough
            if (nextShape.getLayoutX() - range <= firstShapeInAcolumn.getLayoutX()) {
                allColumns.get(columnIndex).add(nextShape);
            } else {
                columnIndex++;
                allColumns.add(new ArrayList<>());
                allColumns.get(columnIndex).add(nextShape);
                firstShapeInAcolumn = nextShape;
            }
        }
        System.out.println(" columnIndex = " + columnIndex);
    }

    @Override
    public void handle(KeyEvent event) {

        // if arrow key
        if ((event.getCode().equals(KeyCode.UP))
                || (event.getCode().equals(KeyCode.DOWN))
                || (event.getCode().equals(KeyCode.RIGHT))
                || (event.getCode().equals(KeyCode.LEFT))) {

            System.out.println(" a arrow key was pressed ");

            Shape shapeMarkedByCursor = null;

            //find the shape under the cursor
            for (int i = 0; i < allShapes.size(); i++) {
                shapeMarkedByCursor = allShapes.get(i);

                boolean containsCursorCenter = shapeMarkedByCursor.contains(
                        shapeMarkedByCursor.sceneToLocal(cursor.getLayoutX(), cursor.getLayoutY()));

                //save performance if shape is found we do not need to search the rest of the list
                if (containsCursorCenter) {
//                   shape.fillProperty().set(Color.YELLOWGREEN);
//                   System.out.println(i+" containsCursorCenter = "+containsCursorCenter);
                    break;
                }
            }

            if ((event.getCode().equals(KeyCode.UP))
                    || (event.getCode().equals(KeyCode.DOWN))) {

                int indexOfShape = allShapes.indexOf(shapeMarkedByCursor);
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

            } else { // LEFT or RIGHT

                int columnIndexOfCursor = -1;//invalid value as default

                //check in which column the cursor is
                for (int i = 0; i < allColumns.size(); i++) {
                    boolean columnContainsCursor = allColumns.get(i).contains(shapeMarkedByCursor);

                    if (columnContainsCursor) {
                        columnIndexOfCursor = i;
                        break;
                    }
                }

                Shape firstShapeInNextColumn = null;

                if (event.getCode().equals(KeyCode.LEFT)) {
                    if (columnIndexOfCursor > 0) {
                        firstShapeInNextColumn = allColumns.get(columnIndexOfCursor - 1).get(0);
                    }

                } else // RIGHT
                {
                    if ((columnIndexOfCursor > - 1)//we really found a shape
                            && (columnIndexOfCursor < allColumns.size() - 1)) {

                        firstShapeInNextColumn = allColumns.get(columnIndexOfCursor + 1).get(0);
                    }
                }

                if (firstShapeInNextColumn != null) {
                    cursor.setLayoutX(firstShapeInNextColumn.getLayoutX());
                    cursor.setLayoutY(firstShapeInNextColumn.getLayoutY());
                }
            }

        }

    }

}
