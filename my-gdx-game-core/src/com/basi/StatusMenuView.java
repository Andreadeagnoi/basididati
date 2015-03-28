package com.basi;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StatusMenuView implements Screen{

	private SadogashimaEditor editor;
	private Stage stage;
	//TABLES
	private Table statusMenuTable;
	private Table party;
	private Table status;
	private Table charStats;
	private Table classStats;
	private Table equipment;
	//FIELDS
	//StatusMenuTable
	private Label statusMenuTitle;
	//party table
	private Label partyTitle;
	private List<CharacterData> partyList;
	//char stats table
	private Label l_charName;
	private TextField t_charName;
	
	
	
	//DATA STRUCTURES
	private ArrayList<CharacterData> partyArray;
	private ArrayList<String> nameList; //list of the party's names
	private CharacterData selectedChar;
	
	
	public StatusMenuView(final SadogashimaEditor editor) {
		this.editor = editor;
	}

	
	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);	
		//setup main table
		statusMenuTable = new Table(ResPack._SKIN);
		statusMenuTable.setFillParent(true);
				
		statusMenuTitle = new Label(ResPack.STATUS, ResPack._SKIN);
		statusMenuTable.add(statusMenuTitle);
		statusMenuTable.row();
		statusMenuTable.setBackground(ResPack.createMonocromeDrawable(Color.GRAY));
		
		//setup party table
		party = new Table(ResPack._SKIN);
		
		party.setBackground(ResPack.createMonocromeDrawable(Color.RED));
		partyTitle = new Label(ResPack.PARTY, ResPack._SKIN);
		party.add(partyTitle).row();
		//creating a list of character's data
		partyList = new  List<CharacterData>(ResPack._SKIN);
		
		partyArray = new ArrayList<CharacterData>();
		partyArray.add( new CharacterData
				.CharacterBuilder("a", 0)
				.name("personaggio 1").build());
		partyArray.add( new CharacterData
				.CharacterBuilder("a", 0)
				.name("personaggio 2").build());
		
    
		partyList.setItems(partyArray.toArray(new CharacterData[partyArray.size()]));
		//when I select a character from partyList I change the char status accordingly
		partyList.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				selectedChar = partyList.getSelected();
				t_charName.setText(selectedChar.getName());
			}
		});
		
		party.add(partyList);
		
		
		//specific character status table
		//status tables setup
		status = new Table(ResPack._SKIN);
		charStats = new Table(ResPack._SKIN);
		classStats = new Table(ResPack._SKIN);
		equipment = new Table(ResPack._SKIN);
		
		//define charStats content
		l_charName = new Label(ResPack.NAME, ResPack._SKIN);
		t_charName = new TextField("prova", ResPack._SKIN);
		charStats.add(l_charName);
		charStats.add(t_charName);
		
		//build the status table
		status.add(charStats);
		status.row();
		status.add(classStats);
		status.row();
		status.add(equipment);
		status.row();
		
		//build the main table
		statusMenuTable.add(party);
		statusMenuTable.add(status);
		
		
		//drawing debug lines
		statusMenuTable.debug();
		party.debug();
		stage.addActor(statusMenuTable);
		
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
