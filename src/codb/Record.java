/**
 * 
 */
package codb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rchandra
 * 
 */
public class Record {
	Integer storageKey;

	List<GenericValue> attrValues;

	public Record() {
		this.storageKey = -1;
		this.attrValues = new ArrayList<GenericValue>();
	}

	public boolean addAttrValue(StoredValue value) {
		if (this.storageKey < 0) {
			this.storageKey = value.getStorageKey();
		}
		boolean isValid = this.storageKey == value.getStorageKey();

		if (isValid) {
			attrValues.add(value.getRawValue());
		}
		return isValid;
	}

	public int getStorageKey() {
		return this.storageKey;
	}

	public List<GenericValue> getAttrValues() {
		return this.attrValues;
	}
}
