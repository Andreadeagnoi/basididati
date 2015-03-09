package com.basi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SadogashimaEditor extends ApplicationAdapter{
//UI
private Stage stage;

//First screen buttons
private TextButton menu;
private TextButton commonTables;
private TextButton editSavedGames;

private Table buttons;

	@Override
	public void create() {		
		super.create();
		//Setting up the UI
		stage = new Stage();
		buttons = new Table(ResPack._SKIN);
		
		buttons.setFillParent(true);
		buttons.center();
		buttons.setLayoutEnabled(true);
		
		menu = new TextButton("Menu", ResPack._SKIN);
		
		buttons.add(menu).width(400);
		buttons.row();
		
		commonTables = new TextButton("Common tables", ResPack._SKIN);
		commonTables.setWidth(600);
		buttons.add(commonTables).width(400);
		buttons.row();
		
		editSavedGames = new TextButton("Edit saved games", ResPack._SKIN);
		editSavedGames.setWidth(600);
		buttons.add(editSavedGames).width(400);
						
		stage.addActor(buttons);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render() {
		super.render();
		
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
}
