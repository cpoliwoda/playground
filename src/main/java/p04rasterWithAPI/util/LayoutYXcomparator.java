/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p04rasterWithAPI.util;

import java.util.Comparator;
import javafx.scene.Node;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class LayoutYXcomparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        /*
         negativer Rückgabewert: Der erste Parameter ist untergeordnet
         0 als Rückgabewert: Beide Parameter werden gleich eingeordnet
         positiver Rückgabewert: Der erste Parameter ist übergeordnet
         */
        if (o1 == null || o2 == null) {

            if (o1 == null) {
                return -1;
            } else if (o2 == null) {
                return 1;
            }
            return 0;

        } else {

            // check first Y than X
            if (o1.getLayoutY() > o2.getLayoutY()) {
                return 1;
            } else if (o1.getLayoutY() == o2.getLayoutY()) {

                if (o1.getLayoutX() > o2.getLayoutX()) {
                    return 1;
                } else if (o1.getLayoutX() == o2.getLayoutX()) {
                    return 0;
                } else if (o1.getLayoutX() < o2.getLayoutX()) {
                    return -1;
                }
            } else if (o1.getLayoutY() < o2.getLayoutY()) {
                return -1;
            }
        }// null
        return 0;
    }
}
