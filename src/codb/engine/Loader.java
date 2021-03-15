package codb.engine;

import codb.Column;
import codb.ColumnDataFile;
import codb.Projection;

import com.dbconfig.columnListType;
import com.dbconfig.columnType;
import com.dbconfig.dbconfigDoc;
import com.dbconfig.dbconfigType;
import com.dbconfig.projectionListType;
import com.dbconfig.projectionType;

//Package private
class Loader {
	private Database database;

	public Loader(Database db) {
		this.database = db;
	}

	public void loadDatabase(String configFile) {
		dbconfigDoc doc = new dbconfigDoc();
		dbconfigType root = new dbconfigType(doc.load(configFile));

		try {
			String dbName;
			dbName = root.getdbname().getValue().getValue();
			database.setName(dbName);

			projectionListType cfgProjections = root.getprojectionList();

			// Add projections
			int numProjections = cfgProjections.getprojectionCount();
			for (int i = 0; i < numProjections; i++) {
				projectionType cfgProjection = cfgProjections
						.getprojectionAt(i);

				String projName = cfgProjection.getanchorTable().getValue()
						.getValue();
				String anchorTable = cfgProjection.getprojName().getValue()
						.getValue();

				Projection proj = new Projection(projName, anchorTable);

				columnListType cfgColumns = cfgProjection.getcolumnList();
				int numColumns = cfgColumns.getcolumnCount();
				for (int j = 0; j < numColumns; j++) {
					columnType cfgColumn = cfgColumns.getcolumnAt(j);

					String colUri = cfgColumn.getcolURI().getValue().getValue();
					String filePath = cfgColumn.getfilePath().getValue()
							.getValue();
					String colDataType = cfgColumn.getdataType().getValue();
					proj.addColumn(loadColumn(colUri, colDataType, filePath));
				}
				database.addProjection(proj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Column loadColumn(String uri, String dataType, String fileName) {
		ColumnDataFile col = new ColumnDataFile(uri, dataType, fileName);
		col.load();
		return col;
	}
}
