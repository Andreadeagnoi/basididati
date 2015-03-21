package com.basi;

import java.util.ArrayList;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class DBManager {
	Database dbHandler;	

	private static final String DATABASE_NAME = "rpg.db";
	private static final int DATABASE_VERSION = 1;




	public DBManager() {
		dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME,
				DATABASE_VERSION, ResPack.DBCREATE, null);
		dbHandler.setupDatabase();
		createTables();

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


	/** creating the necessary tables for the db for the first time
	 * 
	 */
	private  void createTables(){
		try {
			dbHandler.openOrCreateDatabase();
			dbHandler.execSQL(ResPack.q_SALVATAGGIO); //Created when I create the db
			dbHandler.execSQL(ResPack.q_PERSONAGGIO);	
			dbHandler.execSQL(ResPack.q_ISTANZA_PERSONAGGIO);
			dbHandler.execSQL(ResPack.q_CLASSE);
			dbHandler.execSQL(ResPack.q_OGGETTO);
			dbHandler.execSQL(ResPack.q_STATO);
			dbHandler.execSQL(ResPack.q_TECNICA);
			dbHandler.execSQL(ResPack.q_APPARTIENE);
			dbHandler.execSQL(ResPack.q_CEDE);
			dbHandler.execSQL(ResPack.q_EQUIPAGGIA);
			dbHandler.execSQL(ResPack.q_EQUIPAGGIABILE);
			dbHandler.execSQL(ResPack.q_IMPARA);
			dbHandler.execSQL(ResPack.q_MODIFICAO);
			dbHandler.execSQL(ResPack.q_MODIFICAT);
			dbHandler.execSQL(ResPack.q_POSSIEDE);
			dbHandler.execSQL(ResPack.q_PROMUOVE);
			dbHandler.execSQL(ResPack.q_RICHIEDE);
			dbHandler.execSQL(ResPack.q_UTILIZZA);		

		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

	}


	/**
	 * Select query to get all the saves.
	 * @return a list of SaveData
	 */
	public ArrayList<SaveData> getSaves() {
		ArrayList<SaveData> saveList = new ArrayList<SaveData>();
		DatabaseCursor cursor = null;
		SaveData tempSave;

		try {
			cursor = dbHandler.rawQuery("SELECT Nome,DataCreazione,TempoGiocato,DataUltimoSalvataggio "
					+ "FROM SALVATAGGIO_GIOCATORE ;") ;
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

		while (cursor.next()) {

			saveList.add(new SaveData(cursor.getString(1),
					cursor.getString(3),
					cursor.getInt(2),
					cursor.getString(0)));
			Gdx.app.log("DatabaseTest",saveList.get(0).toString());
			Gdx.app.log("DatabaseTest", String.valueOf(cursor.getInt(1)));
		}


		return saveList;
	}

}
