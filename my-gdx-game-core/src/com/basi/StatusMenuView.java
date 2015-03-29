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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
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
	private Table spriteTable;
	//CONSTANTS FOR TABLES
	private static final int WIDTH = 160;
	private static final int HEIGHT = 80;
	//FIELDS
	//StatusMenuTable
	private Label statusMenuTitle;
	//party table
	private Label partyTitle;
	private List<CharacterData> partyList;
	//char stats table
	//labels of stats
	private Label l_classLevel;
	private Label l_hp;
	private Label l_mp;
	private Label l_atk;
	private Label l_def;
	private Label l_int;
	private Label l_agi;
	//actual fields
	private Label t_charName;
	private Label t_classLevel;
	private Label t_hp;
	private Label t_mp;
	private Label t_atk;
	private Label t_def;
	private Label t_int;
	private Label t_agi;	
	//sprite table
	private Label t_sprite;
	//class stats table
	private Label l_className;
	private Label l_classExp;
	private Label t_className;
	private Label t_classExp;
	//equipment info table
	private Label l_equipment;
	private Label l_arm1;
	private Label l_arm2;
	private Label l_body;
	private Label l_accessory;
	private Label t_arm1;
	private Label t_arm2;
	private Label t_body;
	private Label t_accessory;

	
	
	
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
		//it will have a 9x7 base grid
		statusMenuTable = new Table(ResPack._SKIN);
		statusMenuTable.setFillParent(true);
				
		statusMenuTitle = new Label(ResPack.STATUS, ResPack._SKIN);
		statusMenuTable.add(statusMenuTitle).height(HEIGHT).colspan(2);
		statusMenuTable.row();
		statusMenuTable.setBackground(ResPack.createMonocromeDrawable(Color.GRAY));
		
		//setup party table
		//it will occupy a space of 8x2 cells, with the head of 2x2 cells
		party = new Table(ResPack._SKIN);
		party.defaults().width(WIDTH*2);
		
		partyTitle = new Label(ResPack.PARTY, ResPack._SKIN);
		party.add(partyTitle).height(HEIGHT*2).row();
		//creating a list of character's data
		partyList = new  List<CharacterData>(ResPack._SKIN);
		partyList.setHeight(HEIGHT*6);
		//set up the list of characters and the data of the first character of the list
		partyArray = genTempCharacters();
		selectedChar = partyArray.get(0);
		
		partyList.setItems(partyArray.toArray(new CharacterData[partyArray.size()]));
		//when I select a character from partyList I change the char status accordingly
		partyList.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				selectedChar = partyList.getSelected();
				t_charName.setText(selectedChar.getName());
			}
		});
		
		party.add(partyList).height(80*6);
		
		
		//specific character status table
		//status tables setup
		status = new Table(ResPack._SKIN);
		charStats = new Table(ResPack._SKIN);
		charStats.defaults().width(WIDTH).height(HEIGHT);
		spriteTable = new Table(ResPack._SKIN);
		spriteTable.defaults().width(WIDTH).height(HEIGHT*4);
		classStats = new Table(ResPack._SKIN);
		classStats.defaults().width(WIDTH).height(HEIGHT);
		equipment = new Table(ResPack._SKIN);
		equipment.defaults().width(WIDTH).height(HEIGHT);
		
		
		//define charStats content (4x4 cells)
		//first row, name LV classLevel
		t_charName = new Label(selectedChar.getName(), ResPack._SKIN);
		charStats.add(t_charName).colspan(2);
		l_classLevel = new Label(ResPack.LEVEL, ResPack._SKIN);
		charStats.add(l_classLevel);
		t_classLevel = new Label(String.valueOf(selectedChar.getClassLevel()), ResPack._SKIN);
		charStats.add(t_classLevel).row();
		//second row, HP hp MP mp
		l_hp = new Label(ResPack.HP, ResPack._SKIN);
		charStats.add(l_hp);
		t_hp = new Label(String.valueOf(selectedChar.getC_hp()), ResPack._SKIN);
		charStats.add(t_hp);
		l_mp = new Label(ResPack.MP, ResPack._SKIN);
		charStats.add(l_mp);
		t_mp = new Label(String.valueOf(selectedChar.getC_mp()), ResPack._SKIN);
		charStats.add(t_mp).row();
		//third row, ATK atk DEF def
		l_atk = new Label(ResPack.ATK, ResPack._SKIN);
		charStats.add(l_atk);
		t_atk = new Label(String.valueOf(selectedChar.getC_atk()), ResPack._SKIN);
		charStats.add(t_atk);
		l_def = new Label(ResPack.DEF, ResPack._SKIN);
		charStats.add(l_def);
		t_def = new Label(String.valueOf(selectedChar.getC_def()), ResPack._SKIN);
		charStats.add(t_def).row();
		//fourth row, INT int AGI agi
		l_int = new Label(ResPack.INT, ResPack._SKIN);
		charStats.add(l_int);
		t_int = new Label(String.valueOf(selectedChar.getC_int()), ResPack._SKIN);
		charStats.add(t_int);
		l_agi = new Label(ResPack.AGI, ResPack._SKIN);
		charStats.add(l_agi);
		t_agi = new Label(String.valueOf(selectedChar.getC_agi()), ResPack._SKIN);
		charStats.add(t_agi).row();
		
		//define class stats content (1x5)
		//first (and only) row CLASS class EXP exp
		l_className = new Label(ResPack.CLASS_NAME, ResPack._SKIN);
		classStats.add(l_className);
		t_className = new Label(String.valueOf(selectedChar.getActiveClass()), ResPack._SKIN);
		classStats.add(t_className).width(WIDTH*2);
		l_classExp = new Label(ResPack.CLASS_EXP, ResPack._SKIN);
		classStats.add(l_classExp);
		t_classExp = new Label(String.valueOf(selectedChar.getExp()), ResPack._SKIN);
		classStats.add(t_classExp).row();
		
		//define equipment content (3*5)
		//first row EQUIPMENT
		l_equipment = new Label(ResPack.EQUIPMENT, ResPack._SKIN);
		classStats.add(l_equipment).colspan(4);
		//second row  ARM1 NULL NULL ARM2
		//labels
		l_arm1 = new Label(ResPack.ARM1, ResPack._SKIN);
		equipment.add(l_arm1).width(WIDTH*2).height((int)(HEIGHT*3/10));
		equipment.add().height((int)(HEIGHT*3/10));
		l_body = new Label(ResPack.BODY, ResPack._SKIN);
		equipment.add(l_body).width(WIDTH*2).height((int)(HEIGHT*3/10)).row();
		//fields
		t_arm1 = new Label(String.valueOf(selectedChar.getArm1()), ResPack._SKIN);
		equipment.add(t_arm1).width(WIDTH*2).height((int)(HEIGHT*7/10));
		equipment.add().height((int)(HEIGHT*7/10));
		t_body = new Label(String.valueOf(selectedChar.getBody()), ResPack._SKIN);
		equipment.add(t_body).width(WIDTH*2).height((int)(HEIGHT*7/10)).row();	
		//third row  ARM1 NULL NULL ARM2
		//labels
		l_arm2 = new Label(ResPack.ARM2, ResPack._SKIN);
		equipment.add(l_arm2).width(WIDTH*2).height((int)(HEIGHT*3/10));
		equipment.add().height((int)(HEIGHT*3/10));
		l_accessory = new Label(ResPack.ACCESSORY, ResPack._SKIN);
		equipment.add(l_accessory).width(WIDTH*2).height((int)(HEIGHT*3/10)).row();
		//fields
		t_arm2 = new Label(String.valueOf(selectedChar.getArm2()), ResPack._SKIN);
		equipment.add(t_arm2).width(WIDTH*2).height((int)(HEIGHT*7/10));
		equipment.add().height((int)(HEIGHT*7/10));
		t_accessory = new Label(String.valueOf(selectedChar.getAccessory()), ResPack._SKIN);
		equipment.add(t_accessory).width(WIDTH*2).height((int)(HEIGHT*7/10)).row();	
		
		
		//build the status table
		status.add(charStats);
		status.add(spriteTable);
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
		charStats.debug();
		classStats.debug();
		equipment.debug();
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
	
	private ArrayList<CharacterData> genTempCharacters() {
		ArrayList<CharacterData> partyArray = new ArrayList<CharacterData>();
		partyArray.add( new CharacterData
				.CharacterBuilder("a", 0)
				.name("personaggio 1").build());
		partyArray.add( new CharacterData
				.CharacterBuilder("a", 0)
				.name("personaggio 2").build());
		partyArray.add( new CharacterData
				.CharacterBuilder("a", 0)
				.name("personaggio 3").build());
		partyArray.add( new CharacterData
				.CharacterBuilder("a", 0)
				.name("personaggio 4").build());
		partyArray.add( new CharacterData
				.CharacterBuilder("a", 0)
				.name("personaggio 5").build());
		partyArray.add( new CharacterData
				.CharacterBuilder("a", 0)
				.name("personaggio 6").build());
		return partyArray;
	}

	
}
