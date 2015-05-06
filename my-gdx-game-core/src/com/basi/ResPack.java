package com.basi;

import java.util.Date;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** Class to load all assets for the game */
public class ResPack {
	//GameState
	public static String currentSave;
	public static HashMap<String, SkillData> skills;
	public static Inventory inventory = new Inventory();
	public static HashMap<String, CharacterData> party;
	public static Item itemType = new Item();

	
	
	//UI
	public static final Skin _SKIN = new Skin(Gdx.files.internal("skin/uiskin.json"));
	
	//Strings (in Italian)
	public static final String EDIT = "Cancella";
	public static final String MENU = "Menu";
	public static final String STATUS = "Status";
	public static final String EQUIP = "Equip";
	public static final String SKILLS = "Tecniche";
	public static final String INVENTORY = "Inventario";
	public static final String PARTY = "Party";
	public static final String NAME = "Nome";
	public static final String LEVEL = "Lv";
	public static final String HP = "Pv";
	public static final String MP = "Pm";
	public static final String ATK = "Att";
	public static final String DEF = "Dif";
	public static final String INT = "Int";
	public static final String AGI = "Agi";
	public static final String CLASS_NAME = "Classe";
	public static final String CLASS_EXP = "Esp";
	public static final String EQUIPMENT = "Equipaggiamento";
	public static final String ARM1 = "Arma primaria";
	public static final String ARM2 = "Arma secondaria";
	public static final String BODY = "Corpo";
	public static final String ACCESSORY = "Accessorio";
	public static final String CONSUMABLE = "Consumabile";
	public static final String KEY = "Chiave";
	public static final String INFO = "Info";
	public static final String DESCRIPTION = "Descrizione";
	public static final String QUANTITY = "Q.ta";
	public static final String THROW = "butta";
	public static final String SELECTITEM = "Seleziona un oggetto";
	public static final String OK = "OK";
	public static final String CANCEL = "Annulla";
	public static final String SELECTSKILL = "Seleziona una tecnica";
	
	
	
	//Graphic Resources

