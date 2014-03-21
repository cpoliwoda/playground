/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p04rasterWithAPI;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import p04rasterWithAPI.util.LayoutXYcomparator;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXw02withLevels extends Application {

    HashMap<String, ArrayList<Node>> levels = new HashMap<>();
    static String CIRCLES = "CIRCLES";
    static String RECTANGLES = "RECTANGLES";
    static String POLYGONS = "POLYGONS";
    static LayoutXYcomparator xyComparator = new LayoutXYcomparator();
    

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

        ArrayList<Node> circlesInSecColumn = new ArrayList<>();
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

        ArrayList<Node> circlesInThirdColumn = new ArrayList<>();
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

        ArrayList<Node> allCircles = new ArrayList<>();
        allCircles.add(circle01);
        allCircles.addAll(circlesInSecColumn);
        allCircles.addAll(circlesInThirdColumn);

        //
        //Rectangles
        //
        ArrayList<Node> allRectangles = new ArrayList<>();
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
        ArrayList<Node> allPolygons = new ArrayList<>();
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
//        //add elements to the different levels
//        levels.put(CIRCLES, allCircles);
//        levels.put(RECTANGLES, allRectangles);
//        levels.put(POLYGONS, allPolygons);

        //add elements to root element of scene
        root.getChildren().addAll(allCircles);
        root.getChildren().addAll(allRectangles);
        root.getChildren().addAll(allPolygons);

        //show window
        stage.show();

//        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler02(root, levels, CIRCLES));
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyMoveOverListEventHandler02(root, levels, RECTANGLES));

        VirtualRaster virtualRaster = new VirtualRaster(scene, root);
        
        //add elements to the different levels
        virtualRaster.addLevel(CIRCLES, allCircles);
        virtualRaster.addLevel(RECTANGLES, allRectangles);
        virtualRaster.addLevel(POLYGONS, allPolygons);
        
        virtualRaster.setActiveNodeLevel(CIRCLES);
        virtualRaster.initCursor();
    }

}
