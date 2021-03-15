/**
 * 
 */
package codb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author rchandra
 *
 */
public class RecordSet {
	List<String> attrNames;
	HashMap<Integer,Record> records;
	
	public RecordSet() {
		super();
		attrNames = new ArrayList<String>();
		records = new HashMap<Integer,Record>();
	}
	
	public RecordSet(List<String> attrNames, HashMap<Integer,Record> records) {
		super();
		this.attrNames = attrNames;
		this.records = records;
	}
	public List<String> getAttrNames() {
		return attrNames;
	}
	public HashMap<Integer,Record> getRecords() {
		return records;
	}
	public void addRecord (Record record){
		this.records.put(record.getStorageKey(),record);
	}
	public Record getRecord(int storageKey){
		return records.get(storageKey);
	}
	
	public void mergeColumn(List<StoredValue> colValues){
		//Expand the record with column value
		boolean isEmpty = (records.size() == 0);
		
		Iterator<StoredValue> valIter = colValues.iterator();
		while(valIter.hasNext()){
			if( isEmpty ){
				Record record = new Record();
				record.addAttrValue(valIter.next());
				records.put(record.getStorageKey(),record);				
			}
			else{
				// Get the record corresponding to the storage key
				StoredValue nextValue = valIter.next();
				Record record = records.get(nextValue.getStorageKey());
				assert(record!=null);
				record.addAttrValue(nextValue);
			}			
		}
		
	}	
}
