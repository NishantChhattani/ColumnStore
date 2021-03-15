package codb.query;

import SciLib.Math.bitStream;

class Restriction {
	private SimpleRestriction operand1;
	private Operator operator;
	private Restriction operand2;

	public Restriction(SimpleRestriction operand1) {
		this.operand1 = operand1;
		this.operator = null;
		this.operand2 = null;
	}

	private Restriction(SimpleRestriction operand1, Operator op,
			Restriction operand2) {
		this.operand1 = operand1;
		this.operator = op;
		this.operand2 = operand2;
	}

	public Restriction addRestriction(Operator op, SimpleRestriction operand) {

		Restriction newRestriction = null;

		if (operand1 == null) {
			// Simply add the condition as is
			operator = op;
			this.operand1 = operand;
			newRestriction = this;
		} else {
			newRestriction = new Restriction(operand, op, this);
		}
		return newRestriction;
	}

	public bitStream eval() {
		bitStream result = null;

		assert (operand1 != null);

		// But operand2 could be null
		if (operand2 == null) {
			result = operand1.eval();
		}
		else{
			switch (operator) {
			case AND:
				result = operand1.eval().and(operand2.eval());
				break;
			case OR:
				result = operand1.eval().or(operand2.eval());
				break;
			case NOT:
				result = operand1.eval().not();
				break;
			}
		} 
		return result;
	}
}