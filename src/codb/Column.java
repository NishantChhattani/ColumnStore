/**
 * 
 */
package codb;
import java.util.ArrayList;
import java.util.Iterator;

import SciLib.Math.bitStream;
import codb.query.Condition;

/**
 * @author rchandra
 *
 */
public class Column{
	private String colUri;
	private String dataType;
	
	protected ArrayList<StoredValue> storedValues;
	
	public Column(String uri, String dataType) {
		super();
		this.colUri = uri;
		this.dataType = dataType;
		storedValues = new ArrayList<StoredValue>();
	}

	public Column(String uri,ArrayList<StoredValue> storedValues) {
		super();
		this.colUri = uri;
		this.storedValues = storedValues;
	}

	public void addValue(StoredValue value){
		if( value.getStorageKey() > storedValues.size()){
			
		}
		else{
			storedValues.add(value.getStorageKey(),value);
		}
	}
	
	public String getColUri() {
		return colUri;
	}
	public Iterator<StoredValue> getValueIter() {
		return storedValues.iterator();
	}

	public bitStream restrict(Condition condition) {
		bitStream result = new bitStream();
		Iterator<StoredValue> iter = storedValues.iterator();
		while(iter.hasNext()){
			StoredValue value = iter.next();
			result.add(condition.eval(value));
		}
		return result;
	}

	public ArrayList<StoredValue> getFilteredColumn(bitStream bits) {
		Iterator<StoredValue> iter = storedValues.iterator();
		ArrayList<StoredValue> filteredColumn = new ArrayList<StoredValue>();

		int count=0;
		while(iter.hasNext()){
			StoredValue value = iter.next();
			if( bits.bitAt(count) == '1')
				filteredColumn.add(value);
			count++;
		}
		return filteredColumn;
	}

	public String getDataType() {
		return dataType;
	}
}
