package codb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ColumnDataFile extends Column {

	private String dataFileName;

	public ColumnDataFile(String uri, String dataType, String dataFileName) {
		super(uri, dataType);
		this.dataFileName = dataFileName;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	@SuppressWarnings("unchecked")
	public void load() {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(dataFileName);
			in = new ObjectInputStream(fis);
			storedValues = (ArrayList<StoredValue>) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void store() {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(dataFileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(storedValues);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
