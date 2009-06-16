package com.pss.core.testes;

import java.util.LinkedList;

public class TestaGrafo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    LinkedList stack = new LinkedList();
	    
	    // Push on top of stack
	    stack.addFirst(new Integer(1));
	    stack.addFirst(new Integer(2));
	    stack.addFirst(new Integer(3));
	    stack.addFirst(new Integer(4));
	    
	    System.out.println(stack);
	    // Pop off top of stack
	    
	    if (stack.contains(4)){
	    	System.out.println("contem 4");
	    }
	    
	    System.out.println("first: "+stack.removeFirst());
	    System.out.println(stack);
	    System.out.println("first: "+stack.removeFirst());
	    System.out.println(stack);
	    System.out.println("first: "+stack.removeFirst());
	    // If the queue is to be used by multiple threads,
	    // the queue must be wrapped with code to synchronize the methods
	    //stack = (LinkedList)Collections.synchronizedList(stack);


	}

}
