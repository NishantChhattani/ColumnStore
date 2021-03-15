package codb.util;

import java.io.PrintStream;
import java.util.Iterator;

import codb.GenericValue;
import codb.Record;
import codb.RecordSet;

public class CodbUtils {
	public static String getFirstPart(String str, String delim){
		return str.substring(0, str.indexOf(delim, 0));
	}
	public static String getSecondPart(String str, String delim){
		return str.substring(str.indexOf(delim, 0)+1);
	}
	
	public static void dumpRecord(Record rec, PrintStream os){
		Iterator<GenericValue> iter = rec.getAttrValues().iterator();
		while(iter.hasNext()){
			os.print(iter.next().toString() + "1st\t");
		}
		os.println("");
	}
	public static void dumpRecordSet(RecordSet rs, PrintStream os){
		Iterator<String> nameIter = rs.getAttrNames().iterator();
		while(nameIter.hasNext()){
			os.print(nameIter.next()+"2nd\t");
		}
		os.println("");
		
		Iterator<Record> iter = rs.getRecords().values().iterator();
		while(iter.hasNext()){
			dumpRecord(iter.next(),os);
		}
	}
}