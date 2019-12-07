package com.onelogin.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import com.onelogin.arithmetic.BasicOperations;
import com.onelogin.number.MixedNumber;

/**
* Main class is used to invoke the fractional calculations. It has a main method which -
* takes in the expression as string, parses it and then computes the operations.
*  
*/

public class Main {

	public static String compute(String expression) {
		if(expression == null || expression.equals("") || expression.length() < 5) throw new IllegalArgumentException("Not a valid mathematical expression");
		
		String[] elements = expression.split(" ");
		if(!isWellFormed(elements)) {
			throw new IllegalArgumentException("Not a valid mathematical expression");
		}
		
		String finalResult = evaluate(elements);
		
		return finalResult;
	}
	
	private static boolean isWellFormed(String[] elements) {
		boolean isWellFormed = true;
		if(elements == null || elements.length < 3) return false;
		
		BasicOperations basicOps = BasicOperations.getInstance();
		
		for(int i = 0 ; i < elements.length ; i++) {
			if((i % 2 == 0) && basicOps.isOperationSupported(elements[i])) {//If even positions - 0, 2, 4, etc. contain operand, then not a valid expression
				isWellFormed = false;
				break;
			}
			if((i % 2 == 1) && !basicOps.isOperationSupported(elements[i])) { //If odd positions - 1, 3, 5, etc. does not contain operand, then not a valid expression
				isWellFormed = false;
				break;
			}
		}
		return isWellFormed;
	}
	
	public static String evaluate(String[] elements) {
		String result = "";
		
		if(elements.length < 3) return result;

		Stack<String> stack = new Stack<String>();
		stack.push(elements[0]);
		for(int i = 1; i < elements.length ;i++) {
			if(elements[i].equals("*") || elements[i].equals("/")) {
				stack.push(elements[i]);
				stack.push(elements[i+1]);
				i++;
				String currentNum = stack.pop();
				String operand = stack.pop();
				String previousNum = stack.pop();
				
				List<String> tempList = new ArrayList<String>();
				tempList.add(previousNum);
				tempList.add(currentNum);
				result = calculateInterim(tempList, operand);
				
				stack.push(""+result);
			}
			else {
				stack.push(elements[i]);
			}
		}
		
		while(!stack.isEmpty() && stack.size() >= 3) {
			String currentNum = stack.pop();
			String operand = stack.pop();
			String previousNum = stack.pop();
			
			List<String> tempList = new ArrayList<String>();
			tempList.add(previousNum);
			tempList.add(currentNum);
			result = calculateInterim(tempList, operand);
			
			stack.push(""+result);
		}
		
		return result;
	}	
	
	private static String calculateInterim(List<String> list, String operand ) {
		
		BasicOperations basicOp = BasicOperations.getInstance();
		MixedNumber result = null;
		
		MixedNumber num1 = new MixedNumber(list.get(0));
		MixedNumber num2 = new MixedNumber(list.get(1));
		if(operand.equals("+")) {
			result = basicOp.add(num1, num2);
		}
		else if(operand.equals("-")) {
			result = basicOp.subtract(num1, num2);
		}
		else if(operand.equals("*")) {
			result = basicOp.multiply(num1, num2);
		}
		else if(operand.equals("/")) {
			result = basicOp.divide(num1, num2);
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		String expression = "";
		String result = "";
		System.out.println("Welcome to the Fraction calculator!");
		Scanner console = new Scanner(System.in);
		System.out.print("Enter an expression (or \"quit\"): ");
		// get the mathematical expression fraction, or quit
		expression = console.nextLine();
		// test fraction1 to see if the user types "quit"
		if (expression.equalsIgnoreCase("quit")) {
			System.out.println("Goodbye!");
		}
		while (!expression.equalsIgnoreCase("quit")) {
			try {
				result = compute(expression);
				System.out.println("Result is :" + result);
			} catch (Exception e) {
				System.out.println("Exception occurred during calculations : " + e.getMessage());
			}
			System.out.print("Enter an expression (or \"quit\"): ");
			expression = console.nextLine();
			if (expression.equalsIgnoreCase("quit")) {
				System.out.println("Goodbye!");
			}
		}
		console.close();
	}
}
