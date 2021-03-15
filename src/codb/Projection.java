package codb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import codb.engine.Database;

public class Projection {
	private String name;
	private HashMap<String,Column> columns;
	private String anchorTable;
	private List<Column> sortKey;

	public Projection(String name, String anchorTable){
		this.name = name;
		this.anchorTable = anchorTable;
		columns = new HashMap<String,Column>();
		sortKey = new ArrayList<Column>();
	}
	
	public HashMap<String,Column> getColumns() {
		return columns;
	}

	public String getAnchorTable() {
		return anchorTable;
	}

	List<Column> getSortKey() {
		return sortKey;
	}

	void setSortKey(List<Column> sortKey) {
		this.sortKey = sortKey;
	}

	public String getName() {
		return name;
	}
	
	public Column getColumn(String name){
		return columns.get(name); 
	}
	
	public void addColumn(Column col){
		columns.put(col.getColUri(), col);
		Database.getInstance().registerColumn(col);
	}	
}
