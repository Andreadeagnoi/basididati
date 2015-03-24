package com.basi;

import java.util.Date;

public class CharacterData {

	// non me ne frega un cazzo della data, la tratto come stringa per
	// semplicità e fanculo sql e java
	//private Date dataCreazione;
	private String id_salvataggio;
	private int id_personaggio;

	private String name;
	private String sprite;
	// It would be better to have a activeClass class
	private String activeClass;
	private int classLevel;
	private int exp;
	// It would be better to have a Stats class
	private int c_hp;
	private int c_mp;
	private int c_atk;
	private int c_def;
	private int c_agi;
	private int c_int;

	public CharacterData(CharacterBuilder builder) {
		this.id_salvataggio = builder.id_salvataggio;
		this.id_personaggio = builder.id_personaggio;
		this.name = builder.name;
		this.sprite = builder.sprite;
		this.activeClass = builder.activeClass;
		this.classLevel = builder.classLevel;
		this.exp = builder.exp;
		this.c_hp = builder.c_hp;
		this.c_mp = builder.c_mp;
		this.c_atk = builder.c_atk;
		this.c_def = builder.c_def;
		this.c_agi = builder.c_agi;
		this.c_int = builder.c_int;
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
		return "CharacterData [name=" + name + ", sprite=" + sprite
				+ ", activeClass=" + activeClass + ", classLevel=" + classLevel
				+ ", exp=" + exp + ", c_hp=" + c_hp + ", c_mp=" + c_mp
				+ ", c_atk=" + c_atk + ", c_def=" + c_def + ", c_agi=" + c_agi
				+ ", c_int=" + c_int + "]";
	}
	
	 public String getId_salvataggio() {
		return id_salvataggio;
	}

	public void setId_salvataggio(String id_salvataggio) {
		this.id_salvataggio = id_salvataggio;
	}

	public int getId_personaggio() {
		return id_personaggio;
	}

	public void setId_personaggio(int id_personaggio) {
		this.id_personaggio = id_personaggio;
	}

	public static class CharacterBuilder {
		
		private String id_salvataggio;
		private int id_personaggio;

		private String name;
		private String sprite;
		//It would be better to have a activeClass class
		private String activeClass;
		private int classLevel;
		private int exp;
		// It would be better to have a Stats class
		private int c_hp;
		private int c_mp;
		private int c_atk;
		private int c_def;
		private int c_agi;
		private int c_int;

		    public CharacterBuilder(String id_salvataggio, int id_personaggio) {
		      this.id_salvataggio = id_salvataggio;
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

		    public CharacterData build() {
		      return new CharacterData(this);
		    }
	 }
	
	
	
}
