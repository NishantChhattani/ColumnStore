package codb;

import java.io.Serializable;


public class GenericValue implements Serializable{
	/**
	 * 
	 */
	static final long serialVersionUID = -8820651657149579907L;
	
	private Object value;
	
	public GenericValue(String value) {
		this.value = value;
	}

	public GenericValue(Integer value) {
		this.value = value;
	}

	public GenericValue(Double value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public int compareTo(GenericValue operand){
		Comparable c = (Comparable) value;
		return c.compareTo(operand.value);
	}
	
	public String toString(){
		return value.toString();
	}
}
