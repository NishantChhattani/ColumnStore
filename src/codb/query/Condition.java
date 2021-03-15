package codb.query;

import SciLib.Math.bitStream;
import codb.Column;
import codb.StoredValue;

//Package private
public class Condition {
	private String colUri;

	private SimpleCondition operand1;
	private Operator operator;
	private Condition operand2;

	boolean isValidColUri(String uri) {
		return uri.equals(colUri);
	}

	protected Condition(String colUri){
		this.colUri = colUri;
	}
	
	public Condition(SimpleCondition operand) {
		assert (operand != null);

		this.colUri = operand.getColUri();
		this.operand1 = operand;
		this.operator = null;
		this.operand2 = null;
	}

	private Condition(SimpleCondition operand1, Operator op, Condition operand2) {
		this.operand1 = operand1;
		this.operator = op;
		this.operand2 = operand2;
	}

	public Condition addCondition(Operator op, SimpleCondition operand) {

		assert (isValidColUri(operand.getColUri()));

		Condition newCondition = null;

		if (operand2 == null) {
			// Simply add the condition as is
			operator = op;
			this.operand2 = new Condition(operand);
			newCondition = this;
		} else {
			newCondition = new Condition(operand, op, this);
		}
		return newCondition;
	}

	public boolean eval(StoredValue storedValue) {
		boolean result = false;

		assert (operand1 != null);

		// But operand2 could be null !!!

		if (operand2 == null) {
			//result = storedValue.eval(operand1);
			result = operand1.eval(storedValue);
		} else {
			switch (operator) {
			case AND:
//				result = storedValue.eval(operand1)
//						&& operand2.eval(storedValue);
				result = operand1.eval(storedValue)
				&& operand2.eval(storedValue);
				break;
			case OR:
//				result = storedValue.eval(operand1)
//						|| operand2.eval(storedValue);
				result = operand1.eval(storedValue)
				|| operand2.eval(storedValue);
				break;
			case NOT:
//				result = !storedValue.eval(operand1);
				result = !operand1.eval(storedValue);
				break;
			}
		}
		return result;
	}

	public String getColUri() {
		return colUri;
	}
	
	public boolean isValidColumn (Column column){
		return colUri.equals(column.getColUri());
	}
	
	public bitStream eval(){
		// Dummy implementation 
		// The control should never get to this method
		assert(false);		
		return new bitStream();
	}
}
