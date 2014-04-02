/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tries.basics;

import java.util.ArrayList;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class Basics {

    public static void main(String[] args) {
        listWithOneElement();
    }

    public static void listWithOneElement() {
        ArrayList<String> list = new ArrayList<>();
        String eins = "eins";
        list.add(eins);

        System.out.println("list.size() = " + list.size());
        int index = list.indexOf(eins);
        System.out.println("index = " + index);
    }
}
