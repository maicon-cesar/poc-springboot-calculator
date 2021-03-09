package br.com.calculator.javaspringcalculator.identities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "historic")
public class Operation {
	
	@Id
	private String id;

	private Double input1;
	private Double input2;
	
	private String oper;
	
	private Double output;
	
	public Operation(Double input1, Double input2, String oper, Double output) {
		super();
		this.input1 = input1;
		this.input2 = input2;
		this.oper = oper;
		this.output = output;
	}

	public Double getOutput() {
		return output;
	}

	public void setOutput(Double output) {
		this.output = output;
	}

	public Double getInput1() {
		return input1;
	}

	public void setInput1(Double input1) {
		this.input1 = input1;
	}

	public Double getInput2() {
		return input2;
	}

	public void setInput2(Double input2) {
		this.input2 = input2;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}
	
	@Override
	public String toString() {
		return String.format("Operation[id='%s', input1='%s', oper='%s', input2='%s', output='%s']", 
				id, Double.toString(input1), oper, Double.toString(input2), Double.toString(output));
	}
}
