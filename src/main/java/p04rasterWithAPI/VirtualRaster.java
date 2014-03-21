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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import p04rasterWithAPI.util.LayoutYXcomparator;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class VirtualRaster implements EventHandler<KeyEvent>{

    ObjectProperty<String> activeNodeLevelProperty = null;
    ObjectProperty<HashMap<String, ArrayList<Node>>> levelsProperty = null;

    LayoutYXcomparator yxcomparator = null;
    KeyMoveOverListEventHandler02 keyHandler = null;

    public VirtualRaster(Scene scene, Group root) {
        activeNodeLevelProperty = new SimpleObjectProperty<String>();
        levelsProperty = new SimpleObjectProperty<>();
        levelsProperty.set(new HashMap<>());
        
        yxcomparator = new LayoutYXcomparator();
        
        keyHandler = new KeyMoveOverListEventHandler02(root, levelsProperty, "NONE");

        scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
    }

    @Override
    public void handle(KeyEvent event) {
        keyHandler.handle(event);
    }
    
    public void setActiveNodeLevel(String level){
        activeNodeLevelProperty.set(level);
    }
    
    public void addLevel(String levelName, ArrayList<Node> allNodesInThatLevel){
        levelsProperty.get().put(levelName, allNodesInThatLevel);
    }
    
    public void initCursor(){
        keyHandler.initCursor();
    }

}
