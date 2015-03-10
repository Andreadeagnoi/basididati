package com.basi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/** Class to load all assets for the game */
public class ResPack {
	//UI
	public static final Skin _SKIN = new Skin(Gdx.files.internal("skin/uiskin.json"));
	
	//Strings
	public static final String EDIT = "Modifica";
	public static final String MENU = "Menu";
}
