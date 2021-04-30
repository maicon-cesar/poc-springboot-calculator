package br.com.calculator.javaspringcalculator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calculator.javaspringcalculator.identities.Operation;
import br.com.calculator.javaspringcalculator.services.CalculatorServices;

@RestController
@RequestMapping("/calculator2")
public class Calculator2Controller {
	
	private CalculatorServices calculatorServices;
	
    public Calculator2Controller(CalculatorServices calculatorServices) {
        this.calculatorServices = calculatorServices;
    }
	
	@PostMapping
	public Operation calculate(@RequestBody Operation oper) {

		Double res = calculatorServices.executeCalculation(oper.getInput1(), oper.getInput2(), oper.getOper());		
		oper.setOutput(res);
			
		calculatorServices.saveCalculation(oper);

		return oper;
	}	
}