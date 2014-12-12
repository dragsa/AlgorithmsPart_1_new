/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package st;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class SymbolTableSmallTests {
    public static void main (String[] args) {
        Map<String, Integer> someMap = new HashMap<String, Integer>();
        someMap.put(null, null);
        System.out.println("");
        someMap.clear();
        someMap.remove("something");
    }
}
