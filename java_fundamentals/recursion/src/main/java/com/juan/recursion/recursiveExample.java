package com.juan.recursion;

public class recursiveExample {
	// Entry Point Method
	public static void main(String[] args) {
		sayHello(10);
		
	}
	static void sayHello(int number) {
		// base case
		if(number == 0) {
			System.out.println("we have blastoff");
			return;
		}
		System.out.printf("Recursion is awesome %d iteration\n", number);
		sayHello(number - 1);
}
}
