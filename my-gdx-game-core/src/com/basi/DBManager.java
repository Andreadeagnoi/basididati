package com.basi;

import java.util.ArrayList;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

/**
 * Note to self: be aware to when you save something on the db. Game data life
 * cycle: 
 * 1) When the game starts and a save is selected, all the pertaining
 * information are loaded on memory. 
 * 2) When some information are needed they
 * will use the db, for example to check the equippable items. 
 * 3) When you save first the information about the save are loaded in temporary structures, then
 * the game will check if the save is different from the game data, and after
 * that it will update the update.
 * 
 * OR
 * 
 * 
 * 
 * @author Andrea
 *
 */
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

	//create a save and return the info about the save
	public SaveData insertSave(String name){
		//insert save in the db
		try {
			dbHandler.execSQL("INSERT INTO SALVATAGGIO_GIOCATORE (Nome, TempoGiocato)"
					+ " VALUES ('" + name + "', 0)");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
			return null;
		}
		//retrieve the save
		DatabaseCursor cursor = null;
		try {
			cursor = dbHandler.rawQuery("SELECT Nome,DataCreazione,TempoGiocato,DataUltimoSalvataggio "
					+ "FROM SALVATAGGIO_GIOCATORE ORDER BY DataCreazione DESC;") ;
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

		SaveData genSave = new SaveData(cursor.getString(1),
				cursor.getString(3),
				cursor.getInt(2),
				cursor.getString(0));
		return genSave;
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

	public ArrayList<CharacterData> getParty() {
		ArrayList<CharacterData> charList = new ArrayList<CharacterData>();
		DatabaseCursor cursor = null;
		CharacterData tempChar;
		
		try {
			cursor = dbHandler.rawQuery("SELECT DataCreazione, Id_personaggio, Nome, Sprite"
					+ "IP_HP, IP_MP ,IP_ATK, IP_DEF, IP_INT, IP_AGI, "
					+ "ID_Classe, NomeClasse,"
					+ "InUso, LivelloClasse, EXP "
					+ "FROM ISTANZA_PERSONAGGIO NATURAL JOIN Appartiene NATURAL JOIN CLASSE");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

		while (cursor.next()) {
			tempChar = new CharacterData
					.CharacterBuilder(cursor.getString(0), cursor.getInt(1))
					.name(cursor.getString(2))
					.sprite(cursor.getString(3))
					.hpmp(cursor.getInt(4), cursor.getInt(5))
					.atkdef(cursor.getInt(6), cursor.getInt(7))
					.intagi(cursor.getInt(8), cursor.getInt(9))
					.activeClass(cursor.getString(11), cursor.getInt(13), cursor.getInt(14))
					.build();
			charList.add(tempChar);
			Gdx.app.log("DatabaseTest",charList.get(0).toString());
			Gdx.app.log("DatabaseTest", String.valueOf(cursor.getInt(1)));
		}


		return charList;
	}
}
