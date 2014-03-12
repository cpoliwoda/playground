/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oneCircle.keyhandler;

import com.sun.javafx.binding.BidirectionalBinding;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Binding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class ArrowKeysEventHandler implements EventHandler<KeyEvent> {

        
//    Shape shape = null;
    public static ObjectProperty<Shape> shapeProperty = new SimpleObjectProperty<Shape>();
     
    
    double stepSize = 10;
    UpKeyEventHandler up = null;
    DownKeyEventHandler down = null;
    LeftKeyEventHandler left = null;
    RightKeyEventHandler right = null;

    public ArrowKeysEventHandler(Shape shape, double stepSize) {
//        this.shape = shape;
        shapeProperty.set(shape);
        this.stepSize = stepSize;
        up = new UpKeyEventHandler(shape, stepSize);
        down = new DownKeyEventHandler(shape, stepSize);
        left = new LeftKeyEventHandler(shape, stepSize);
        right = new RightKeyEventHandler(shape, stepSize);
    }

    @Override
    public void handle(KeyEvent event) {
        up.handle(event);
        down.handle(event);
        left.handle(event);
        right.handle(event);
    }
    
    public void setShape(Shape shape){
        shapeProperty.set(shape);
        up.setShape(shape);
        down.setShape(shape);
        left.setShape(shape);
        right.setShape(shape);
    }
}
