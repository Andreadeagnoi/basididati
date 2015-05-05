package com.basi;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;
import com.basi.Item.ConsumableItemData;
import com.basi.Item.EquippableItemData;
import com.basi.Item.ItemData;
import com.basi.Item.KeyItemData;
import com.libgdx.tools.generateString;

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

		DatabaseCursor cursor = null;
		//insert save in the db
		try {
			dbHandler.execSQL("INSERT INTO SALVATAGGIO_GIOCATORE (Nome, TempoGiocato)"
					+ " VALUES ('" + name + "', 0)");
			
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
			return null;
		}
		//retrieve the save
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
		//initialize data for new save with character with id 1
		try {
			cursor = dbHandler.rawQuery("SELECT * "
					+ "FROM personaggio WHERE id_personaggio = 1") ;
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		try {
			dbHandler.execSQL("INSERT INTO istanza_personaggio "
					+ " VALUES (1,'" + genSave.getCreationTime() + 
						"','" + cursor.getString(1) +
						"','" + cursor.getString(2) +
						"'," + cursor.getInt(3) + 
						"," + cursor.getInt(4) + 
						"," + cursor.getInt(5) + 
						"," + cursor.getInt(6) + 
						"," + cursor.getInt(7) + 
						"," + cursor.getInt(8) + ")" );
			dbHandler.execSQL("INSERT INTO appartiene "
					+ " VALUES (1,'"+ genSave.getCreationTime()+"',1,1,1,0)");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		return genSave;
	}
	
	public void deleteSaveData(String saveDate){
		try {
			dbHandler.execSQL("DELETE FROM Possiede WHERE DataCreazione ='" 
					+ saveDate + "'");
			dbHandler.execSQL("DELETE FROM Appartiene WHERE DataCreazione ='" 
					+ saveDate + "'");
			dbHandler.execSQL("DELETE FROM Istanza_Personaggio WHERE DataCreazione ='" 
					+ saveDate + "'");
			dbHandler.execSQL("DELETE FROM SALVATAGGIO_GIOCATORE WHERE DataCreazione ='" 
								+ saveDate + "'");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
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
			dbHandler.execSQL(ResPack.q_NEMICO);
			dbHandler.execSQL(ResPack.q_APPARTIENE);
			dbHandler.execSQL(ResPack.q_CEDE);
			dbHandler.execSQL(ResPack.q_EQUIPAGGIA);
			dbHandler.execSQL(ResPack.q_EQUIPAGGIABILE);
			dbHandler.execSQL(ResPack.q_IMPARA);
			dbHandler.execSQL(ResPack.q_MODIFICAT);
			dbHandler.execSQL(ResPack.q_POSSIEDE);
//			dbHandler.execSQL(ResPack.q_PROMUOVE);
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

	/**
	 * 
	 * @return an ArrayList containing the characters data pertaining the current save
	 */
	public ArrayList<CharacterData> getParty() {
		ArrayList<CharacterData> charList = new ArrayList<CharacterData>();
		DatabaseCursor cursorCharacter = null;
		DatabaseCursor cursorEquipment = null;
		CharacterData tempChar = null;
		
		try {
			cursorCharacter = dbHandler.rawQuery("SELECT DataCreazione, Id_personaggio, Nome, Sprite,"
					+ "IP_HP, IP_MP ,IP_ATK, IP_DEF, IP_INT, IP_AGI, "
					+ "ID_Classe, NomeClasse,"
					+ "InUso, LivelloClasse, EXP "
					+ "FROM ISTANZA_PERSONAGGIO NATURAL JOIN Appartiene NATURAL JOIN CLASSE "
					+ "WHERE DataCreazione = '" + ResPack.currentSave + "'");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		//build  the characters
		while (cursorCharacter.next()) {
			tempChar = new CharacterData
					.CharacterBuilder(cursorCharacter.getInt(1))
			.name(cursorCharacter.getString(2))
			.sprite(cursorCharacter.getString(3))
			//hp and maxhp are set to the hp of the db. Hp are restored when the game is saved! Same for mp and maxmp
			.hpmp(cursorCharacter.getInt(4), cursorCharacter.getInt(5))
			.hpmpmax(cursorCharacter.getInt(4), cursorCharacter.getInt(5))
			.atkdef(cursorCharacter.getInt(6), cursorCharacter.getInt(7))
			.intagi(cursorCharacter.getInt(8), cursorCharacter.getInt(9))
			.activeClass(cursorCharacter.getString(11), cursorCharacter.getInt(13), cursorCharacter.getInt(14))
			.build();
			//build the equipment

			charList.add(tempChar);
		}
		cursorCharacter.close();
		for (int i = 0; i < charList.size(); i++){
			tempChar = charList.remove(i);
			//get all the character's equipment, this is lazy i should get the id and then put the item in the character data
			try {
				cursorEquipment = dbHandler.rawQuery("SELECT oggetto.nome, tipoequip "
						+ "FROM ISTANZA_PERSONAGGIO LEFT JOIN equipaggia ON ISTANZA_PERSONAGGIO.ID_PERSONAGGIO = EQUIPAGGIA.ID_PERSONAGGIO"
						+ " LEFT JOIN oggetto ON EQUIPAGGIA.ID_OGGETTO = OGGETTO.ID_OGGETTO "
						+ "WHERE equipaggia.DataCreazione = '" + ResPack.currentSave + "' "
						+ "AND EQUIPAGGIA.id_personaggio = " + tempChar.getId());
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
			}
			while (cursorEquipment.next()) {
				if(cursorEquipment.getString(1).equals("arma")){
					if(tempChar.getArm1() == null){
						tempChar.setArm1(cursorEquipment.getString(0));
					}
					else {
						tempChar.setArm2(cursorEquipment.getString(0));
					}
				}
				else if (cursorEquipment.getString(1).equals("corpo")){
					tempChar.setBody(cursorEquipment.getString(0));
				}
				else {
					tempChar.setAccessory(cursorEquipment.getString(0));
				}
			}
			if(tempChar.getAccessory()==null){
				tempChar.setAccessory("");
			}
			if(tempChar.getArm1()==null){
				tempChar.setArm1("");
			}
			if(tempChar.getArm2()==null){
				tempChar.setArm2("");
			}
			if(tempChar.getBody()==null){
				tempChar.setBody("");
			}
			cursorEquipment.close();
			charList.add(i, tempChar);
			
		}
		

		return charList;
	}

	/**
	 * 
	 * @return an ArrayList containing the items data pertaining the current save
	 */
	public ArrayList<ItemData> getInventory() {
		ArrayList<ItemData> inventory = new ArrayList<ItemData>();
		DatabaseCursor cursor = null;
		ConsumableItemData tempConsumable;
		EquippableItemData tempEquip;
		KeyItemData tempKey;

		try {
			cursor = dbHandler.rawQuery("SELECT TipoOggetto, TipoEquip, DataCreazione, OGGETTO.Id_Oggetto, "
					+ "Nome, Sprite, Descrizione, POSSIEDE.Quantita,"
					+ "HP, MP ,ATK, DEF, INT, AGI, TECNICA "
					+ "FROM OGGETTO NATURAL JOIN Possiede "
					+ "WHERE DataCreazione = '" + ResPack.currentSave + "'");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		
		while (cursor.next()) {
			if(cursor.getString(0).equals("consumabile")){
				tempConsumable = ResPack.itemType.new ConsumableItemData(cursor.getInt(3),cursor.getString(4),cursor.getString(6));
				tempConsumable.setQuantity(cursor.getInt(7));
				
				if (!String.valueOf(cursor.getInt(14)).equals("NULL")){
					tempConsumable.setItemSkill(ResPack.skills.get(String.valueOf(cursor.getInt(14))));
				}
				
				inventory.add(tempConsumable);
			}
			else if (cursor.getString(0).equals("equip")){
				tempEquip = ResPack.itemType.new EquippableItemData(cursor.getInt(3),cursor.getString(4),cursor.getString(6));
				tempEquip.setQuantity(cursor.getInt(7));
				tempEquip.setI_hp(cursor.getInt(8));
				tempEquip.setI_mp(cursor.getInt(8));
				tempEquip.setI_atk(cursor.getInt(10));
				tempEquip.setI_def(cursor.getInt(11));
				tempEquip.setI_int(cursor.getInt(12));
				tempEquip.setI_agi(cursor.getInt(13));
				inventory.add(tempEquip);
			}
			else if (cursor.getString(0).equals("chiave")){
				tempKey = ResPack.itemType.new KeyItemData(cursor.getInt(3),cursor.getString(4),cursor.getString(6));
				tempKey.setQuantity(cursor.getInt(7));
				inventory.add(tempKey);
			}
	
			Gdx.app.log("DatabaseTest",inventory.get(0).toString());
			Gdx.app.log("DatabaseTest", String.valueOf(cursor.getInt(1)));
		}
		return inventory;
	}
	
	/**
	 * use this method to read all the skills from the db. 
	 * @return an ArrayList containing the skills data 
	 */
	public ArrayList<SkillData> getSkills() {
		ArrayList<SkillData> skills = new ArrayList<SkillData>();
		SkillData tempSkill;
		DatabaseCursor cursor = null ;
		try {
			cursor = dbHandler.rawQuery("SELECT * "
					+ "FROM tecnica"); //redefine toString or something to work with database
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		while (cursor.next()) {
			
				tempSkill = new SkillData
							.SkillBuilder(cursor.getInt(0))
							.name(cursor.getString(1))
							.description(cursor.getString(2))
							.cost(cursor.getInt(3))
							.damage(cursor.getInt(4))
							.build();
				skills.add(tempSkill);
		}
		return skills;
	}
	/**
	 * use this method to get all the skill the given character can use.
	 * @param charId
	 * @return a list with the ids of the skills
	 */
	public ArrayList<String> getCharSkills(String charId){
		ArrayList<String> charSkills = new ArrayList<String>();
		DatabaseCursor cursor = null ;
		try {
			cursor = dbHandler.rawQuery("SELECT * "
					+ "FROM tecnica NATURAL JOIN impara NATURAL JOIN appartiene "
					+ "WHERE appartiene.id_Personaggio = " + charId + " AND "
					+ " appartiene.DataCreazione = '" + ResPack.currentSave + "' AND "
					+ " appartiene.LivelloClasse > impara.LivelloRichiesto "
					+ " AND appartiene.inUso = 1");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		while (cursor.next()) {
				charSkills.add(String.valueOf(cursor.getInt(0)));
				Gdx.app.log("DatabaseTest",charSkills.get(charSkills.size()-1).toString());
				Gdx.app.log("DatabaseTest", String.valueOf(cursor.getInt(0)));
		}
		return charSkills;
	}
	
	/**Fill the data structure containing save information, such as characters,items owned and item's owned skills.
	 * 
	 */
	public void loadSavedData(){
		
		ResPack.skills = new HashMap<String, SkillData>();
		ArrayList<SkillData> skills = getSkills();
		for (SkillData skill : skills) {
			ResPack.skills.put(String.valueOf(skill.getId()), skill);
		}
		ArrayList<ItemData> inventory = getInventory();
		for (ItemData item : inventory) {
			ResPack.inventory.put(item);
		}
		ResPack.party = new HashMap<String, CharacterData>();
		ArrayList<CharacterData> party = getParty();
		for (CharacterData character : party) {
			ResPack.party.put(String.valueOf(character.getId()), character);
		}
		
	}
	
public void storeSavedData(){
		
		HashMap<String, ItemData> oldInventory = new HashMap<String, ItemData>();
		ArrayList<ItemData> inventory = getInventory();
		for (ItemData item : inventory) {
			oldInventory.put(String.valueOf(item.getId()), item);
		}
		
		for (String itemId : ResPack.inventory.getItemIds()){
			if(ResPack.inventory.getQuantity(itemId) != oldInventory.get(itemId).getQuantity()){
					try {
						
						if(ResPack.inventory.getQuantity(itemId) == 0) {
							//the item will be deleted from the db
						dbHandler.execSQL("DELETE FROM possiede"
								+ " WHERE id_oggetto =" + itemId 
								+ " AND DataCreazione = '" + ResPack.currentSave + "'");
						}
						else if (oldInventory.get(itemId).getQuantity() == 0){
							//the item will be inserted into the possiede table
							dbHandler.execSQL("INSERT INTO possiede "
									+ " VALUES ('" 
									+ ResPack.currentSave + "',"
									+ ResPack.inventory.get(itemId) + ","
									+ ResPack.inventory.getQuantity(itemId)
									+ ")");
						}
						else {
							//the item quantity will be updated
							dbHandler.execSQL("UPDATE possiede "
									+ " SET quantita = " + ResPack.inventory.getQuantity(itemId) 
									+ " WHERE datacreazione = '" + ResPack.currentSave + "'"
									+ " AND id_oggetto = " + itemId
									);
						}
					} catch (SQLiteGdxException e) {
						e.printStackTrace();
						return;
					}
			}
		}
		
		HashMap<String, CharacterData> oldParty = new HashMap<String, CharacterData>();
		ArrayList<CharacterData> party = getParty();
		for (CharacterData character : party) {
			oldParty.put(String.valueOf(character.getId()), character);
		}
		//id_personaggio	datacreazione	nome	sprite	ip_hp	ip_mp	ip_atk	ip_def	ip_int	ip_agi
		CharacterData currentChar = null;
		for (String charId : ResPack.party.keySet()){
			currentChar = ResPack.party.get(charId);
			try{ 
				dbHandler.execSQL("UPDATE istanza_personaggio "
						+ " SET nome = '" + currentChar.getName() + "',"
						+ "  sprite = '" + currentChar.getSprite() + "',"
						+ "  ip_hp = " + currentChar.getC_MaxHp() + ","
						+ "  ip_mp = " + currentChar.getC_MaxMp() + ","
						+ "  ip_atk = " + currentChar.getC_atk() + ","
						+ "  ip_def = " + currentChar.getC_def() + ","
						+ "  ip_int = " + currentChar.getC_int() + ","
						+ "  ip_agi = " + currentChar.getC_agi() 
						+ " WHERE id_personaggio = " + charId
						+ " AND datacreazione ='" + ResPack.currentSave + "'"
						);
				
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}
		}
		//loadSavedData();
		
		
	}
	
	/**
	 * given the tables data in txt format, I fill the db with that data.
	 */
	public void fillDB(){
		String data ;
		int i,j,p,col;
		String[] rowData = new String[20];
		//Insert characters
		data = generateString.txtToString("tables/personaggio.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 9;
		while(i<data.length()){

			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				//dbHandler.execSQL("DELETE FROM PERSONAGGIO WHERE id_personaggio = " + rowData[0] );
								dbHandler.execSQL("INSERT INTO PERSONAGGIO "
										+ " VALUES (" 
										+ rowData[0] + ",'"
										+ rowData[1] + "','"
										+ rowData[2] + "',"
										+ rowData[3] + ","
										+ rowData[4] + ","
										+ rowData[5] + ","
										+ rowData[6] + ","
										+ rowData[7] + ","
										+ rowData[8] + ")");
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}

		//Insert saves
		data = generateString.txtToString("tables/salvataggio_giocatore.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 4;
		while(i<data.length()){

			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				//dbHandler.execSQL("DELETE FROM SALVATAGGIO_GIOCATORE WHERE data_creazione = '" + rowData[0] +"'");
				dbHandler.execSQL("INSERT INTO SALVATAGGIO_GIOCATORE "
						+ " VALUES ('" 
						+ rowData[0] + "','"
						+ rowData[1] + "',"
						+ rowData[2] + ",'"
						+ rowData[3] +  "')");
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert char_instance
		data = generateString.txtToString("tables/istanza_personaggio.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 10;
		while(i<data.length()){

			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO ISTANZA_PERSONAGGIO "
						+ " VALUES (" 
						+ rowData[0] + ",'"
						+ rowData[1] + "','"
						+ rowData[2] + "','"
						+ rowData[3] + "',"
						+ rowData[4] + ","
						+ rowData[5] + ","
						+ rowData[6] + ","
						+ rowData[7] + ","
						+ rowData[8] + ","
						+ rowData[9] + ")");
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert classes
		data = generateString.txtToString("tables/classe.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 9;
		while(i<data.length()){

			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				//dbHandler.execSQL("DELETE FROM CLASSE WHERE id_classe = " + rowData[0] );
				dbHandler.execSQL("INSERT INTO CLASSE "
						+ " VALUES (" 
						+ rowData[0] + ",'"
						+ rowData[1] + "',"
						+ rowData[2] + ","
						+ rowData[3] + ","
						+ rowData[4] + ","
						+ rowData[5] + ","
						+ rowData[6] + ","
						+ rowData[7] + ","
						+ rowData[8] + ")");
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert class_membership
		data = generateString.txtToString("tables/appartiene.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 6;
		while(i<data.length()){

			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO appartiene "
						+ " VALUES (" 
						+ rowData[0] + ",'"
						+ rowData[1] + "',"
						+ rowData[2] + ","
						+ rowData[3] + ","
						+ rowData[4] + ","
						+ rowData[5] + ")");
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert class promotion
//		data = generateString.txtToString("tables/promuove.txt");
//		i = 0;
//		j = 0;
//		p = 0;
//		col = 3;
//		while(i<data.length()){
//
//			while(p<col){
//				j = data.indexOf(9,i);
//				if(p == col-1){
//					j = data.indexOf(10, i);
//				}
//				rowData[p++] = data.substring(i, j);
//				i = j + 1;	
//			}
//			p = 0;
//
//			try {
//				dbHandler.execSQL("INSERT INTO promuove "
//						+ " VALUES (" 
//						+ rowData[0] + ","
//						+ rowData[1] + ","
//						+ rowData[2] + ")");
//			} catch (SQLiteGdxException e) {
//				e.printStackTrace();
//				return;
//			}
//		}

		//Insert skills
		data = generateString.txtToString("tables/tecnica.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 5;
		while(i<data.length()){

			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				//dbHandler.execSQL("DELETE FROM tecnica WHERE id_tecnica = " + rowData[0] );
				dbHandler.execSQL("INSERT INTO tecnica "
						+ " VALUES (" 
						+ rowData[0] + ",'"
						+ rowData[1] + "','"
						+ rowData[2] + "',"
						+ rowData[3] + ","
						+ rowData[4] + ")");
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert skills teachings
		data = generateString.txtToString("tables/impara.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 3;
		while(i<data.length()){

			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				
				dbHandler.execSQL("INSERT INTO impara "
						+ " VALUES (" 
						+ rowData[0] + ","
						+ rowData[1] + ","
						+ rowData[2] + ")");
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert items
		data = generateString.txtToString("tables/oggetto.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 13;
		while(i<data.length()){

			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				//dbHandler.execSQL("DELETE FROM OGGETTO WHERE id_oggetto = " + rowData[0] );
				//if equip_type is null i must delete the '' on NULL in the query
				if(rowData[5].equals("NULL")) {
				dbHandler.execSQL("INSERT INTO oggetto "
						+ " VALUES (" 
						+ rowData[0] + ",'"
						+ rowData[1] + "','"
						+ rowData[2] + "','"
						+ rowData[3] + "','"
						+ rowData[4] + "',"
						+ rowData[5] + ","
						+ rowData[6] + ","
						+ rowData[7] + ","
						+ rowData[8] + ","
						+ rowData[9] + ","
						+ rowData[10] + ","
						+ rowData[11] + ","
						+ rowData[12] + ")");
				}
				else {
					dbHandler.execSQL("INSERT INTO oggetto "
							+ " VALUES (" 
							+ rowData[0] + ",'"
							+ rowData[1] + "','"
							+ rowData[2] + "','"
							+ rowData[3] + "','"
							+ rowData[4] + "','"
							+ rowData[5] + "',"
							+ rowData[6] + ","
							+ rowData[7] + ","
							+ rowData[8] + ","
							+ rowData[9] + ","
							+ rowData[10] + ","
							+ rowData[11] + ","
							+ rowData[12] + ")");
				}
			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}

		//Insert able to equip
		data = generateString.txtToString("tables/equipaggiabile.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 2;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO equipaggiabile "
						+ " VALUES (" 
						+ rowData[0] + ","
						+ rowData[1] + ")");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert  equips
		data = generateString.txtToString("tables/equipaggia.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 3;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO equipaggia "
						+ " VALUES (" 
						+ rowData[0] + ",'"
						+ rowData[1] + "',"
						+ rowData[2] + ")");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert  owns
		data = generateString.txtToString("tables/possiede.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 3;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO possiede "
						+ " VALUES ('" 
						+ rowData[0] + "',"
						+ rowData[1] + ","
						+ rowData[2] + ")");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert  requires
		data = generateString.txtToString("tables/richiede.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 2;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO richiede "
						+ " VALUES (" 
						+ rowData[0] + ","
						+ rowData[1] + ")");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert  status
		data = generateString.txtToString("tables/stato.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 3;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO stato "
						+ " VALUES ('" 
						+ rowData[0] + "','"
						+ rowData[1] + "','"
						+ rowData[2] + "')");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert  modifies_t
		data = generateString.txtToString("tables/modifica_t.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 3;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO modifica_t "
						+ " VALUES (" 
						+ rowData[0] + ",'"
						+ rowData[1] + "',"
						+ rowData[2] + ")");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		//Insert  enemy
		data = generateString.txtToString("tables/nemico.txt");
		i = 0;
		j = 0;
		p = 0;
		col =10;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO nemico "
						+ " VALUES ('" 
						+ rowData[0] + "','"
						+ rowData[1] + "','"
						+ rowData[2] + "','"
						+ rowData[3] + "',"
						+ rowData[4] + ","
						+ rowData[5] + ","
						+ rowData[6] + ","
						+ rowData[7] + ","
						+ rowData[8] + ","
						+ rowData[9] + ")");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}

		//Insert  uses
		data = generateString.txtToString("tables/utilizza.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 2;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO utilizza "
						+ " VALUES ('" 
						+ rowData[0] + "',"
						+ rowData[1] +  ")");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}
		
		
		
		//Insert  leaves
		data = generateString.txtToString("tables/cede.txt");
		i = 0;
		j = 0;
		p = 0;
		col = 3;
		while(i<data.length()){
			while(p<col){
				j = data.indexOf(9,i);
				if(p == col-1){
					j = data.indexOf(10, i);
				}
				rowData[p++] = data.substring(i, j);
				i = j + 1;	
			}
			p = 0;

			try {
				dbHandler.execSQL("INSERT INTO cede "
						+ " VALUES ('" 
						+ rowData[0] + "',"
						+ rowData[1] + ","
						+ rowData[2] + ")");

			} catch (SQLiteGdxException e) {
				e.printStackTrace();
				return;
			}

		}



	}


}
