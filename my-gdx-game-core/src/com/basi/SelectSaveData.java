package com.basi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.sql.Database;



public class SelectSaveData implements Screen {
	// DB FINALS
	private final int fieldsSave = 4;
	final SadogashimaEditor editor;
	Stage savesStage;

	private TextField nameText;
	private Label addressLabel;
	private TextField addressText;
	
	//button to crete new saves
	private TextButton genSave;
	private TextField genSaveName;

	private Label nameLabel;
	private Label totalPlaytimeLabel;
	private Label creationTimeLabel;
	private Label lastSaveTimeLabel;
	private TextField[] name;
	private TextField[] totalPlaytime;
	private TextField[] creationTime;
	private TextField[] lastSaveTime;
	private ScrollPane scroll;
	private Table table;
	private Table saveTable;
	ArrayList<SaveData> saveData ;
	


	public SelectSaveData(final SadogashimaEditor editor) {
		this.editor = editor;
		
	}

	@Override
	public void show() {
		savesStage = new Stage();
		Gdx.input.setInputProcessor(savesStage);
		saveData = ResPack.db.getSaves();
		saveData = ResPack.db.getSaves();
		// set up table for the saves
		table = new Table(ResPack._SKIN);
		table.setFillParent(true);
		table.top();
		table.defaults().width(250);
		// set up the scrolling component
		
		
		

		// create labels for the columns
		
		creationTimeLabel = new Label("DATA DI CREAZIONE", ResPack._SKIN);
		creationTimeLabel.setAlignment(Align.center);
		table.add(creationTimeLabel);
		nameLabel = new Label("NOME", ResPack._SKIN);
		nameLabel.setAlignment(Align.center);
		table.add(nameLabel);
		totalPlaytimeLabel = new Label("TEMPO DI GIOCO", ResPack._SKIN);
		totalPlaytimeLabel.setAlignment(Align.center);
		table.add(totalPlaytimeLabel).width(150);
		lastSaveTimeLabel = new Label("DATA ULTIMO SALVATAGGIO", ResPack._SKIN);
		lastSaveTimeLabel.setAlignment(Align.center);
		table.add(lastSaveTimeLabel);
		table.add().width(100);
		table.add().width(100);
		table.row();
		
		saveTable = new Table(ResPack._SKIN);
		saveTable.debug();
		saveTable.defaults().width(250);
		fillSaveList();	
		table.debug();
		scroll = new ScrollPane(saveTable, ResPack._SKIN);
		scroll.setFillParent(true);
		//let's add a button to generate saves
		genSave = new TextButton("inserisci salvataggio",ResPack._SKIN);
		genSaveName = new TextField("",ResPack._SKIN);
		genSaveName.setMaxLength(20);
		
		genSave.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				final String saveName = genSaveName.getText();
				String processedSaveName = "";
				int j = 0;
				for(int i=0; i<saveName.length();){
					j = saveName.indexOf("'", i);
					if(j == -1){
						processedSaveName = processedSaveName + saveName.substring(i);
						break;
					}
					processedSaveName = processedSaveName + saveName.substring(i, j);
					processedSaveName = processedSaveName + "''";
					i = j+1;
				}
				if(!processedSaveName.equals("")){
					SaveData genSave = ResPack.db.insertSave(processedSaveName);
					saveTable.add(new TextField(String.valueOf(genSave.getCreationTime()), ResPack._SKIN));
					saveTable.add(new TextField(genSave.getSaveName(), ResPack._SKIN));
					saveTable.add(new TextField(String.valueOf(genSave.getTotalPlayTime()), ResPack._SKIN)).width(150);
					saveTable.add(new TextField(String.valueOf(genSave.getLastSaveTime()), ResPack._SKIN));
					saveTable.add(buttonRow(genSave.getCreationTime(),ResPack.EDIT)).width(100);
					saveTable.add(buttonRow(genSave.getCreationTime(),ResPack.MENU)).width(100);
					saveTable.row();
					genSaveName.setText("");
				}
			}
		});
		table.add(saveTable).colspan(6).row();
		table.add(genSave);
		table.add(genSaveName);
		table.row();
		scroll.debug();
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

//	//method to test the link with the dbmanager
//	private String[][] genSaveData(int nSaves) {
//		Random rng = new Random();
//		Date date = new Date();
//		String[][] tempSaveData = new String[nSaves][fieldsSave];
//		for (int row = 0; row < nSaves; row++) {
//			ResPack.db.insertSaveTemp();
//			tempSaveData[row][0] = "Salvataggio " + row;
//			tempSaveData[row][1] = "" + new Date(rng.nextLong());
//			tempSaveData[row][2] = "" + new Date(rng.nextLong());
//			tempSaveData[row][3] = "" + rng.nextInt();
//		}
//		return tempSaveData;
//	}
//	
	
	//method to randomly generate saves (not used anymore)
//	private String[][] genRandomSaveData(int nSaves) {
//		Random rng = new Random();
//		Date date = new Date();
//		String[][] tempSaveData = new String[nSaves][fieldsSave];
//		for (int row = 0; row < nSaves; row++) {
//			tempSaveData[row][0] = "Salvataggio " + row;
//			tempSaveData[row][1] = "" + new Date(rng.nextLong());
//			tempSaveData[row][2] = "" + new Date(rng.nextLong());
//			tempSaveData[row][3] = "" + rng.nextInt();
//		}
//		return tempSaveData;
//	}

	/**
	 * Used by SelectSaveData to create a button that opens a edit screen with
	 * the row's save as reference
	 * It accepts only ResPack.EDIT and ResPack.MENU as input parameters for mode
	 * @param row
	 * @return
	 */
	private TextButton buttonRow(String save_id, String mode) {
		TextButton button = new TextButton(mode,ResPack._SKIN);
		final String modeListener = mode;
		final String currentSaveId = save_id;
	
		button.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				if(modeListener.equals(ResPack.EDIT)){
					ResPack.db.deleteSaveData(currentSaveId);
					saveTable.clear();
					fillSaveList();
				}
				else {
					ResPack.currentSave = currentSaveId;
					//Load save data from db
					ResPack.db.loadSavedData();

					//set menu view
					editor.setScreen(new MenuView(editor));				
				}
				dispose();
			}
		});
		return button;
	}
	
	private void fillSaveList() {
		// initialize the saves data
				ArrayList<SaveData> saveData = ResPack.db.getSaves();
				
		//initialize textfields for the layout of the table
				name = new TextField[saveData.size()];
				totalPlaytime = new TextField[saveData.size()];
				creationTime = new TextField[saveData.size()];
				lastSaveTime = new TextField[saveData.size()];
		
		SaveData readSave = null;
		// filling the table with the save data
		for (int row = 0; row < saveData.size(); row++) {
			readSave = saveData.get(row);
			creationTime[row] = new TextField(String.valueOf(readSave.getCreationTime()), ResPack._SKIN);
			saveTable.add(creationTime[row]);
			name[row] = new TextField(readSave.getSaveName(), ResPack._SKIN);
			saveTable.add(name[row]);
			totalPlaytime[row] = new TextField(String.valueOf(readSave.getTotalPlayTime()), ResPack._SKIN);
			saveTable.add(totalPlaytime[row]).width(150);		
			lastSaveTime[row] = new TextField(String.valueOf(readSave.getLastSaveTime()), ResPack._SKIN);
			saveTable.add(lastSaveTime[row]);
			saveTable.add(buttonRow(readSave.getCreationTime(),ResPack.EDIT)).width(100);
			saveTable.add(buttonRow(readSave.getCreationTime(),ResPack.MENU)).width(100);
			saveTable.row();
		}
	}

}
