package com.basi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FirstScreen implements Screen{

	final SadogashimaEditor editor;
	//UI
	private Stage stage;

	//First screen buttons
	private TextButton menu;
	private TextButton commonTables;
	private TextButton editSavedGames;

	private Table buttons;

	public FirstScreen(final SadogashimaEditor editor){
		this.editor = editor;
	}

	@Override
	public void show() {
		//set up the stage and connect an input processor to it
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		//Setting up the table containing the buttons
		buttons = new Table(ResPack._SKIN);	
		buttons.setFillParent(true);
		buttons.center();

		//Adding the menu button
		menu = new TextButton("Menu", ResPack._SKIN);	
		menu.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				editor.setScreen(new SelectSaveData(editor));
				dispose();
			}
		});
		buttons.add(menu).width(400);
		buttons.row();

		//Adding the common tables button
		commonTables = new TextButton("Common tables", ResPack._SKIN);
		commonTables.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){

			}
		});
		buttons.add(commonTables).width(400);
		buttons.row();

		//Adding the edit saved games button
		editSavedGames = new TextButton("Edit saved games", ResPack._SKIN);
		editSavedGames.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				editor.setScreen(new SelectSaveData(editor));
				dispose();
			}
		});
		editSavedGames.setWidth(600);
		buttons.add(editSavedGames).width(400);

		stage.addActor(buttons);


	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
