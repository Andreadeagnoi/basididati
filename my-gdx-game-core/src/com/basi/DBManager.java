package com.basi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class DBManager {
	Database dbHandler;

	public static final String TABLE_COMMENTS = "prova";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "comment";

	private static final String DATABASE_NAME = "rpg.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = sqlFromFile("data/createDB.txt");

	public DBManager() {
		dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME,
				DATABASE_VERSION, DATABASE_CREATE, null);

		dbHandler.setupDatabase();
		try {
			dbHandler.openOrCreateDatabase();
			dbHandler.execSQL(DATABASE_CREATE);
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
	}
	//temp method to test the dbManager
	public int insertSaveTemp(){
		try {
			dbHandler.execSQL("INSERT INTO SALVATAGGIO_GIOCATORE (Nome, TempoGiocato)"
					+ " VALUES ('Valchirie', 0)");
			return 1;
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private static final String sqlFromFile(String path){
		FileHandle file = Gdx.files.internal("createDB.txt");
		String sql = file.readString();
		return sql;
	}
	
	private static final void createTables(){
		
	}

}
