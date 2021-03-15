package codb;

import java.io.Serializable;

public class StoredValue implements Serializable{
	/**
	 * 
	 */
	// version id generated with "serialver" utility
	private static final long serialVersionUID = -8820651657149579907L;
	private String uri;
	private int storageKey;
	private GenericValue rawValue;
	
	public StoredValue(String uri, int storageKey, String value) {
		super();
		this.uri = uri;
		this.storageKey = storageKey;
		this.rawValue = new GenericValue(value);
	}

	public StoredValue(String uri, int storageKey, Integer value) {
		super();
		this.uri = uri;
		this.storageKey = storageKey;
		this.rawValue = new GenericValue(value);
	}

	public StoredValue(String uri, int storageKey, Double value) {
		super();
		this.uri = uri;
		this.storageKey = storageKey;
		this.rawValue = new GenericValue(value);
	}

	public int getStorageKey() {
		return storageKey;
	}

	public String getUri() {
		return uri;
	}
	public int compareTo(GenericValue operand){
		return rawValue.compareTo(operand);
	}
	
	
	public String toString(){
		return rawValue.toString();
	}

	public GenericValue getRawValue() {
		return rawValue;
	}
}
