package com.onelogin.arithmetic;

import java.util.HashSet;
import java.util.Set;

import com.onelogin.number.MixedNumber;

/**
* BasicOperations is the class that does the actual mathematical
* operations of addition (+) , subtraction (-) , multiplication (*) and division (/)
*  
*/

public class BasicOperations implements Operations {

	private Set<String> operands = new HashSet<String>();
	private BasicOperations() {
		operands.add("+");
		operands.add("-");
		operands.add("*");
		operands.add("/");
	}

	private static class BasicOperationsHelper {
		private static final BasicOperations instance = new BasicOperations();
	}

	public static BasicOperations getInstance() {
		return BasicOperationsHelper.instance;
	}

	public boolean isOperationSupported(String operation) {
		return this.operands.contains(operation);
	}
	
	/**
	 * This method adds two mixed numbers - n1 + n2.
	 * @returns MixedNumber
	 * 
	 */
	public MixedNumber add(MixedNumber n1, MixedNumber n2) {

		int numerator1 = (n1.getFractionDenominator() * Math.abs(n1.getWholeNumber()))
				+ n1.getFractionNumerator();
		int denominator1 = n1.getFractionDenominator();
		int numerator2 = (n2.getFractionDenominator() * Math.abs(n2.getWholeNumber()))
				+ n2.getFractionNumerator();
		int denominator2 = n2.getFractionDenominator();
		
		if(n1.getWholeNumber() < 0) {
			numerator1 = -1 * numerator1;
		}
		if(n2.getWholeNumber() < 0) {
			numerator2 = -1 * numerator2;
		}
		
		int resultNumerator = (denominator2 * numerator1)
				+ (denominator1 * numerator2);
		int resultDenominator = denominator1 * denominator2;
		
		return getLowestFactoredNumber(resultNumerator, resultDenominator);
	}

	/**
	 * This method subtract two mixed numbers - n1 - n2.
	 * @returns MixedNumber
	 * 
	 */
	public MixedNumber subtract(MixedNumber n1, MixedNumber n2) {

		int numerator1 = (n1.getFractionDenominator() * Math.abs(n1.getWholeNumber()))
				+ n1.getFractionNumerator();
		int denominator1 = n1.getFractionDenominator();
		int numerator2 = (n2.getFractionDenominator() * Math.abs(n2.getWholeNumber()))
				+ n2.getFractionNumerator();
		int denominator2 = n2.getFractionDenominator();
		
		if(n1.getWholeNumber() < 0) {
			numerator1 = -1 * numerator1;
		}
		if(n2.getWholeNumber() < 0) {
			numerator2 = -1 * numerator2;
		}

		int resultNumerator = (denominator2 * numerator1)
				- (denominator1 * numerator2);
		int resultDenominator = denominator1 * denominator2;

		return getLowestFactoredNumber(resultNumerator, resultDenominator);
	}

	/**
	 * This method multiplies two mixed numbers - n1 x n2.
	 * @returns MixedNumber
	 * 
	 */
	public MixedNumber multiply(MixedNumber n1, MixedNumber n2) {

		if(n1.toString().equals("0") || n2.toString().equals("0")) {
			return new MixedNumber("0");
		}
		int numerator1 = (n1.getFractionDenominator() * Math.abs(n1.getWholeNumber()))
				+ n1.getFractionNumerator();
		int denominator1 = n1.getFractionDenominator();
		int numerator2 = (n2.getFractionDenominator() * Math.abs(n2.getWholeNumber()))
				+ n2.getFractionNumerator();
		int denominator2 = n2.getFractionDenominator();
		
		if(n1.getWholeNumber() < 0) {
			numerator1 = -1 * numerator1;
		}
		if(n2.getWholeNumber() < 0) {
			numerator2 = -1 * numerator2;
		}
		
		int gcd1 = gcd(numerator1, denominator1);
		int gcd2 = gcd(numerator2, denominator2);
		numerator1 = numerator1 / gcd1;
		denominator1 = denominator1 / gcd1;
		numerator2 = numerator2 / gcd2;
		denominator2 = denominator2 / gcd2;

		int resultNumerator = numerator1 * numerator2;
		int resultDenominator = denominator1 * denominator2;

		return getLowestFactoredNumber(resultNumerator, resultDenominator);
	}

	/**
	 * This method divides two mixed numbers - n1 / n2.
	 * @returns MixedNumber
	 * 
	 */
	public MixedNumber divide(MixedNumber n1, MixedNumber n2) {
		
		if(n1.toString().equals("0") && !n2.toString().equals("0")) {
			return new MixedNumber("0");
		}
		else if(n2.toString().equals("0")) {
			throw new IllegalArgumentException(
					"Cannot divide by 0.");
		}

		int numerator1 = (n1.getFractionDenominator() * Math.abs(n1.getWholeNumber()))
				+ n1.getFractionNumerator();
		int denominator1 = n1.getFractionDenominator();
		int numerator2 = (n2.getFractionDenominator() * Math.abs(n2.getWholeNumber()))
				+ n2.getFractionNumerator();
		int denominator2 = n2.getFractionDenominator();
		

		
		if(n1.getWholeNumber() < 0) {
			numerator1 = -1 * numerator1;
		}
		if(n2.getWholeNumber() < 0) {
			numerator2 = -1 * numerator2;
		}
		
		int resultNumerator = numerator1 * denominator2;
		int resultDenominator = denominator1 * numerator2;
		
		if(resultNumerator < 0 && resultDenominator < 0) {
			resultNumerator = Math.abs(resultNumerator);
			resultDenominator = Math.abs(resultDenominator);
		}

		return getLowestFactoredNumber(resultNumerator, resultDenominator);
	}

	public static int gcd(int numerator, int denominator) {

		if (numerator == 0)
			return 0;
		if (denominator == 0)
			throw new IllegalArgumentException(
					"Denominator of a GCD cannot be 0.");

		int numer, denom, remainder;
		numerator = Math.abs(numerator);
		denominator = Math.abs(denominator);
		denom = denominator;
		remainder = numerator % denominator;

		while (remainder > 0) {
			numer = denom;
			denom = remainder;
			remainder = numer % denom;
		}

		return denom;
	}

	/**
	 * This method gets the gcd; and lowers the fraction to its
	 * lowest value, then creates a MixedNumber and returns it
	 * @returns MixedNumber
	 * 
	 */
	public static MixedNumber getLowestFactoredNumber(int numerator,
			int denominator) {

		if (numerator == 0)
			return new MixedNumber("0");
		if (denominator == 0)
			throw new IllegalArgumentException(
					"Denominator of a GCD cannot be 0.");

		int gcd = gcd(numerator, denominator);
		numerator = numerator / gcd;
		denominator = denominator / gcd;

		MixedNumber resultNumber = null;
		if (Math.abs(numerator) >= denominator) {
			int remainder = Math.abs(numerator) % denominator;
			int quotient = Math.abs(numerator) / denominator;
			if(numerator < 0) {
				quotient = -1 * quotient;
			}
			resultNumber = new MixedNumber(quotient, 0, 1);
			if (remainder != 0) {
				resultNumber.setFractionNumerator(remainder);
				resultNumber.setFractionDenominator(denominator);
			}
		} else {
			resultNumber = new MixedNumber(0, numerator, denominator);
		}
		return resultNumber;
	}

}
