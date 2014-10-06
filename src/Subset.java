/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dragsa
 */
public class Subset {

    public static void main(String[] args) {
        int maxIndex = Integer.parseInt(args[0]);
        RandomizedQueue<String> rqInstance = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rqInstance.enqueue(StdIn.readString());
        }
        for (int i = 0; i < maxIndex; i++) {
            StdOut.println(rqInstance.dequeue());
        }
    }
}
