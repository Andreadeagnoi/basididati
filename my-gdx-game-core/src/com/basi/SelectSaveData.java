package com.basi;

import java.util.Date;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class SelectSaveData implements Screen{
	//DB FINALS
	private final int fieldsSave = 4;
	final SadogashimaEditor editor;
	Stage savesStage;
	
	
	private TextField nameText;
	private Label addressLabel;
	private TextField addressText;
	
	private Label nameLabel;
	private Label totalPlaytimeLabel;
	private Label creationTimeLabel;
	private Label lastSaveTimeLabel;
	private TextField[] name;
	private TextField[] totalPlaytime;
	private TextField[] creationTime;
	private TextField[] lastSaveTime;
	
	
	public SelectSaveData(final SadogashimaEditor editor){
		this.editor = editor;
	}
	
	@Override
	public void show() {
		savesStage = new Stage();
		Gdx.input.setInputProcessor(savesStage);
		
		//set up table for the saves
		Table table = new Table(ResPack._SKIN);
		table.setFillParent(true);
		table.center();
		
		//initialize the saves data
		String[][] saveData = genSaveData(4);
		name = new TextField[saveData.length];
		totalPlaytime = new TextField[saveData.length];
		creationTime = new TextField[saveData.length];
		lastSaveTime = new TextField[saveData.length];
		
		//create labels for the columns
		nameLabel = new Label("NOME",ResPack._SKIN);
		table.add(nameLabel);
		totalPlaytimeLabel = new Label("TEMPO DI GIOCO", ResPack._SKIN);
		table.add(totalPlaytimeLabel);
		creationTimeLabel = new Label("DATA DI CREAZIONE", ResPack._SKIN);
		table.add(creationTimeLabel);
		lastSaveTimeLabel = new Label("DATA ULTIMO SALVATAGGIO", ResPack._SKIN);
		table.add(lastSaveTimeLabel);
		table.row();
		
		//filling the table with the save data
		for(int row = 0; row < saveData.length; row++) {
			name[row] = new TextField(saveData[row][0], ResPack._SKIN);
			table.add(name[row]);
			totalPlaytime[row] = new TextField(saveData[row][1], ResPack._SKIN);
			table.add(totalPlaytime[row]);
			creationTime[row] = new TextField(saveData[row][2], ResPack._SKIN);
			table.add(creationTime[row]);
			lastSaveTime[row] = new TextField(saveData[row][3], ResPack._SKIN);
			table.add(lastSaveTime[row]);
			table.row();
		}
		
		savesStage.addActor(table);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		savesStage.act();
		savesStage.draw();
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

	private String[][] genSaveData(int nSaves) {
		Random rng = new Random();
		Date date = new Date();
		String[][] tempSaveData = new String[nSaves][fieldsSave];
		for(int row = 0; row < nSaves; row++){
			tempSaveData[row][0] = "Salvataggio " + row;
			tempSaveData[row][1] = "" + new Date(rng.nextLong());
			tempSaveData[row][2] = "" + new Date(rng.nextLong());
			tempSaveData[row][3] = "" + rng.nextInt();
		}
		return tempSaveData;
	}
}
