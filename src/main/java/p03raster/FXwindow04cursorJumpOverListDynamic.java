/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p03raster;

import java.util.ArrayList;
import java.util.Random;
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
import p03raster.util.LayoutXYcomparator;
import p03raster.util.LayoutYXcomparator;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXwindow04cursorJumpOverListDynamic extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        int sceneWidth = 600;
        int sceneHeight = 400;
        Group root = new Group();
        Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.BLACK);
        stage.setScene(scene);

        int idCounter = 1;

        Circle circle01 = new Circle(30, Color.BEIGE);
        //set position of the shape
        circle01.setLayoutX(100);
        circle01.setLayoutY(180);
        circle01.setOpacity(0.5);

        circle01.setId(Integer.toString(idCounter));
        idCounter++;

        Random random = new Random();

        ArrayList<Shape> circlesInSecColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle02 = new Circle(30 + i * 3, Color.BLUEVIOLET);
            //set position of the shape
            circle02.setLayoutX(random.nextDouble() * sceneWidth);
            circle02.setLayoutY(50 + 100 * i);
            circle02.setOpacity(0.5);
            circlesInSecColumn.add(circle02);

            circle02.setId(Integer.toString(idCounter));
            idCounter++;
        }

        ArrayList<Shape> circlesInThirdColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle03 = new Circle(20 + i * 2, Color.BLUE);
            //set position of the shape
            circle03.setLayoutX(random.nextDouble() * sceneWidth);
            circle03.setLayoutY(50 + 100 * i);
            circle03.setOpacity(0.5);
            circlesInSecColumn.add(circle03);

            circle03.setId(Integer.toString(idCounter));
            idCounter++;
        }

        ArrayList<Shape> allCircles = new ArrayList<>();
        allCircles.add(circle01);
        allCircles.addAll(circlesInSecColumn);
        allCircles.addAll(circlesInThirdColumn);
        //sort the circles 
        allCircles.sort(new LayoutXYcomparator());

        //add elements to root element of scene
        root.getChildren().addAll(allCircles);

        //show window
        stage.show();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler04(root, allCircles));

    }

}

class KeyMoveOverListEventHandler04 implements EventHandler<KeyEvent> {

    ArrayList<Shape> allShapes = new ArrayList<>();
    Circle cursor = new Circle(10, Color.BROWN);
    ArrayList<ArrayList<Shape>> allColumns = new ArrayList<>();
    int range = 50;

    public KeyMoveOverListEventHandler04(Group root, ArrayList<Shape> allCircles) {
        allShapes.addAll(allCircles);
//        allShapes.sort(new LayoutXYcomparator());

        Shape firstShape = allShapes.get(0);
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

//        sortShapesIntoColumns();
    }

    public void sortShapesIntoColumns() {
        //
        // sort the the shapes into columns
        //
        //remove all stuff that was added by last call of this methode
        allColumns.clear();
        //start with new first column and pick the first shape as basis for comparison
        allColumns.add(new ArrayList<>());
        int columnIndex = 0;
        Shape firstShapeInAcolumn = this.allShapes.get(0);
        allColumns.get(0).add(firstShapeInAcolumn);//add the first shape in the first column manually

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
        
        //sort all columns from top to botton meaning via Y is more intuitiv
        LayoutYXcomparator comparator = new LayoutYXcomparator();
        for (ArrayList<Shape> column : allColumns) {
            column.sort(comparator);
        }
    }

    @Override
    public void handle(KeyEvent event) {

        sortShapesIntoColumns();

        // if arrow key
        if ((event.getCode().equals(KeyCode.UP))
                || (event.getCode().equals(KeyCode.DOWN))
                || (event.getCode().equals(KeyCode.RIGHT))
                || (event.getCode().equals(KeyCode.LEFT))) {

            Shape shapeMarkedByCursor = null;

            //find the shape under the cursor
            for (int i = 0; i < allShapes.size(); i++) {
                shapeMarkedByCursor = allShapes.get(i);

                boolean containsCursorCenter = shapeMarkedByCursor.contains(
                        shapeMarkedByCursor.sceneToLocal(cursor.getLayoutX(), cursor.getLayoutY()));

                //save performance if shape is found we do not need to search the rest of the list
                if (containsCursorCenter) {
                    break;
                }
            }

            int columnIndexOfCursor = -1;//invalid value as default

            //check in which column the cursor is
            for (int i = 0; i < allColumns.size(); i++) {
                boolean columnContainsCursor = allColumns.get(i).contains(shapeMarkedByCursor);

                if (columnContainsCursor) {
                    columnIndexOfCursor = i;
                    System.out.println("columnIndexOfCursor = " + columnIndexOfCursor);
                    break;
                }
            }

            //now we check which action we should do
            //in which direction the cursor should move
            if ((event.getCode().equals(KeyCode.UP))
                    || (event.getCode().equals(KeyCode.DOWN))) {

                ArrayList<Shape> currentColumn = allColumns.get(columnIndexOfCursor);

                int indexOfShapeMarkedByCursor = currentColumn.indexOf(shapeMarkedByCursor);
                Shape nextShape = null;

                if (event.getCode().equals(KeyCode.UP)) {
                    System.out.println(" UP key was pressed ");
                    if (indexOfShapeMarkedByCursor > 0) {
                        nextShape = currentColumn.get(indexOfShapeMarkedByCursor - 1);
                    }
                } else {//DOWN
                    System.out.println(" DOWN key was pressed ");
                    if ( //(indexOfShapeMarkedByCursor > - 1)&& 
                            (indexOfShapeMarkedByCursor < currentColumn.size() - 1)) {
                        nextShape = currentColumn.get(indexOfShapeMarkedByCursor + 1);
                    }
                }

                if (nextShape != null) {
                    cursor.setLayoutX(nextShape.getLayoutX());
                    cursor.setLayoutY(nextShape.getLayoutY());
                }

            } else { // LEFT or RIGHT

                Shape firstShapeInNextColumn = null;

                if (event.getCode().equals(KeyCode.LEFT)) {
                    System.out.println(" LEFT  key was pressed ");

                    if (columnIndexOfCursor > 0) {
                        firstShapeInNextColumn = allColumns.get(columnIndexOfCursor - 1).get(0);
                    }

                } else // RIGHT
                {
                    System.out.println(" RIGHT key was pressed ");
                    if ((columnIndexOfCursor > - 1)//we really found a shape
                            && (columnIndexOfCursor < allColumns.size() - 1)) {

                        System.out.println("allColumns.size() = " + allColumns.size());
                        System.out.println("columnIndexOfCursor + 1 = " + (columnIndexOfCursor + 1));

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
