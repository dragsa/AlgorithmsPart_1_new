/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package st;

import edu.princeton.cs.introcs.In;

/**
 *
 * @author admin
 */
public class SymbolTableTestClient {

    public static void main(String[] args) {
        In in = new In(args[0]);
        
        SymbolTableSequentialSearch<String, Integer> st = new SymbolTableSequentialSearch<String, Integer>();
        int value = 0;
        while (!in.isEmpty()) {
            String key = in.readString();
            st.put(key, value);
            value++;
        }
    }
}
