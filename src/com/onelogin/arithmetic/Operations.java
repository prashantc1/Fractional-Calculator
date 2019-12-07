package com.onelogin.arithmetic;

import com.onelogin.number.MixedNumber;

public interface Operations {

	public MixedNumber add(MixedNumber n1, MixedNumber n2);
	
	public MixedNumber subtract(MixedNumber n1, MixedNumber n2);
	
	public MixedNumber multiply(MixedNumber n1, MixedNumber n2);
	
	public MixedNumber divide(MixedNumber n1, MixedNumber n2);
}
