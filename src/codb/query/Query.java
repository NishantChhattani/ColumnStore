package codb.query;
import java.util.HashMap;
import java.util.List;

import codb.RecordSet;

public class Query {
	Restriction	rootRestriction;  // This toplevel restriction could have multiple embedded restrictions
	HashMap<String,SimpleRestriction> restrictionColUriList; // List of all restriction in the query
	QueryEngine queryEngine;
	
	public Query() {
		rootRestriction = null;
		restrictionColUriList = new HashMap<String,SimpleRestriction>();
		queryEngine = new QueryEngine();
	}
	
	// Default operator is AND
	public void addCondition(SimpleCondition condition) {
		this.addCondition(condition, Operator.AND);
	}
	
	public void addCondition(SimpleCondition condition, Operator operator){
		//See if a restriction already exists for this condition's column
		SimpleRestriction res = restrictionColUriList.get(condition.getColUri());
		
		if( res == null){
			// Create a new restriction with this condition
			SimpleRestriction sres = new SimpleRestriction(condition);

			if( this.rootRestriction == null){
				//Root restriction does not exist
				restrictionColUriList.put(condition.getColUri(), sres);
				this.rootRestriction = new Restriction(sres);
			}
			else{
				//Root restriction already exists
				restrictionColUriList.put(condition.getColUri(), sres);
				rootRestriction = rootRestriction.addRestriction(operator, sres);
			}
		}
		else{
			// Simply add condition to existing restriction
			res.addCondition(operator, condition);			
		}
	}
	public RecordSet select(List<String> colNames){
		assert(rootRestriction != null);
		return queryEngine.getRecords(colNames, rootRestriction);
	}

}
