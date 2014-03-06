/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pack01.keyhandler;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class UpKeyEventHandler implements EventHandler<KeyEvent> {

    Shape shape = null;
    double stepSize = 10;

    public UpKeyEventHandler(Shape shape, double stepSize) {
        this.shape = shape;
        this.stepSize = stepSize;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
            shape.setLayoutY(shape.getLayoutY() - stepSize);
//            shape.centerYProperty().set(shape.getCenterY() - stepSize);

        }
    }
}