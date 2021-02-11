package br.com.calculator.javaspringcalculator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calculator.javaspringcalculator.Calculator;
import br.com.calculator.javaspringcalculator.identities.Operation;

@RestController
@RequestMapping("/calculator2")
public class Calculator2Controller {
	
	@PostMapping
	public Operation calculate(@RequestBody Operation oper) {

		Calculator calc = new Calculator();
		
		switch (oper.getOper()) {
		case "add":
			oper.setOutput(calc.add(oper.getInput1(), oper.getInput2()));
			break;
		case "div":
			oper.setOutput(calc.div(oper.getInput1(), oper.getInput2()));			
			break;
		case "mul":
			oper.setOutput(calc.mul(oper.getInput1(), oper.getInput2()));
			break;
		case "sub":
			oper.setOutput(calc.sub(oper.getInput1(), oper.getInput2()));			
			break;
		}
		
		return oper;
	}	
}
