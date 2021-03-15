package codb.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import SciLib.Math.bitStream;
import codb.Projection;
import codb.RecordSet;
import codb.StoredValue;
import codb.engine.Database;

class QueryEngine {
	private Database database;

	public QueryEngine(){
		database = Database.getInstance();
	}
	
	public RecordSet getRecords(List<String> colNames, Restriction restriction){
		// Current implementation can support only one universal projection
		// comprised of all the columns in the table

		Projection proj = database.getProjections().get(0);
		
		RecordSet rs = new RecordSet();
		
		bitStream bits = restriction.eval();
		
		Iterator<String> colIter = colNames.iterator();
		while(colIter.hasNext()){
			String colName = colIter.next();
			ArrayList<StoredValue> col = proj.getColumn(colName).getFilteredColumn(bits);
			rs.mergeColumn(col);
			
		}
		return rs;
	}

}
