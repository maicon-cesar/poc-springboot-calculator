package br.com.calculator.javaspringcalculator.identities;

public class Operation {

	private Double input1;
	private Double input2;
	
	private String oper;
	
	private Double output;
	
	public Operation(Double input1, Double input2, String oper) {
		super();
		this.input1 = input1;
		this.input2 = input2;
		this.oper = oper;
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
}