	/**
	 * 
	 * @param pixmapColor something like Color.desired color
	 * @return a monochrome TextureRegionDrawable
	 */
	public static TextureRegionDrawable createMonocromeDrawable(Color pixmapColor) {
		//create a pixmap to color the title row
		Pixmap pm1 = new Pixmap(1, 1, Format.RGB565);
		pm1.setColor(pixmapColor);
		pm1.fill();
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pm1)));
		return drawable;
		
	}
	public static final String MENU_FONT = "skin/jupiterc.ttf";
	
	public static Skin skinMenuFont(String fontPath, int fontSize) {
		Skin uiSkin;
		Skin uiSkinReduced;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = fontSize;
		BitmapFont myFont = generator.generateFont(parameter); // font size 12 pixels
		parameter.size = 12;
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		uiSkin = new Skin();
		uiSkin.add("myFont",myFont);
		uiSkin.addRegions(new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));
		uiSkin.load(Gdx.files.internal("skin/uiskinMenu.json"));
		return uiSkin;
	}
	//Database	
	public static final DBManager db = new DBManager();
	
	//Tables
	//I could make tables with a map maybe for future optimization
	public static final String t_PERSONAGGIO = "PERSONAGGIO";
	public static final String q_PERSONAGGIO = "CREATE TABLE PERSONAGGIO(\r\n" + 
			"    ID_Personaggio INTEGER PRIMARY KEY NOT NULL,\r\n" + 
			"    Nome VARCHAR(30) NOT NULL,\r\n" + 
			"    Sprite VARCHAR NOT NULL,\r\n" + 
			"    P_HP INTEGER NOT NULL,\r\n" + 
			"    P_MP INTEGER NOT NULL,\r\n" + 
			"    P_ATK INTEGER NOT NULL,\r\n" + 
			"    P_DEF INTEGER NOT NULL,\r\n" + 
			"    P_INT INTEGER NOT NULL,\r\n" + 
			"    P_AGI INTEGER NOT NULL,\r\n" + 
			"    \r\n" + 
			"    CHECK(\r\n" + 
			"        ID_Personaggio >= 0\r\n" + 
			"        AND P_HP >= 0\r\n" + 
			"        AND P_MP >= 0\r\n" + 
			"        AND p_ATK >= 0\r\n" + 
			"        AND P_DEF >= 0\r\n" + 
			"        AND P_INT >= 0\r\n" + 
			"        AND P_AGI >= 0\r\n" + 
			"    )\r\n" + 
			");\r\n" + 
			"" ;
	public static final String t_SALVATAGGIO = "SALVATAGGIO_GIOCATORE";
	public static final String q_SALVATAGGIO = "CREATE TABLE SALVATAGGIO_GIOCATORE(\r\n" + 
			"    DataCreazione TIMESTAMP PRIMARY KEY NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n" + 
			"    Nome VARCHAR(20) NOT NULL,\r\n" + 
			"    TempoGiocato BIGINT NOT NULL DEFAULT 0,\r\n" + 
			"    DataUltimoSalvataggio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP\r\n" + 
			");\r\n" + 
			"" ;
	public static final String t_ISTANZA_PERSONAGGIO = "ISTANZA_PERSONAGGIO";
	public static final String q_ISTANZA_PERSONAGGIO = "CREATE TABLE ISTANZA_PERSONAGGIO(\r\n" + 
			"    ID_Personaggio INTEGER NOT NULL,\r\n" + 
			"    DataCreazione TIMESTAMP NOT NULL,\r\n" + 
			"    Nome VARCHAR(30) NOT NULL,\r\n" + 
			"    Sprite VARCHAR NOT NULL,\r\n" + 
			"    IP_HP INTEGER NOT NULL,\r\n" + 
			"    IP_MP INTEGER NOT NULL,\r\n" + 
			"    IP_ATK INTEGER NOT NULL,\r\n" + 
			"    IP_DEF INTEGER NOT NULL,\r\n" + 
			"    IP_INT INTEGER NOT NULL,\r\n" + 
			"    IP_AGI INTEGER NOT NULL,\r\n" + 
			"    CHECK(\r\n" + 
			"        ID_Personaggio >= 0\r\n" + 
			"        AND IP_HP >= 0\r\n" + 
			"        AND IP_MP >= 0\r\n" + 
			"        AND IP_ATK >= 0\r\n" + 
			"        AND IP_DEF >= 0\r\n" + 
			"        AND IP_INT >= 0\r\n" + 
			"        AND IP_AGI >= 0\r\n" + 
			"    ),\r\n" + 
			"        \r\n" + 
			"    PRIMARY KEY(ID_Personaggio, DataCreazione),\r\n" + 
			"    FOREIGN KEY(ID_Personaggio) REFERENCES PERSONAGGIO(ID_Personaggio)\r\n" + 
			"                ON UPDATE CASCADE ON DELETE RESTRICT,\r\n" + 
			"    FOREIGN KEY(DataCreazione) REFERENCES SALVATAGGIO_GIOCATORE(DataCreazione)\r\n" + 
			"                ON UPDATE CASCADE ON DELETE RESTRICT\r\n" + 
			");\r\n";
	public static final String t_CLASSE = "CLASSE";
	public static final String q_CLASSE = "CREATE TABLE CLASSE(\r\n" + 
			"    ID_Classe INTEGER PRIMARY KEY NOT NULL,\r\n" + 
			"    Nome VARCHAR(20) NOT NULL,\r\n" + 
			"    LivelloMax INTEGER NOT NULL,\r\n" + 
			"    C_HP INTEGER NOT NULL,\r\n" + 
			"    C_MP INTEGER NOT NULL,\r\n" + 
			"    C_ATK INTEGER NOT NULL,\r\n" + 
			"    C_DEF INTEGER NOT NULL,\r\n" + 
			"    C_INT INTEGER NOT NULL,\r\n" + 
			"    C_AGI INTEGER NOT NULL,\r\n" + 
			"    \r\n" + 
			"    CHECK(\r\n" + 
			"        ID_Classe >= 0\r\n" + 
			"        AND C_HP >= 0\r\n" + 
			"        AND C_MP >= 0\r\n" + 
			"        AND C_ATK >= 0\r\n" + 
			"        AND C_DEF >= 0\r\n" + 
			"        AND C_INT >= 0\r\n" + 
			"        AND C_AGI >= 0\r\n" + 
			"    )\r\n" + 
			");\r\n" + 
			"";
	public static final String t_APPARTIENE = "Appartiene";
	public static final String q_APPARTIENE = "CREATE TABLE Appartiene(\r\n" + 
			"    ID_Personaggio INTEGER NOT NULL,\r\n" + 
			"    DataCreazione TIMESTAMP NOT NULL,\r\n" + 
			"    ID_Classe INTEGER NOT NULL,\r\n" + 
			"    InUso BOOLEAN NOT NULL,\r\n" + 
			"    LivelloClasse INTEGER NOT NULL DEFAULT 1,\r\n" + 
			"    EXP INTEGER NOT NULL DEFAULT 0,\r\n" + 
			"    \r\n" + 
			"    PRIMARY KEY (ID_Personaggio, DataCreazione, ID_Classe),\r\n" + 
			"    FOREIGN KEY(ID_Personaggio, DataCreazione)\r\n" + 
			"        REFERENCES ISTANZA_PERSONAGGIO(ID_Personaggio, DataCreazione)\r\n" + 
			"        ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    FOREIGN KEY(ID_Classe) REFERENCES CLASSE(ID_Classe)\r\n" + 
			"        ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Personaggio >= 0 AND ID_Classe >= 0)\r\n" + 
			");\r\n" + 
			"\r\n" + 
			"";
