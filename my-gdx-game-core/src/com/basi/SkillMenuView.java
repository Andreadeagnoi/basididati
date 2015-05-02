package com.basi;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SkillMenuView implements Screen{


	private SadogashimaEditor editor;
	private Stage stage;
	private Skin uiSkin;
	private Skin uiSkinReduced;
	//TABLES
	private Table skillMenuTable;
	private Table titleRow;
	private Table party;
	private Table charSkills;
	private Table skillInfo;
	//CONSTANTS FOR TABLES
	private static final int WIDTH = 160;
	private static final int HEIGHT = 80;
	//FIELDS
	//StatusMenuTable
	private Label statusMenuTitle;
	private ImageButton backToMenu;
	//party table
	private Label partyTitle;
	private List<CharacterData> partyList;
	//char stats table
	//actual fields
	private Label t_SkillName;

	//DATA STRUCTURES
	private ArrayList<CharacterData> partyArray;
	private CharacterData selectedChar;
	private Texture backTexture;
	private Table skillList;
	private SkillData currentSkill = null;
	private Label t_description;


	public SkillMenuView(final SadogashimaEditor editor) {
		this.editor = editor;
	}


	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);	
		uiSkin = ResPack.skinMenuFont(ResPack.MENU_FONT, 36);
		uiSkinReduced = ResPack.skinMenuFont(ResPack.MENU_FONT, 12);


		//setup main table	
		//it will have a 9x7 base grid
		skillMenuTable = new Table(uiSkin);
		skillMenuTable.setFillParent(true);

		titleRow = new Table(uiSkin);

		backTexture = new Texture(Gdx.files.internal("data/back_button.png")); 
		backTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		backToMenu = new ImageButton( new TextureRegionDrawable(new TextureRegion(backTexture)));
		backToMenu.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				editor.setScreen(new MenuView(editor));
			}
		});
		titleRow.add(backToMenu).width(WIDTH).height(HEIGHT);

		statusMenuTitle = new Label(ResPack.SKILLS, uiSkin);
		statusMenuTitle.setAlignment(Align.center);
		titleRow.add(statusMenuTitle).width(WIDTH*6).height(HEIGHT);

		skillMenuTable.add(titleRow).colspan(2);
		skillMenuTable.row();
		skillMenuTable.setBackground( new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/background_tecniche.png")))));
		// new Texture(Gdx.files.internal("background.png"));

		//setup party table
		//it will occupy a space of 8x2 cells, with the head of 2x2 cells
		party = new Table(uiSkin);
		party.defaults().width(WIDTH*2);			
		//partyTitle = new Label(ResPack.inventory.get("1").toString(), uiSkin); //test for loadSavedData()
		partyTitle = new Label(ResPack.PARTY, uiSkin);
		party.add(partyTitle).height(HEIGHT*2).row();
		//creating a list of character's data
		partyList = new  List<CharacterData>(uiSkin);
		partyList.setHeight(HEIGHT*6);
		//set up the list of characters and the data of the first character of the list
		partyArray = new ArrayList<CharacterData>(ResPack.party.values());
		selectedChar = partyArray.get(0);

		partyList.setItems(partyArray.toArray(new CharacterData[partyArray.size()]));
		//when I select a character from partyList I change the char status accordingly
		partyList.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				selectedChar = partyList.getSelected();
				fillSkillList();
			}
		});

		party.add(partyList).height(HEIGHT*6);


		//Table containing skills info

		charSkills = new Table(uiSkin);
		//fist the table with the list of the selected Character's skills
		skillList = new Table(uiSkin);
		skillList.top();
		skillList.defaults().width(WIDTH).height(HEIGHT);
		fillSkillList();
		//then the table with the description of the selected skills
		skillInfo = new Table(uiSkin);
		skillInfo.defaults().width(WIDTH*5).height(HEIGHT);
		t_SkillName = new Label(ResPack.SELECTSKILL, uiSkin);
		skillInfo.add(t_SkillName).row();
		t_description = new Label("", uiSkin);
		skillInfo.add(t_description).height(HEIGHT*2);
		charSkills.add(skillList).height(HEIGHT*5).width(WIDTH*5);
		charSkills.row();
		charSkills.add(skillInfo);

		skillMenuTable.add(party);
		skillMenuTable.add(charSkills);
		stage.addActor(skillMenuTable);
//		skillList.debug();
//		skillMenuTable.debug();
//		party.debug();
//		charSkills.debug();
//		skillInfo.debug();

	}

	private void fillSkillList() {
		ArrayList<String> charSkills = ResPack.db.getCharSkills(String.valueOf(selectedChar.getId()));
		boolean colonnaDestra = false;
		Label skillLabel = null;
		skillList.clear();
		for(final String skill : charSkills){
			skillLabel = new Label(ResPack.skills.get(skill).getName(),uiSkin);
			skillLabel.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y){
					currentSkill = ResPack.skills.get(skill);
					t_SkillName.setText(currentSkill.getName());
					t_description.setText(currentSkill.getDescription());
				}
			});
			skillList.add(skillLabel).width(WIDTH*2).padLeft(10);

			if (!colonnaDestra){
				skillList.add().width(WIDTH-20);
			}
			else {
				skillList.row();
			}

			colonnaDestra = !colonnaDestra;
		}
		if(colonnaDestra){
			skillList.add().width(WIDTH*2).padLeft(10);
		}

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
