package com.basi;

import java.util.Date;
/**
 * This class will be used to hold the data of the character in use.
 * hp and mp are note directly saved in the db, they are only stored
 * on memory because they are recovered when you save the game on the 
 * save point.
 * 
 * MAXHP and MAXMP is calculated with class.hp,mp and character.hp,mp.
 * For example MAXHP = PERSONAGGIO.HP * INUSO.LIVELLOCLASSE * 2 + CLASSE.HP * INUSO.LIVELLOCLASSE * 5 + TOTALE BONUS EQUIP
 * And so on for atk,def,int,agi. H
 * @author Andrea
 *
 */
public class CharacterData {

	// non me ne frega un cazzo della data, la tratto come stringa per
	// semplicitÓ e fanculo sql e java
	//private Date dataCreazione;
	private int id;

	private String name;
	private String sprite;
	// It would be better to have a activeClass class
	private String activeClass;
	private int classLevel;
	private int exp;
	// It would be better to have a Stats class
	private int c_MaxHp;
	private int c_MaxMp;
	private int c_hp;
	private int c_mp;
	private int c_atk;
	private int c_def;
	private int c_agi;
	private int c_int;
	//equipment, temporary in strings
	private String arm1;
	private String arm2;
	private String body;
	private String accessory;

	
	public CharacterData(CharacterBuilder builder) {
		
		this.id = builder.id_personaggio;
		this.name = builder.name;
		this.sprite = builder.sprite;
		this.activeClass = builder.activeClass;
		this.classLevel = builder.classLevel;
		this.exp = builder.exp;
		this.setC_MaxHp(builder.c_MaxHp);
		this.setC_MaxMp(builder.c_MaxMp);
		this.c_hp = builder.c_hp;
		this.c_mp = builder.c_mp;
		this.c_atk = builder.c_atk;
		this.c_def = builder.c_def;
		this.c_agi = builder.c_agi;
		this.c_int = builder.c_int;
		this.arm1 = builder.arm1;
		this.arm2 = builder.arm2;
		this.body = builder.body;
		this.accessory = builder.accessory;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSprite() {
		return sprite;
	}
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}
	public String getActiveClass() {
		return activeClass;
	}
	public void setActiveClass(String activeClass) {
		this.activeClass = activeClass;
	}
	public int getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(int classLevel) {
		this.classLevel = classLevel;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getC_hp() {
		return c_hp;
	}
	public void setC_hp(int c_hp) {
		this.c_hp = c_hp;
	}
	public int getC_mp() {
		return c_mp;
	}
	public void setC_mp(int c_mp) {
		this.c_mp = c_mp;
	}
	public int getC_atk() {
		return c_atk;
	}
	public void setC_atk(int c_atk) {
		this.c_atk = c_atk;
	}
	public int getC_def() {
		return c_def;
	}
	public void setC_def(int c_def) {
		this.c_def = c_def;
	}
	public int getC_agi() {
		return c_agi;
	}
	public void setC_agi(int c_agi) {
		this.c_agi = c_agi;
	}
	public int getC_int() {
		return c_int;
	}
	public void setC_int(int c_int) {
		this.c_int = c_int;
	}
	@Override
	public String toString() {
		return this.getName();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id_personaggio) {
		this.id = id_personaggio;
	}

	public String getArm1() {
		return arm1;
	}

	public void setArm1(String arm1) {
		this.arm1 = arm1;
	}

	public String getArm2() {
		return arm2;
	}

	public void setArm2(String arm2) {
		this.arm2 = arm2;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	
	public int getC_MaxHp() {
		return c_MaxHp;
	}

	public void setC_MaxHp(int c_MaxHp) {
		this.c_MaxHp = c_MaxHp;
	}

	public int getC_MaxMp() {
		return c_MaxMp;
	}

	public void setC_MaxMp(int c_MaxMp) {
		this.c_MaxMp = c_MaxMp;
	}

	public static class CharacterBuilder {
		
		
		private int id_personaggio;

		private String name;
		private String sprite;
		//It would be better to have a activeClass class
		private String activeClass;
		private int classLevel;
		private int exp;
		// It would be better to have a Stats class
		private int c_MaxHp;
		private int c_MaxMp;
		private int c_hp;
		private int c_mp;
		private int c_atk;
		private int c_def;
		private int c_agi;
		private int c_int;
		private String arm1;
		private String arm2;
		private String body;
		private String accessory;

		    public CharacterBuilder( int id_personaggio) {
		      
		      this.id_personaggio = id_personaggio;
		    }
		    
		    public CharacterBuilder name(String name){
		    	this.name = name;
		    	return this;
		    }
		    
		    public CharacterBuilder sprite(String sprite) {
			      this.sprite = sprite;
			      return this;
			    }

		    public CharacterBuilder activeClass(String activeClass, int classLevel, int exp) {
		      this.activeClass = activeClass;
		      this.classLevel = classLevel;
		      this.exp = exp;
		      return this;
		    }

		    public CharacterBuilder hpmp(int c_hp, int c_mp) {
		      this.c_hp = c_hp;
		      this.c_mp = c_mp;
		      return this;
		    }
		    
		    public CharacterBuilder hpmpmax(int c_MaxHp, int c_MaxMp) {
			      this.c_MaxHp = c_MaxHp;
			      this.c_MaxMp = c_MaxMp;
			      return this;
			    }

		    public CharacterBuilder atkdef(int c_atk, int c_def) {
		      this.c_atk = c_atk;
		      this.c_def = c_def;
		      return this;
		    }
		    
		    public CharacterBuilder intagi(int c_int, int c_agi){
		    	this.c_int = c_int;
		    	this.c_agi = c_agi;
		    	return this;
		    }

		    public CharacterBuilder equip(String arm1, String arm2, String body, String accessory) {
		    	this.arm1 = arm1;
		    	this.arm2 = arm2;
		    	this.body = body;
		    	this.accessory = accessory;
		    	return this;
		    }
		    public CharacterData build() {
		      return new CharacterData(this);
		    }
	 }

	
	
	
	
}
