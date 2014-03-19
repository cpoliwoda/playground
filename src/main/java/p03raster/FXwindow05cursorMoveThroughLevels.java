/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p03raster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import p03raster.util.LayoutXYcomparator;
import p03raster.util.LayoutYXcomparator;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXwindow05cursorMoveThroughLevels extends Application {

    HashMap<String, ArrayList<Shape>> levels = new HashMap<>();
    static String CIRCLES = "CIRCLES";
    static String RECTANGLES = "RECTANGLES";
    static String POLYGONS = "POLYGONS";
    static LayoutXYcomparator xyComparator = new LayoutXYcomparator();
    static LayoutYXcomparator yxcomparator = new LayoutYXcomparator();

    @Override
    public void start(Stage stage) throws Exception {
        int sceneWidth = 800;
        int sceneHeight = 400;
        Group root = new Group();
        Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.GREY);
        stage.setScene(scene);

        int idCounter = 1;

        //        
        //   Circles
        //
        Circle circle01 = new Circle(15, Color.BEIGE);
        //set position of the shape
        circle01.setLayoutX(100);
        circle01.setLayoutY(180);
        circle01.setOpacity(0.5);

        circle01.setId(Integer.toString(idCounter));
        idCounter++;

        Random random = new Random();

        ArrayList<Shape> circlesInSecColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle02 = new Circle(20 + i * 2, Color.BLUEVIOLET);
            //set position of the shape
//            circle02.setLayoutX(random.nextDouble() * sceneWidth);
            circle02.setLayoutX(200);
            circle02.setLayoutY(50 + 100 * i);
            circle02.setOpacity(0.5);
            circlesInSecColumn.add(circle02);

            circle02.setId(Integer.toString(idCounter));
            idCounter++;
        }

        ArrayList<Shape> circlesInThirdColumn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Circle circle03 = new Circle(30 + i * 3, Color.BLUE);
            //set position of the shape
//            circle03.setLayoutX(random.nextDouble() * sceneWidth);
            circle03.setLayoutX(300);
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

        //
        //Rectangles
        //
        ArrayList<Shape> allRectangles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Rectangle rect = new Rectangle();
            //set position of the shape
//            rect.setLayoutX(random.nextDouble() * sceneWidth);
            rect.setLayoutX(400);
            rect.setLayoutY(30 + 100 * i);
            //set proprties
            rect.setHeight(20 + 5 * i);
            rect.setWidth(20);
            rect.fillProperty().set(Color.ORANGE);
            rect.setOpacity(0.6);

            rect.setId(Integer.toString(idCounter));
            idCounter++;

            allRectangles.add(rect);
        }
        
         for (int i = 0; i < 4; i++) {
            Rectangle rect = new Rectangle();
            //set position of the shape
//            rect.setLayoutX(random.nextDouble() * sceneWidth);
            rect.setLayoutX(470);
            rect.setLayoutY(30 + 100 * i);
            //set proprties
            rect.setHeight(20 + 5 * i);
            rect.setWidth(20);
            rect.fillProperty().set(Color.RED);
            rect.setOpacity(0.6);

            rect.setId(Integer.toString(idCounter));
            idCounter++;

            allRectangles.add(rect);
        }

        //
        //Polygons
        //
        ArrayList<Shape> allPolygons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Polygon polygon = new Polygon(10, 0, 0, 10, 20, 10);//p1(x,y), p2(x,y), p3(x,y)

            //set position of the shape
//            polygon.setLayoutX(random.nextDouble() * sceneWidth);
            polygon.setLayoutX(500);
            polygon.setLayoutY(10 + 100 * i);
            //set proprties

            polygon.fillProperty().set(Color.GREEN);
            polygon.setOpacity(0.4);

            polygon.setId(Integer.toString(idCounter));
            idCounter++;

            allPolygons.add(polygon);
        }
        
        for (int i = 0; i < 4; i++) {
            Polygon polygon = new Polygon(10, 0, 0, 10, 20, 10);//p1(x,y), p2(x,y), p3(x,y)

            //set position of the shape
//            polygon.setLayoutX(random.nextDouble() * sceneWidth);
            polygon.setLayoutX(570);
            polygon.setLayoutY(10 + 100 * i);
            //set proprties

            polygon.fillProperty().set(Color.GREENYELLOW);
            polygon.setOpacity(0.4);

            polygon.setId(Integer.toString(idCounter));
            idCounter++;

            allPolygons.add(polygon);
        }

        //sort the circles / rectangles / polygons
        //if not sorted the virtual rastering algo is not working correct
        allCircles.sort(xyComparator);
        allRectangles.sort(xyComparator);
        allPolygons.sort(xyComparator);

        // ERROR 
        // still problem with the LAST LEVEL !!! ??? !!!
        // changing the levels bevviour strang
