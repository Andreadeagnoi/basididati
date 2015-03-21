package com.basi;

import java.util.Date;
import java.util.Random;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SadogashimaEditor extends Game{
	
SpriteBatch batch;
BitmapFont font;	




	@Override
	public void create() {		
		
		
		//Setting up the firstscreen
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		font = new BitmapFont();
		this.setScreen(new FirstScreen(this));
		
		
	}

	@Override
	public void render() {
		super.render();
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	


	


	/**
	 * temporary methods to fill the saveData structure, it will be replaced by
	 * the call to the db.
	 * field of SaveGame(name, creationTime, lastSaveTime, total play time)
	 * @param nSaves number of saves to generate
	 * @return an array of array of Strings containing temp data for savegames
	 */
	
}
