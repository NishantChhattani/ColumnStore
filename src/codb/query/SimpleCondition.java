package codb.query;

import codb.GenericValue;
import codb.StoredValue;

public class SimpleCondition{
	String colUri;
	private Operator operator;
	private GenericValue operand;

	public SimpleCondition(String uri, Operator operator,GenericValue operand) {
		this.colUri = uri;
		this.operator = operator;
		this.operand = operand;
	}

	public boolean eval(StoredValue storedValue) {
		boolean result = false;
		
		if (storedValue.getUri().equals(this.getColUri())) {
			switch (operator) {
			case EQUAL:
				result = storedValue.compareTo(operand) == 0;
				break;
			case LESS:
				result = storedValue.compareTo(operand) < 0;
				break;
			case GREATER:
				result = storedValue.compareTo(operand) > 0;
				break;
			case LESS_EQUAL:
				result = storedValue.compareTo(operand) <= 0;
				break;
			case GREATER_EQUAL:
				result = storedValue.compareTo(operand) >= 0;
				break;
			}
		} else {
			// If the condition is not applicable to this column, simply return true
			result =  true;
		}
		return result;
	}

	public String getColUri() {
		return colUri;
	}
}
