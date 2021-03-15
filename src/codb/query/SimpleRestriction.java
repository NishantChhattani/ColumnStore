package codb.query;

import SciLib.Math.bitStream;
import codb.Column;
import codb.engine.Database;

 class SimpleRestriction{
	// A simple restriction always applies only to a single column
	protected Column column;

	private Condition condition;

	public SimpleRestriction(SimpleCondition condition) {
		column = Database.getInstance().getColumn(condition.getColUri());
		this.condition = new Condition(condition);
	}
	
	public void addCondition(Operator op, SimpleCondition newCondition){
		condition.addCondition(op, newCondition);
	}
	public bitStream eval(){
		assert(column!=null);
		return column.restrict(condition);		
	}
}
