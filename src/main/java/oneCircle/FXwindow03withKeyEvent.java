/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oneCircle;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import oneCircle.keyhandler.ArrowKeysEventHandler;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class FXwindow03withKeyEvent extends Application {

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
//        //TRANSLATE the position of the circle depending start position and
//        //on the arrow key is pressed (moving AROUND start position)
//        scene.onKeyPressedProperty().set(new EventHandler<KeyEvent>() {
//
//            @Override
//            public void handle(KeyEvent event) {
//          
//        double stepSize =10;
//      
////        sollte aber tun sie bsp:    /Users/christianpoliwoda/Apps/tests/java/konsole/switchString
////                switch(event.getCode().getName()){
////                    case KeyCode.UP.getName():                // wieso nicht erlaubt ?? sondern const
////                        circle01.translateYProperty().set(-stepSize);
////                        break;
////                }
//        
//                if (event.getCode().equals(event.getCode().UP)) {
//                    circle01.translateYProperty().set(-stepSize);
//
//                } else if (event.getCode().equals(event.getCode().DOWN)) {
//                    circle01.translateYProperty().set(+stepSize);
//
//                } else if (event.getCode().equals(event.getCode().LEFT)) {
//                    circle01.translateXProperty().set(-stepSize);
//
//                } else if (event.getCode().equals(event.getCode().RIGHT)) {
//                    circle01.translateXProperty().set(+stepSize);
//                }
//
//            }
//        }
//        );
//        
//        //TRANSLATE the position of the circle depending start position and
//        //on the arrow key is pressed (moving free in scene)
//        scene.onKeyPressedProperty().set(new EventHandler<KeyEvent>() {
//
//            @Override
//            public void handle(KeyEvent event) {
//
//                double stepSize = 10;
//
//                if (event.getCode().equals(event.getCode().UP)) {
//                    circle01.centerYProperty().set(circle01.getCenterY() - stepSize);
//
//                } else if (event.getCode().equals(event.getCode().DOWN)) {
//                    circle01.centerYProperty().set(circle01.getCenterY() + stepSize);
//
//                } else if (event.getCode().equals(event.getCode().LEFT)) {
//                    circle01.centerXProperty().set(circle01.getCenterX() - stepSize);
//
//                } else if (event.getCode().equals(event.getCode().RIGHT)) {
//                    circle01.centerXProperty().set(circle01.getCenterX() + stepSize);
//                }
//
//            }
//        }
//        );
//        
        //TRANSLATE the position of the circle depending start position and
//        //on the arrow key is pressed (moving free in scene)
        // DONT work only last added EventHandler is reacting/responsing
//        scene.onKeyPressedProperty().set(new LeftKeyEventHandler(circle01, 10));
//        scene.onKeyPressedProperty().set(new RightKeyEventHandler(circle01, 10));
//        scene.onKeyPressedProperty().set(new UpKeyEventHandler(circle01, 10));
//        scene.onKeyPressedProperty().set(new DownKeyEventHandler(circle01, 10));
//
//         //TRANSLATE the position of the circle depending start position and
////        //on the arrow key is pressed (moving free in scene)
//        // BUT no diaganle walk is possible via HOLDING 2 keys ONLY via typping simultainly 2 keys
        scene.onKeyPressedProperty().set(new ArrowKeysEventHandler(circle01, 10));
        
        // DO NOT work
//        scene.addEventHandler(new EventType<KeyEvent>(),new ArrowKeysEventHandler(circle01, 10));
//        scene.addEventFilter(new EventType<KeyEvent>(),new ArrowKeysEventHandler(circle01, 10));
//        circle01.addEventHandler(new EventType<KeyEvent>(),new ArrowKeysEventHandler(circle01, 10));
        
        
        //show window
        stage.show();
    }
}