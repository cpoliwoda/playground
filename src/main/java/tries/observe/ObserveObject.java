/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tries.observe;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class ObserveObject {
    
    public static ObjectProperty<Shape> shapeProperty = new SimpleObjectProperty<Shape>();
            
    
    public static void main(String[] args) {
        
        shapeProperty.set(new Circle(2));
        
        shapeProperty.addListener(new ChangeListener<Shape>() {

            @Override
            public void changed(ObservableValue<? extends Shape> observable, Shape oldValue, Shape newValue) {
                System.out.println(" oldValue = "+oldValue+ " newValue = "+newValue);
            }
        });
        
        shapeProperty.set(new Circle(3));
    }
}