//	public static final String t_PROMUOVE = "Promuove";
//	public static final String q_PROMUOVE = "CREATE TABLE   if not exists Promuove(\r\n" + 
//			"ID_Classe INTEGER NOT NULL,\r\n" + 
//			"PromossoDa INTEGER,\r\n" + 
//			"PromuoveIn INTEGER,\r\n" + 
//			"PRIMARY KEY(ID_Classe),\r\n" + 
//			"FOREIGN KEY(ID_Classe) REFERENCES CLASSE(ID_Classe)\r\n" + 
//			"	ON UPDATE CASCADE ON DELETE RESTRICT\r\n" + 
//			");\r\n";
	public static final String t_TECNICA = "TECNICA";
	public static final String q_TECNICA = "CREATE TABLE TECNICA(\r\n" + 
			"    ID_Tecnica INTEGER PRIMARY KEY NOT NULL,\r\n" + 
			"    Nome VARCHAR(30) NOT NULL,\r\n" + 
			"    Descrizione VARCHAR(200) NOT NULL,\r\n" + 
			"    Costo INTEGER NOT NULL DEFAULT 0,\r\n" + 
			"    Danno INTEGER NOT NULL DEFAULT 0,\r\n" + 
			"    \r\n" + 
			"    CHECK(ID_Tecnica >= 0)\r\n" + 
			");\r\n" + 
			"";
	public static final String t_IMPARA = "IMPARA";
	public static final String q_IMPARA = "CREATE TABLE Impara(\r\n" + 
			"    ID_Classe INTEGER NOT NULL,\r\n" + 
			"    ID_Tecnica INTEGER NOT NULL,\r\n" + 
			"    LivelloRichiesto INTEGER NOT NULL DEFAULT 0,\r\n" + 
			"    \r\n" + 
			"    PRIMARY KEY(ID_Classe, ID_Tecnica), \r\n" + 
			"    FOREIGN KEY(ID_Classe) REFERENCES CLASSE(ID_Classe)\r\n" + 
			"        ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    FOREIGN KEY(ID_Tecnica) REFERENCES TECNICA(ID_Tecnica)\r\n" + 
			"        ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Classe >= 0 AND ID_Tecnica >= 0)\r\n" + 
			");\r\n" + 
			"";

	public static final String t_OGGETTO = "OGGETTO";
	public static final String q_OGGETTO = "CREATE TABLE OGGETTO(\r\n" + 
			"    ID_Oggetto INTEGER PRIMARY KEY NOT NULL,\r\n" + 
			"    Nome VARCHAR(20) NOT NULL,\r\n" + 
			"    Sprite VARCHAR NOT NULL,\r\n" + 
			"    Descrizione VARCHAR(200) NOT NULL,\r\n" + 
			"    TipoOggetto VARCHAR(20) NOT NULL,\r\n" + 
			"    TipoEquip VARCHAR(20),\r\n" + 
			"    Tecnica INTEGER,\r\n" + 
			"    HP INTEGER,\r\n" + 
			"    MP INTEGER,\r\n" + 
			"    ATK INTEGER,\r\n" + 
			"    DEF INTEGER,\r\n" + 
			"    AGI INTEGER,\r\n" + 
			"    INT INTEGER,\r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"    FOREIGN KEY(Tecnica) REFERENCES TECNICA(ID_Tecnica)\r\n" + 
			"        ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Oggetto >= 0)\r\n" + 
			");\r\n" + 
			"";
	
	public static final String t_EQUIPAGGIABILE = "Equipaggiabile";
	public static final String q_EQUIPAGGIABILE = "CREATE TABLE Equipaggiabile(\r\n" + 
			"    ID_Classe INTEGER NOT NULL,\r\n" + 
			"    ID_Oggetto INTEGER NOT NULL,\r\n" + 
			"    \r\n" + 
			"    PRIMARY KEY(ID_Classe, ID_Oggetto), \r\n" + 
			"    FOREIGN KEY(ID_Classe) REFERENCES CLASSE(ID_Classe)\r\n" + 
			"        ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    FOREIGN KEY(ID_Oggetto) REFERENCES Oggetto(ID_Oggetto)\r\n" + 
			"        ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Classe >= 0 AND ID_Oggetto >= 0)\r\n" + 
			");\r\n" + 
			"";
	public static final String t_EQUIPAGGIA = "Equipaggia";
	public static final String q_EQUIPAGGIA = "CREATE TABLE Equipaggia(\r\n" + 
			"    ID_Personaggio INTEGER NOT NULL,\r\n" + 
			"    DataCreazione TIMESTAMP NOT NULL,\r\n" + 
			"    ID_Oggetto INTEGER NOT NULL,\r\n" + 
			"    \r\n" + 
			"    PRIMARY KEY(ID_Personaggio, DataCreazione, ID_Oggetto),\r\n" + 
			"    FOREIGN KEY(ID_Personaggio, DataCreazione)\r\n" + 
			"        REFERENCES ISTANZA_PERSONAGGIO(ID_Personaggio, DataCreazione)\r\n" + 
			"            ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    FOREIGN KEY(ID_Oggetto) REFERENCES OGGETTO(ID_Oggetto)\r\n" + 
			"            ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Personaggio >= 0 AND ID_Oggetto >= 0)\r\n" + 
			");\r\n" + 
			"";
	public static final String t_POSSIEDE = "Possiede";
	public static final String q_POSSIEDE = "CREATE TABLE Possiede(\r\n" + 
			"    DataCreazione TIMESTAMP NOT NULL,\r\n" + 
			"    ID_Oggetto INTEGER NOT NULL,\r\n" + 
			"    Quantita INTEGER NOT NULL DEFAULT 0,\r\n" + 
			"    \r\n" + 
			"    PRIMARY KEY(DataCreazione, ID_Oggetto),\r\n" + 
			"    FOREIGN KEY(DataCreazione) REFERENCES SALVATAGGIO_GIOCATORE(DataCreazione)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    FOREIGN KEY(ID_Oggetto) REFERENCES OGGETTO(ID_Oggetto)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Oggetto >= 0 AND Quantita > 0)\r\n" + 
			");\r\n" + 
			"";
	public static final String t_RICHIEDE = "Richiede";
	public static final String q_RICHIEDE = "CREATE TABLE Richiede(\r\n" + 
			"    ID_Tecnica INTEGER NOT NULL,\r\n" + 
			"    ID_Oggetto INTEGER NOT NULL,\r\n" + 
			"    PRIMARY KEY(ID_Tecnica, ID_Oggetto),\r\n" + 
			"    FOREIGN KEY(ID_Tecnica) REFERENCES TECNICA(ID_Tecnica)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    FOREIGN KEY(ID_Oggetto) REFERENCES OGGETTO(ID_Oggetto)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Tecnica >= 0 AND ID_Oggetto >= 0)\r\n" + 
			");\r\n" + 
			"";
	public static final String t_STATO = "STATO";
	public static final String q_STATO = "CREATE TABLE STATO(\r\n" + 
			"    Sigla CHAR(3) PRIMARY KEY NOT NULL,\r\n" + 
			"    Nome VARCHAR(20) NOT NULL,\r\n" + 
			"    Descrizione VARCHAR NOT NULL\r\n" + 
			");\r\n" + 
			"";
	public static final String t_MODIFICAT = "Modifica_T";
	public static final String q_MODIFICAT = "CREATE TABLE Modifica_T(\r\n" + 
			"    ID_Tecnica INTEGER NOT NULL,\r\n" + 
			"    Sigla CHAR(3) NOT NULL,\r\n" + 
			"    PercentualeSuccesso NUMERIC(5,2) NOT NULL,\r\n" + 
			"    \r\n" + 
			"    PRIMARY KEY(ID_Tecnica, Sigla),\r\n" + 
			"    FOREIGN KEY(ID_Tecnica) REFERENCES TECNICA(ID_Tecnica)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"\r\n" + 
			"    FOREIGN KEY(Sigla) REFERENCES STATO(Sigla)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL\r\n" + 
			");\r\n" + 
			"";
	public static final String t_NEMICO = "NEMICO";
	public static final String q_NEMICO ="CREATE TABLE NEMICO(\r\n" + 
			"    Nome VARCHAR(30) PRIMARY KEY NOT NULL,\r\n" + 
			"    Famiglia VARCHAR(30) NOT NULL,\r\n" + 
			"    Descrizione VARCHAR(200) NOT NULL,\r\n" + 
			"    Sprite VARCHAR NOT NULL,\r\n" + 
			"    N_HP INTEGER NOT NULL,\r\n" + 
			"    N_MP INTEGER NOT NULL,\r\n" + 
			"    N_ATK INTEGER NOT NULL,\r\n" + 
			"    N_DEF INTEGER NOT NULL,\r\n" + 
			"    N_INT INTEGER NOT NULL,\r\n" + 
			"    N_AGI INTEGER NOT NULL,\r\n" + 
			"    \r\n" + 
			"    CHECK( N_HP >= 0\r\n" + 
			"    AND N_MP >= 0\r\n" + 
			"    AND N_ATK >= 0\r\n" + 
			"    AND N_DEF >= 0\r\n" + 
			"    AND N_INT >= 0\r\n" + 
			"    AND N_AGI >= 0)\r\n" + 
			");\r\n" + 
			"";
	public static final String t_UTILIZZA = "Utilizza";
	public static final String q_UTILIZZA = "CREATE TABLE Utilizza(\r\n" + 
			"    Nome VARCHAR(30) NOT NULL,\r\n" + 
			"    ID_Tecnica INTEGER NOT NULL,\r\n" + 
			"    \r\n" + 
			"    PRIMARY KEY(Nome, ID_Tecnica),\r\n" + 
			"    FOREIGN KEY(Nome) REFERENCES NEMICO(Nome)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    FOREIGN KEY(ID_Tecnica) REFERENCES TECNICA(ID_Tecnica)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Tecnica >= 0)\r\n" + 
			");\r\n" + 
			"";
	
	public static final String t_CEDE = "Cede";
	public static final String q_CEDE = "\r\n" + 
			"CREATE TABLE Cede(\r\n" + 
			"    Nome VARCHAR(30) NOT NULL,\r\n" + 
			"    ID_Oggetto INTEGER NOT NULL,\r\n" + 
			"    Quantita INTEGER NOT NULL,\r\n" + 
			"    \r\n" + 
			"    PRIMARY KEY(Nome, ID_Oggetto),\r\n" + 
			"    FOREIGN KEY(Nome) REFERENCES Nemico(Nome)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    FOREIGN KEY(ID_Oggetto) REFERENCES OGGETTO(ID_Oggetto)\r\n" + 
			"                    ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
			"    CHECK(ID_Oggetto >= 0)\r\n" + 
			");\r\n" + 
			"";
	
	public static final String DBCREATE = q_SALVATAGGIO ;
	
	

	
	

	



	

	
}	