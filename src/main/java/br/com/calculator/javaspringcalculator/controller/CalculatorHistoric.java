package br.com.calculator.javaspringcalculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calculator.javaspringcalculator.identities.Operation;
import br.com.calculator.javaspringcalculator.mongodb.CalculatorRepository;

@RestController
@RequestMapping("/historic")
public class CalculatorHistoric {

	@Autowired
	private CalculatorRepository repository;

	@GetMapping
	public List<Operation> getHistoric() {
	    return repository.findAll();
	}
}
