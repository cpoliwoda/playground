/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oneCircle.keyhandler;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;
import static oneCircle.keyhandler.ArrowKeysEventHandler.shapeProperty;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class RightKeyEventHandler implements EventHandler<KeyEvent> {

//    Shape shape = null;
    public static ObjectProperty<Shape> shapeProperty = new SimpleObjectProperty<Shape>();

    double stepSize = 10;

    public RightKeyEventHandler(Shape shape, double stepSize) {
        this.shapeProperty.set(shape);
        this.stepSize = stepSize;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.RIGHT)) {
//            shape.setLayoutX(shape.getLayoutX() + stepSize);
            shapeProperty.get().setLayoutX(shapeProperty.get().getLayoutX() + stepSize);
        }
    }
    
    public void setShape(Shape shape){
        shapeProperty.set(shape);
    }
}
