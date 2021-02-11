package br.com.calculator.javaspringcalculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.calculator.javaspringcalculator.Calculator;

@RestController
@RequestMapping("/calculator1")
public class Calculator1Controller {

	@GetMapping
	public String calculator(@RequestParam(value = "num1") String num1, @RequestParam(value = "num2") String num2, @RequestParam(value = "oper") String oper) {
		Double res = 0.0;

		Calculator calc = new Calculator();
		
		switch (oper) {
		case "add":
			res = calc.add(Double.valueOf(num1), Double.valueOf(num2));
			break;
		case "div":
			res = calc.div(Double.valueOf(num1), Double.valueOf(num2));			
			break;
		case "mul":
			res = calc.mul(Double.valueOf(num1), Double.valueOf(num2));
			break;
		case "sub":
			res = calc.sub(Double.valueOf(num1), Double.valueOf(num2));			
			break;
		}
		
		return String.format("%s %s %s = %s", num1, oper, num2, res.toString());
	}	
}
