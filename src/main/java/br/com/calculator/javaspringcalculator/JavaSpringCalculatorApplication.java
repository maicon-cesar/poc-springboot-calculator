package br.com.calculator.javaspringcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaSpringCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringCalculatorApplication.class, args);
	}
	
	@GetMapping("/calculator")
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
