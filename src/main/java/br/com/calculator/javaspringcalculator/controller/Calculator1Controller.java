package br.com.calculator.javaspringcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.calculator.javaspringcalculator.identities.Operation;
import br.com.calculator.javaspringcalculator.mongodb.CalculatorRepository;
import br.com.calculator.javaspringcalculator.services.CalculatorServices;

@RestController
@RequestMapping("/calculator1")
public class Calculator1Controller {
	
	private CalculatorServices calculatorServices;
	
	@Autowired
	private CalculatorRepository repository;
	
    public Calculator1Controller(CalculatorServices calculatorServices) {
        this.calculatorServices = calculatorServices;
    }

	@GetMapping
	public String calculator(@RequestParam(value = "num1") String num1, @RequestParam(value = "num2") String num2, @RequestParam(value = "oper") String oper) {
		
		Double res = calculatorServices.executeCalcutation(Double.valueOf(num1), Double.valueOf(num2), oper);
						
		repository.save(new Operation(Double.valueOf(num1), Double.valueOf(num2), oper, res));
		
		return String.format("%s %s %s = %s", num1, oper, num2, res.toString());
	}	
}
