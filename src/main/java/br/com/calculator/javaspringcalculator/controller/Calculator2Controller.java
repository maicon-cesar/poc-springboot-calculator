package br.com.calculator.javaspringcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calculator.javaspringcalculator.identities.Operation;
import br.com.calculator.javaspringcalculator.mongodb.CalculatorRepository;
import br.com.calculator.javaspringcalculator.services.CalculatorServices;

@RestController
@RequestMapping("/calculator2")
public class Calculator2Controller {
	
	private CalculatorServices calculatorServices;
	
	@Autowired
	private CalculatorRepository repository;	
	
    public Calculator2Controller(CalculatorServices calculatorServices) {
        this.calculatorServices = calculatorServices;
    }
	
	@PostMapping
	public Operation calculate(@RequestBody Operation oper) {

		Double res = calculatorServices.executeCalcutation(oper.getInput1(), oper.getInput2(), oper.getOper());		
		oper.setOutput(res);
			
		repository.save(oper);

		return oper;
	}	
}