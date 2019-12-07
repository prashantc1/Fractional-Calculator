package com.onelogin.number;

/**
* MixedNumber is a class that represent mathematical mixed numbers, improper fractions and also whole numbers
*  
*/

public class MixedNumber {
	
	
	private String value; //String representation of a mixed number "3_1/2" or "-1/2", etc.
	private int wholeNumber = 0; // the whole number part of the mixed number - "3" from the example above.
	private int fractionNumerator = 0; // numerator of the fractional part of the mixed number - "1" from the example above.
	private int fractionDenominator = 1; // denominator of the fractional part of the mixed number - "2" from the example above.
	
	public static final String WHOLE_DELIMITER = "_";
	public static final String FRACTION_DELIMITER = "/";
	
	public MixedNumber(String value) {
		this.value = value;
		validateNumber(value);
	}
	
	public MixedNumber(int wholeNumber, int fractionNumerator, int fractionDenominator) {
		this.wholeNumber = wholeNumber;
		this.fractionNumerator = fractionNumerator;
		if(fractionDenominator == 0) throw new IllegalArgumentException("Denominator cannot be 0.");
		this.fractionDenominator = fractionDenominator;
		this.value = this.toString();
	}

	public void setValue(String value) {
		this.value = value;
		validateNumber(value);
	}

	public int getWholeNumber() {
		return wholeNumber;
	}

	public void setWholeNumber(int wholeNumber) {
		this.wholeNumber = wholeNumber;
		handleNegatives();
	}
	
	public int getFractionNumerator() {
		return fractionNumerator;
	}
	
	public void setFractionNumerator(int fractionNumerator) {
		this.fractionNumerator = fractionNumerator;
		handleNegatives();
	}
	
	public int getFractionDenominator() {
		return fractionDenominator;
	}
	
	public void setFractionDenominator(int fractionDenominator) {
		if(fractionDenominator == 0) throw new IllegalArgumentException("Denominator cannot be 0.");
		this.fractionDenominator = fractionDenominator;
		handleNegatives();
	}
	
	public void validateNumber(String value) {
		this.value = value;
		
		//check if number is blank
		if(value == null || value.trim().equals("")) throw new IllegalArgumentException("Number cannot be blank.");
		
		//check if there are more than 1 WHOLE_DELIMITER
		if(value.lastIndexOf(WHOLE_DELIMITER) != value.indexOf(WHOLE_DELIMITER)) throw new NumberFormatException("Invalid Number - too many - " + WHOLE_DELIMITER);
		//check if there are more than 1 FRACTION_DELIMITER
		if(value.lastIndexOf(FRACTION_DELIMITER) != value.indexOf(FRACTION_DELIMITER)) throw new NumberFormatException("Invalid Number - too many - " + FRACTION_DELIMITER);
		//check if there is WHOLE_DELIMITER but no FRACTION_DELIMITER. If there is no FRACTION_DELIMITER, WHOLE_DELIMITER should also not be there.
		if(value.indexOf(WHOLE_DELIMITER) != -1 && value.indexOf(FRACTION_DELIMITER) == -1 ) throw new NumberFormatException("Invalid Number - missing fraction.");

		String[] nums = value.split(WHOLE_DELIMITER);
		if(nums.length > 1) { //means that the argument is a mixed number - whole number present along with fraction part
			this.wholeNumber = Integer.parseInt(nums[0]);
			String[] fraction = nums[1].split(FRACTION_DELIMITER); //determine the fractional part
			this.fractionNumerator = Integer.parseInt(fraction[0]);
			this.fractionDenominator = Integer.parseInt(fraction[1]);
			if(this.fractionDenominator == 0) throw new IllegalArgumentException("Denominator cannot be 0.");
		}
		else { //either the argument is only fractional or only a whole number
			String[] fraction = value.split(FRACTION_DELIMITER);
			if(fraction.length > 1) { // the argument is fraction
				this.fractionNumerator = Integer.parseInt(fraction[0]);
				this.fractionDenominator = Integer.parseInt(fraction[1]);
				if(this.fractionDenominator == 0) throw new IllegalArgumentException("Denominator cannot be 0.");
			}
			else { // the argument is only a whole number
				this.wholeNumber = Integer.parseInt(value);
			}
		}
		handleNegatives();
	}
	
	private void handleNegatives() {
		if(this.wholeNumber < 0) {
			this.fractionNumerator = Math.abs(this.fractionNumerator);
			this.fractionDenominator = Math.abs(this.fractionDenominator);
		}
		
		if(this.fractionDenominator < 0) {
			this.fractionNumerator = -1 * this.fractionNumerator;
			this.fractionDenominator = Math.abs(this.fractionDenominator);
		}
	}
	
	public String toString() {
		String value = "";
		if(this.wholeNumber != 0 || (this.fractionNumerator == 0)) value = value + this.wholeNumber;
		if(this.wholeNumber != 0 && this.fractionDenominator != 0 && this.fractionNumerator != 0) value += this.WHOLE_DELIMITER;
		if(this.fractionNumerator != 0) value += this.fractionNumerator+this.FRACTION_DELIMITER+this.fractionDenominator;
		
		return value;
	}
}
