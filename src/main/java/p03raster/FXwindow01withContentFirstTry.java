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
public class FXwindow01withContentFirstTry extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);

        Circle circle01 = new Circle(10, Color.BEIGE);
        //set position of the centrum
        circle01.centerXProperty().set(100);
        circle01.centerYProperty().set(180);

//        //add element to root element of scene
//        root.getChildren().add(circle01);
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

        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyRasterEventHandler(allCircles));

        //add element to root element of scene
        root.getChildren().addAll(allCircles);

        //show window
        stage.show();
    }

}

class KeyRasterEventHandler implements EventHandler<KeyEvent> {

    ArrayList<Shape> allCircles = new ArrayList<>();
    ArrayList<ArrayList<Shape>> allColumns = new ArrayList<>();
    Shape cursor = null;
    TranslationXYZcomparator comparator = null;
    double range = 50;

    KeyRasterEventHandler(ArrayList<Shape> allCircles) {
        this.allCircles.addAll(allCircles);
        cursor = allCircles.get(0); // assumption cursor is most left element
//        this.allCircles.remove(0);// we do not need the first/"cursor"

        comparator = new TranslationXYZcomparator();
        this.allCircles.sort(comparator);

        //sort all circles into columns
        allColumns.add(new ArrayList<>());
        int columnIndex = 0;
        Shape firstShapeInAcolumn = this.allCircles.get(0);

        for (int i = 1; i < this.allCircles.size(); i++) {
            Shape nextShape = this.allCircles.get(i);

            //check if next shape is in x direction near enough
            //allCircles are sorted via translation/position , first X Than Y than Z Translation
            if (nextShape.getTranslateX() - range <= firstShapeInAcolumn.getTranslateX()) {
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

        System.out.println(" a key was pressed ");

        // if arrow key
        if ((event.getCode().equals(KeyCode.UP))
                || (event.getCode().equals(KeyCode.DOWN))
                || (event.getCode().equals(KeyCode.RIGHT))
                || (event.getCode().equals(KeyCode.LEFT))) {
            System.out.println(" a arrow key was pressed ");

            ArrayList<Shape> workingCopyAll = new ArrayList<>();
            workingCopyAll.addAll(allCircles);
//            workingCopyAll.add(cursor);
//            workingCopyAll.sort(comparator);

            double nextCursorX = 0;
            double nextCursorY = 0;
            int cursorIndex = workingCopyAll.indexOf(cursor);
            System.out.println(" cursorIndex = " + cursorIndex);

            if ((event.getCode().equals(KeyCode.UP))
                    || (event.getCode().equals(KeyCode.DOWN))) {

                if (event.getCode().equals(KeyCode.UP)) {
                    System.out.println(" UP key was pressed ");

//                    if (cursorIndex > 0) {
                    if (cursorIndex < workingCopyAll.size() - 1) {
                        nextCursorX = workingCopyAll.get(cursorIndex + 1).getTranslateX() - cursor.getLayoutX();
                        nextCursorY = workingCopyAll.get(cursorIndex + 1).getTranslateY() - cursor.getLayoutY();

                        System.out.println("nextCursor X,Y = " + nextCursorX + " , " + nextCursorY);

//                        cursor.setLayoutX(nextCursorX);
//                        cursor.setLayoutY(nextCursorY);
                        cursor.setTranslateX(nextCursorX);
                        cursor.setTranslateY(nextCursorY);
                    }

                } else if (event.getCode().equals(KeyCode.DOWN)) {
                    System.out.println(" DOWN key was pressed ");

//                    if (cursorIndex < workingCopyAll.size() - 1) {
                    if (cursorIndex > 0) {
                        nextCursorX = workingCopyAll.get(cursorIndex - 1).getTranslateX() - cursor.getLayoutX();
                        nextCursorY = workingCopyAll.get(cursorIndex - 1).getTranslateY() - cursor.getLayoutY();

                        System.out.println("nextCursor X,Y = " + nextCursorX + " , " + nextCursorY);

//                        cursor.setLayoutX(nextCursorX);
//                        cursor.setLayoutY(nextCursorY);
                        cursor.setTranslateX(nextCursorX);
                        cursor.setTranslateY(nextCursorY);
                    }

                }
            } else if ((event.getCode().equals(KeyCode.RIGHT))
                    || (event.getCode().equals(KeyCode.LEFT))) {

                if (event.getCode().equals(KeyCode.LEFT)) {
                    System.out.println(" LEFT key was pressed ");

                    if (cursorIndex > 0) {

                        //check in which column the cursor is and jump on the first shape on the column-1/left
                        for (ArrayList<Shape> shapeList : allColumns) {

                            if (shapeList.contains(cursor)) {
                                int cursorColumnIndex = allColumns.indexOf(shapeList);
                                
                                System.out.println("cursorColumnIndex = "+ cursorColumnIndex);
                                
                                if (cursorColumnIndex > 0) {
                                    Shape firstShapeInLeftColumn = allColumns.get(cursorColumnIndex - 1).get(0);

                                    nextCursorX = firstShapeInLeftColumn.getTranslateX() - cursor.getLayoutX();
                                    nextCursorY = firstShapeInLeftColumn.getTranslateY() - cursor.getLayoutY();

                                    System.out.println("nextCursor X,Y = " + nextCursorX + " , " + nextCursorY);

//                        cursor.setLayoutX(nextCursorX);
//                        cursor.setLayoutY(nextCursorY);
                                    cursor.setTranslateX(nextCursorX);
                                    cursor.setTranslateY(nextCursorY);

                                    //we found what we searched so save performance and quit loop
                                    break;
                                }
                            }
                        }
                    }

                } else if (event.getCode().equals(KeyCode.RIGHT)) {
                    System.out.println(" RIGHT key was pressed ");

                    //check in which column the cursor is and jump on the first shape on the column-1/left
                    for (ArrayList<Shape> shapeList : allColumns) {

                        if (shapeList.contains(cursor)) {
                            int cursorColumnIndex = allColumns.indexOf(shapeList);
                            
                            System.out.println("cursorColumnIndex = "+ cursorColumnIndex);
                            
                            if (cursorColumnIndex < allColumns.size() - 1) {
                                Shape firstShapeInRightColumn = allColumns.get(cursorColumnIndex + 1).get(0);

                                nextCursorX = firstShapeInRightColumn.getTranslateX() - cursor.getLayoutX();
                                nextCursorY = firstShapeInRightColumn.getTranslateY() - cursor.getLayoutY();

                                System.out.println("nextCursor X,Y = " + nextCursorX + " , " + nextCursorY);

//                        cursor.setLayoutX(nextCursorX);
//                        cursor.setLayoutY(nextCursorY);
                                cursor.setTranslateX(nextCursorX);
                                cursor.setTranslateY(nextCursorY);

                                //we found what we searched so save performance and quit loop
                                break;
                            }
                        }
                    }

                }
            }
        }
    }

}

class TranslationXYZcomparator implements Comparator<Shape> {

    @Override
    public int compare(Shape o1, Shape o2) {
        /*
         negativer Rückgabewert: Der erste Parameter ist untergeordnet
         0 als Rückgabewert: Beide Parameter werden gleich eingeordnet
         positiver Rückgabewert: Der erste Parameter ist übergeordnet
         */

        if (o1 == null || o2 == null) {

            if (o1 == null) {
                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            return 0;

        } else {

            // check first X than Y than Z  
            if (o1.getTranslateX() > o2.getTranslateX()) {
                return 1;
            } else if (o1.getTranslateX() == o2.getTranslateX()) {

                if (o1.getTranslateY() > o2.getTranslateY()) {
                    return 1;
                } else if (o1.getTranslateY() == o2.getTranslateY()) {

                    if (o1.getTranslateZ() > o2.getTranslateZ()) {
                        return 1;
                    } else if (o1.getTranslateZ() == o2.getTranslateZ()) {
                        return 0;
                    } else if (o1.getTranslateZ() < o2.getTranslateZ()) {
                        return -1;
                    }

                } else if (o1.getTranslateY() < o2.getTranslateY()) {
                    return -1;
                }
            } else if (o1.getTranslateX() < o2.getTranslateX()) {
                return -1;
            }
        }// null
        return 0;
    }
}
