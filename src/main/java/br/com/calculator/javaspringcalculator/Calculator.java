package br.com.calculator.javaspringcalculator;

public class Calculator {

	public Double add (Double num1, Double num2) {
		return (num1 + num2);
	}
	
	public Double sub (Double num1, Double num2) {
		return (num1 - num2);
	}

	public Double mul (Double num1, Double num2) {
		return (num1 * num2);
	}

	public Double div (Double num1, Double num2) {
		if (num2 == 0)
			return 0.0;
		return (num1 / num2);
	}
}
