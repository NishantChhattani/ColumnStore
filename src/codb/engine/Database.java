/**
 * 
 */
package codb.engine;

import java.util.HashMap;

import codb.Column;
import codb.Projection;
import codb.ProjectionUniverse;

/**
 * @author rchandra
 * 
 */
public class Database {
	private static Database database = null;
	
	private String name;
	private Loader loader;
	private ProjectionUniverse projections;
	private HashMap<String,Column> columns;
	
	private Database() {
		super();
		projections = new ProjectionUniverse();
		columns = new HashMap<String,Column>();
		loader = new Loader(this);
	}

	public void load(String configFile){
		loader.loadDatabase(configFile);
	}
	
	public static Database getInstance(){
		if(database == null){
			database = new Database();		
		}
		return database;
	}
	
	public void addProjection(Projection projection){
		projections.add(projection);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void registerColumn(Column column){
		// TODO - Check for uniqueness of name
		columns.put(column.getColUri(), column);
	}
	
	public Column getColumn(String colUri){
		return columns.get(colUri);
	}

	public ProjectionUniverse getProjections() {
		return projections;
	}
}
