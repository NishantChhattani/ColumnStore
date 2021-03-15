package codb.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import codb.Column;
import codb.ColumnDataFile;
import codb.StoredValue;

public class FileConverter {
	private Column loadTxt(String uri, String dataType, String inputName, String outputName) {
		ColumnDataFile col = new ColumnDataFile(uri, dataType, outputName);

		try {
			FileReader input = new FileReader(inputName);
			BufferedReader bufRead = new BufferedReader(input);

			String line;
			int count = 0;

			// Read through file one line at time. Print line # and line
			while ((line = bufRead.readLine()) != null) {
				if(col.getDataType().equals("stringColumn")){
					col.addValue(new StoredValue(uri, count, line));					
				}
				else if(col.getDataType().equals("numberColumn")){
					Double d = new Double(line);
					col.addValue(new StoredValue(uri, count, d));
				}
				else{
					assert(false);
				}
				count++;
			}

			bufRead.close();
			
			col.store();
			
			return col;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void loadDat(String uri, String inputName, String dataType, String outputName) {
		ColumnDataFile col = new ColumnDataFile(uri, dataType, inputName);
		col.load();
		
		try {
			PrintWriter out
			   = new PrintWriter(new BufferedWriter(new FileWriter(outputName)));

			// Write through file one line at time. Print line # and line
			Iterator<StoredValue> valIter = col.getValueIter();
			while(valIter.hasNext()){
				String line =  valIter.next().toString();
				out.println(line);
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void txt2dat(String uri, String dataType, String inputName) {
		String outputName = CodbUtils.getFirstPart(inputName, ".") + ".dat";
		
		FileConverter conv = new FileConverter();
		
		System.out.println("Converting " + inputName);
		conv.loadTxt(uri, dataType, inputName, outputName);
		System.out.println("Created " + outputName);
	}
	
	public void dat2txt(String uri, String dataType, String inputName) {
		String outputName = CodbUtils.getFirstPart(inputName, ".") + ".txt";
		
		FileConverter conv = new FileConverter();
		
		System.out.println("Converting " + inputName);
		conv.loadDat(uri, inputName, dataType, outputName);
		System.out.println("Created " + outputName);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String cmd = args[0];
		String uri = args[1];
		String dataType = args[2];
		String inputName = args[3];
		
		FileConverter conv = new FileConverter();
		if( cmd.equals("txt2dat")){
			conv.txt2dat(uri, dataType, inputName);
		}
		else if( cmd.equals("dat2txt")){
			conv.dat2txt(uri, dataType, inputName);
		}
		
	}
}
