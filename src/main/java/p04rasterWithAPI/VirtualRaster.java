/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p04rasterWithAPI;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import p04rasterWithAPI.util.LayoutXYcomparator;
import p04rasterWithAPI.util.LayoutYXcomparator;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class VirtualRaster implements EventHandler<KeyEvent> {

    String NONE = "NONE";
    Scene scene = null;
    Group root = null;
    Circle cursor = null;

    ArrayList<ArrayList<Node>> allColumns = new ArrayList<>();
    int range = 50;

    ObjectProperty<String> activeNodeLevelProperty = null;
    ObjectProperty<HashMap<String, ArrayList<Node>>> levelsProperty = null;

    LayoutYXcomparator yxcomparator = null;
    LayoutXYcomparator xycomparator = null;

    public VirtualRaster(Scene scene, Group root) {
        this.scene = scene;
        this.root = root;
        activeNodeLevelProperty = new SimpleObjectProperty<String>();
        activeNodeLevelProperty.set(NONE);

        levelsProperty = new SimpleObjectProperty<>();
        levelsProperty.set(new HashMap<>());

        yxcomparator = new LayoutYXcomparator();
        xycomparator = new LayoutXYcomparator();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
    }

    public void setActiveNodeLevel(String level) {
        activeNodeLevelProperty.set(level);
    }

    public void addLevel(String levelName, ArrayList<Node> allNodesInThatLevel) {
        levelsProperty.get().put(levelName, allNodesInThatLevel);
    }

    public void addNodeToLevel(String levelName, Node node) {
        ArrayList<Node> allNodesInThatLevel = levelsProperty.get().get(levelName);
        if (allNodesInThatLevel != null) {
            allNodesInThatLevel.add(node);
        } else {
            System.err.println("Level not found ! : " + levelName);
        }
    }

    public void initCursor() {
        System.out.println(getClass().getSimpleName() + ".initCursor()");

        //create cursor
        cursor = new Circle(10, Color.BROWN);
        root.getChildren().add(cursor);

        positionizeCursor();

    }

    public void positionizeCursor() {
        //find first node in actived level
        HashMap<String, ArrayList<Node>> hashMap = levelsProperty.get();
        String activeLevelName = activeNodeLevelProperty.get();
        ArrayList<Node> activeLevel = hashMap.get(activeLevelName);

        if (activeLevel != null) {
            Node firstNode = activeLevel.get(0);

            //set cursor at the position of first node
            cursor.setLayoutX(firstNode.getLayoutX());
            cursor.setLayoutY(firstNode.getLayoutY());
        }
    }

    public void sortNodesIntoColumns() {
        //
        // sort the the shapes into columns
        //
        //remove all stuff that was added by last call of this methode
        allColumns.clear();
        //start with new first column and pick the first shape as basis for comparison
        allColumns.add(new ArrayList<>());
        int columnIndex = 0;
//        Node firstNodeInAcolumn = this.allNodes.get(0);
        String activeNodeLevelName = activeNodeLevelProperty.get();
        ArrayList<Node> allNodesOfCurrentLevel = levelsProperty.get().get(activeNodeLevelName);

        if (allNodesOfCurrentLevel != null) {
            //sort all nodes before choosing the "lowest"/"first"
            allNodesOfCurrentLevel.sort(xycomparator);

            Node firstNodeInAcolumn = allNodesOfCurrentLevel.get(0);
            allColumns.get(0).add(firstNodeInAcolumn);//add the first shape in the first column manually

            //check now all other shapes if they are in range 
            //if yes add to latest column
            //if not create next new column and add to it and select new shape for comparison 
//        for (int i = 1; i < this.allNodes.size(); i++) {
//            Node nextNode = this.allNodes.get(i);
            for (int i = 1; i < levelsProperty.get().get(activeNodeLevelProperty.get()).size(); i++) {
                Node nextNode = levelsProperty.get().get(activeNodeLevelProperty.get()).get(i);

                //check if next shape is in x direction near enough
                if (nextNode.getLayoutX() - range <= firstNodeInAcolumn.getLayoutX()) {
                    allColumns.get(columnIndex).add(nextNode);
                } else {
                    columnIndex++;
                    allColumns.add(new ArrayList<>());
                    allColumns.get(columnIndex).add(nextNode);
                    firstNodeInAcolumn = nextNode;
                }
            }
            System.out.println(" columnIndex = " + columnIndex);

            //sort all columns from top to botton meaning via Y is more intutiv
            for (ArrayList<Node> column : allColumns) {
                column.sort(yxcomparator);
            }
        }
    }

    @Override
    public void handle(KeyEvent event) {

        KeyCode eventKeyCode = event.getCode();
        System.out.println("eventCode.getName() = " + eventKeyCode.getName());

        if (eventKeyCode.equals(KeyCode.ESCAPE)) {
            System.exit(0);
        }

        // if a or s or d, switching between the levels
        if ((eventKeyCode.equals(KeyCode.A)) || (eventKeyCode.equals(KeyCode.S)) || (eventKeyCode.equals(KeyCode.D))) {

            if ((eventKeyCode.equals(KeyCode.A))) {
                activeNodeLevelProperty.set(FXw02withLevels.CIRCLES);
            } else if (eventKeyCode.equals(KeyCode.S))// s
            {
                activeNodeLevelProperty.set(FXw02withLevels.RECTANGLES);
            } else if (eventKeyCode.equals(KeyCode.D))//d
            {
                activeNodeLevelProperty.set(FXw02withLevels.POLYGONS);
            }

            System.out.println("activeNodeLevelProperty.get() = " + activeNodeLevelProperty.get());
            positionizeCursor();
        } // if a or s or d, switching between the levels

        sortNodesIntoColumns();

        // if arrow key
        if ((eventKeyCode.equals(KeyCode.UP))
                || (eventKeyCode.equals(KeyCode.DOWN))
                || (eventKeyCode.equals(KeyCode.RIGHT))
                || (eventKeyCode.equals(KeyCode.LEFT))) {

            Node shapeMarkedByCursor = null;

//            //find the shape under the cursor
//            for (int i = 0; i < allNodes.size(); i++) {
//                shapeMarkedByCursor = allNodes.get(i);            
            for (int i = 0; i < levelsProperty.get().get(activeNodeLevelProperty.get()).size(); i++) {
                shapeMarkedByCursor = levelsProperty.get().get(activeNodeLevelProperty.get()).get(i);

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

                ArrayList<Node> currentColumn = allColumns.get(columnIndexOfCursor);

                int indexOfNodeMarkedByCursor = currentColumn.indexOf(shapeMarkedByCursor);
                Node nextNode = null;

                if (eventKeyCode.equals(KeyCode.UP)) {
                    System.out.println(" UP key was pressed ");
                    if (indexOfNodeMarkedByCursor > 0) {
                        nextNode = currentColumn.get(indexOfNodeMarkedByCursor - 1);
                    }
                } else {//DOWN
                    System.out.println(" DOWN key was pressed ");
                    if ( //(indexOfNodeMarkedByCursor > - 1)&& 
                            (indexOfNodeMarkedByCursor < currentColumn.size() - 1)) {
                        nextNode = currentColumn.get(indexOfNodeMarkedByCursor + 1);
                    }
                }

                if (nextNode != null) {
                    cursor.setLayoutX(nextNode.getLayoutX());
                    cursor.setLayoutY(nextNode.getLayoutY());
                }

            } else { // LEFT or RIGHT

                Node firstNodeInNextColumn = null;

                if (eventKeyCode.equals(KeyCode.LEFT)) {
                    System.out.println(" LEFT  key was pressed ");

                    if (columnIndexOfCursor > 0) {
                        firstNodeInNextColumn = allColumns.get(columnIndexOfCursor - 1).get(0);
                    }

                } else // RIGHT
                {
                    System.out.println(" RIGHT key was pressed ");
                    if ((columnIndexOfCursor > - 1)//we really found a shape
                            && (columnIndexOfCursor < allColumns.size() - 1)) {

                        System.out.println("allColumns.size() = " + allColumns.size());
                        System.out.println("columnIndexOfCursor + 1 = " + (columnIndexOfCursor + 1));

                        firstNodeInNextColumn = allColumns.get(columnIndexOfCursor + 1).get(0);
                    }
                }
                if (firstNodeInNextColumn != null) {
                    cursor.setLayoutX(firstNodeInNextColumn.getLayoutX());
                    cursor.setLayoutY(firstNodeInNextColumn.getLayoutY());
                }
            }

        }//// if arrow key

    }
}