//        
        //add elements to the different levels
        levels.put(CIRCLES, allCircles);
        levels.put(RECTANGLES, allRectangles);
        levels.put(POLYGONS, allPolygons);

        //add elements to root element of scene
        root.getChildren().addAll(allCircles);
        root.getChildren().addAll(allRectangles);
        root.getChildren().addAll(allPolygons);

        //show window
        stage.show();

//        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler05(root, allCircles));
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler05(root, levels, CIRCLES));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler05(root, levels, RECTANGLES));

    }

}

class KeyMoveOverListEventHandler05 implements EventHandler<KeyEvent> {

    public static ObjectProperty<String> activeShapeLevelProperty = new SimpleObjectProperty<String>();
    public static ObjectProperty<HashMap<String, ArrayList<Shape>>> levelsProperty = new SimpleObjectProperty<>();

//    ArrayList<Shape> allShapes = new ArrayList<>();
    Circle cursor = new Circle(10, Color.BROWN);
    ArrayList<ArrayList<Shape>> allColumns = new ArrayList<>();
    int range = 50;

    public KeyMoveOverListEventHandler05(Group root, HashMap<String, ArrayList<Shape>> levels, String activLevel) {
        levelsProperty.set(levels);
        activeShapeLevelProperty.set(activLevel);
//        allShapes.addAll(shapes);

//        Shape firstShape = allShapes.get(0);
        Shape firstShape = levelsProperty.get().get(activeShapeLevelProperty.get()).get(0);
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
//        Shape firstShapeInAcolumn = this.allShapes.get(0);
        Shape firstShapeInAcolumn = levelsProperty.get().get(activeShapeLevelProperty.get()).get(0);
        allColumns.get(0).add(firstShapeInAcolumn);//add the first shape in the first column manually

        //check now all other shapes if they are in range 
        //if yes add to latest column
        //if not create next new column and add to it and select new shape for comparison 
//        for (int i = 1; i < this.allShapes.size(); i++) {
//            Shape nextShape = this.allShapes.get(i);
        for (int i = 1; i < levelsProperty.get().get(activeShapeLevelProperty.get()).size(); i++) {
            Shape nextShape = levelsProperty.get().get(activeShapeLevelProperty.get()).get(i);

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

        //sort all columns from top to botton meaning via Y is more intutiv
        for (ArrayList<Shape> column : allColumns) {
            column.sort(FXwindow05cursorMoveThroughLevels.yxcomparator);
        }
    }

    @Override
    public void handle(KeyEvent event) {

        KeyCode eventKeyCode = event.getCode();
        
        if(eventKeyCode.equals(KeyCode.ESCAPE)){
            System.exit(0);
        }

        // if a or s or d, switching between the levels
        if ((eventKeyCode.equals(KeyCode.A)) || (eventKeyCode.equals(KeyCode.S)) || (eventKeyCode.equals(KeyCode.D))) {

            if ((eventKeyCode.equals(KeyCode.A))) {
                activeShapeLevelProperty.set(FXwindow05cursorMoveThroughLevels.CIRCLES);
            } else if (eventKeyCode.equals(KeyCode.S))// s
            {
                activeShapeLevelProperty.set(FXwindow05cursorMoveThroughLevels.RECTANGLES);
            } else if (eventKeyCode.equals(KeyCode.D))//d
            {
                activeShapeLevelProperty.set(FXwindow05cursorMoveThroughLevels.POLYGONS);
            }

            sortShapesIntoColumns();
        }

        sortShapesIntoColumns();

        // if arrow key
        if ((eventKeyCode.equals(KeyCode.UP))
                || (eventKeyCode.equals(KeyCode.DOWN))
                || (eventKeyCode.equals(KeyCode.RIGHT))
                || (eventKeyCode.equals(KeyCode.LEFT))) {

            Shape shapeMarkedByCursor = null;

//            //find the shape under the cursor
//            for (int i = 0; i < allShapes.size(); i++) {
//                shapeMarkedByCursor = allShapes.get(i);            
            for (int i = 0; i < levelsProperty.get().get(activeShapeLevelProperty.get()).size(); i++) {
                shapeMarkedByCursor = levelsProperty.get().get(activeShapeLevelProperty.get()).get(i);

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
            if ((eventKeyCode.equals(KeyCode.UP))
                    || (eventKeyCode.equals(KeyCode.DOWN))) {

                ArrayList<Shape> currentColumn = allColumns.get(columnIndexOfCursor);

                int indexOfShapeMarkedByCursor = currentColumn.indexOf(shapeMarkedByCursor);
                Shape nextShape = null;

                if (eventKeyCode.equals(KeyCode.UP)) {
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

                if (eventKeyCode.equals(KeyCode.LEFT)) {
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
