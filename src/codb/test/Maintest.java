package codb.test;

import java.util.ArrayList;
import java.util.List;

import codb.GenericValue;
import codb.RecordSet;
import codb.engine.Database;
import codb.query.Operator;
import codb.query.Query;
import codb.query.SimpleCondition;
import codb.util.CodbUtils;

public class Maintest {
	public static void main(String[] args) {
		String configFile = "dbconfig.xml";
		Database db = Database.getInstance();
		db.load(configFile);

		List<String> colNames = new ArrayList<String>();

		colNames.add("testdb.employee.id");
		colNames.add("testdb.employee.salary");
		colNames.add("testdb.employee.age");

		Query query = new Query();

		SimpleCondition cond1 = new SimpleCondition("testdb.employee.id",
				Operator.GREATER, new GenericValue("05"));
		query.addCondition(cond1);
		
		SimpleCondition cond2 = new SimpleCondition("testdb.employee.salary",
				Operator.GREATER, new GenericValue(30000.0));
		query.addCondition(cond2);
		
		SimpleCondition cond3 = new SimpleCondition("testdb.employee.id",
				Operator.LESS, new GenericValue("09"));
		query.addCondition(cond3);

		SimpleCondition cond4 = new SimpleCondition("testdb.employee.age",
				Operator.EQUAL, new GenericValue(29.0));
		query.addCondition(cond4);

		RecordSet rs = query.select(colNames);
		CodbUtils.dumpRecordSet(rs, System.out);
	}
}
