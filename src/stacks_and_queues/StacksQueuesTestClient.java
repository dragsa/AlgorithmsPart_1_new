/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stacks_and_queues;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * 
 * @author dragsa
 */
public class StacksQueuesTestClient {

	public static void main(String[] args) {
		In in = new In(args[0]);

		// StackOfStringsResizingArray stackOfStringsRA = new
		// StackOfStringsResizingArray();
		// while (!in.isEmpty()) {
		// String s = in.readString();
		// if (s.equals("-")) {
		// StdOut.println("current size: " + stackOfStringsRA.arrayLenght());
		// StdOut.println("items in stack: " + stackOfStringsRA.size());
		// StdOut.println("poping " + stackOfStringsRA.pop());
		// } else {
		// StdOut.println("current size: " + stackOfStringsRA.arrayLenght());
		// StdOut.println("items in stack: " + stackOfStringsRA.size());
		// StdOut.println("pushing " + s);
		// stackOfStringsRA.push(s);
		// }
		// StdOut.println("");
		// }
		// StdOut.println("");

		// StackResizingArray<Integer> stackInstanceRA = new
		// StackResizingArray<Integer>();
		// while (!in.isEmpty()) {
		// // String s = in.readString();
		// Integer s = in.readInt();
		// if (s.equals(0)) {
		// StdOut.println("current size: " + stackInstanceRA.arrayLenght());
		// StdOut.println("items in stack: " + stackInstanceRA.size());
		// StdOut.println("poping " + stackInstanceRA.pop());
		// } else {
		// StdOut.println("current size: " + stackInstanceRA.arrayLenght());
		// StdOut.println("items in stack: " + stackInstanceRA.size());
		// StdOut.println("pushing " + s);
		// stackInstanceRA.push(s);
		// }
		// StdOut.println("");
		// }
		// for (Integer currentElement : stackInstanceRA) {
		// StdOut.print(currentElement + " ");
		// }
		// StdOut.println("");

		// StackOfStringsLinkedList stackOfStringsLL = new
		// StackOfStringsLinkedList();
		// while (!in.isEmpty()) {
		// String s = in.readString();
		// if (s.equals("-")) {
		// StdOut.println("items in stack: " + stackOfStringsLL.size());
		// StdOut.println("poping " + stackOfStringsLL.pop());
		// } else {
		// StdOut.println("items in stack: " + stackOfStringsLL.size());
		// StdOut.println("pushing " + s);
		// stackOfStringsLL.push(s);
		// }
		// StdOut.println("");
		// }
		// StdOut.println("");

		StackLinkedList<Integer> stackInstanceLL = new StackLinkedList<Integer>();
		while (!in.isEmpty()) {
			Integer s = in.readInt();
			if (s.equals(0)) {
				StdOut.println("items in stack: " + stackInstanceLL.size());
				StdOut.println("poping " + stackInstanceLL.pop());
			} else {
				StdOut.println("items in stack: " + stackInstanceLL.size());
				StdOut.println("pushing " + s);
				stackInstanceLL.push(s);
			}
			StdOut.println("");
		}
		for (Integer currentElement : stackInstanceLL) {
			StdOut.println(currentElement);
			for (Integer innerElement : stackInstanceLL) {
				StdOut.print(innerElement + " ");
			}
			StdOut.println("");
		}
		StdOut.println("");
	}
	// QueueOfStringsLinkedList queueOfStringsLL = new
	// QueueOfStringsLinkedList();
	// while (!in.isEmpty()) {
	// String s = in.readString();
	// if (s.equals("-")) {
	// StdOut.println("current size: " + queueOfStringsLL.size());
	// StdOut.println("dequeue " + queueOfStringsLL.dequeue());
	// StdOut.println("current size: " + queueOfStringsLL.size());
	// } else {
	// StdOut.println("current size: " + queueOfStringsLL.size());
	// StdOut.println("enqueue " + s);
	// queueOfStringsLL.enqueue(s);
	// StdOut.println("current size: " + queueOfStringsLL.size());
	// }
	// StdOut.println("");
	// }
	// StdOut.println("");
	// QueueLinkedList<String> queueLL = new QueueLinkedList<String>();
	// // QueueLinkedList<Integer> queueLL = new QueueLinkedList()<Integer>();
	// while (!in.isEmpty()) {
	// String s = in.readString();
	// if (s.equals("-")) {
	// // Integer s = in.readInt();
	// // if (s.equals(0)) {
	// queueLL.dequeue();
	// for (String currentElement : queueLL) {
	// StdOut.print(currentElement + " ");
	// }
	// StdOut.println("");
	// } else {
	// queueLL.enqueue(s);
	// for (String currentElement : queueLL) {
	// StdOut.print(currentElement + " ");
	// }
	// StdOut.println("");
	// }
	// StdOut.println("");
	// }
	// for (String currentElement : queueLL) {
	// StdOut.println(currentElement + " ");
	// for (String innerElement : queueLL) {
	// StdOut.print(innerElement + " ");
	// }
	// StdOut.println("");
	// }
	// }
	// QueueOfStringsResizingArray queueOfStringsRA = new
	// QueueOfStringsResizingArray();
	// while (!in.isEmpty()) {
	// String s = in.readString();
	// if (s.equals("-")) {
	// queueOfStringsRA.dequeue();
	// } else {
	// queueOfStringsRA.enqueue(s);
	// }
	// StdOut.println("");
	// }
	// StdOut.println("");
	// // QueueResizingArray<String> queueRA = new QueueResizingArray<String>();
	// QueueResizingArray<Integer> queueRA = new QueueResizingArray<Integer>();
	// while (!in.isEmpty()) {
	// // String s = in.readString();
	// // if (s.equals("-")) {
	// Integer s = in.readInt();
	// if (s.equals(0)) {
	// queueRA.dequeue();
	// for (Integer currentElement : queueRA) {
	// StdOut.print(currentElement + " ");
	// }
	// StdOut.println("");
	// } else {
	// queueRA.enqueue(s);
	// for (Integer currentElement : queueRA) {
	// StdOut.print(currentElement + " ");
	// }
	// StdOut.println("");
	// }
	// StdOut.println("");
	// }
	// for (Integer currentElement : queueRA) {
	// StdOut.println(currentElement + " ");
	// for (Integer innerElement : queueRA) {
	// StdOut.print(innerElement + " ");
	// }
	// StdOut.println("");
	// }
	// }
}
