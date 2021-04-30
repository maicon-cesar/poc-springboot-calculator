package br.com.calculator.javaspringcalculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.calculator.javaspringcalculator.Calculator;
import br.com.calculator.javaspringcalculator.identities.Operation;
import br.com.calculator.javaspringcalculator.mongodb.CalculatorRepository;

@Service
public class CalculatorServices {
	
	@Autowired
	private CalculatorRepository repository;

	public Double executeCalculation(Double num1, Double num2, String oper) {
		Calculator calc = new Calculator();
		Double res = 0.0;
		
		switch (oper) {
		case "add":
			res = calc.add(num1, num2);
			break;
		case "div":
			res = calc.div(num1, num2);
			break;
		case "mul":
			res = calc.mul(num1, num2);
			break;
		case "sub":
			res = calc.sub(num1, num2);			
			break;
		}
		
		return res;
	}
	
	public void saveCalculation(Operation oper) {
		repository.save(oper);
	}
}
