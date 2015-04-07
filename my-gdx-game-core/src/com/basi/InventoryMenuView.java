package com.basi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class InventoryMenuView implements Screen{

	private SadogashimaEditor editor;
	private Stage stage;
	private Skin uiSkin;
	//CONSTANTS FOR TABLES
	private static final int WIDTH = 160;
	private static final int HEIGHT = 80;
	//TABLES
	private Table inventoryTable;
	private Table tabTable;
	private Table itemListTable;
	private Table itemInfoTable;
	private Table party;
	private Table charInfo;

	//InventoryTable
	private Label l_inventory;
	private Label consumableTag;
	private Label equipTag;
	private Label keyTag;
	private Skin uiSkinReduced;


	public InventoryMenuView(final SadogashimaEditor editor) {
		this.editor = editor;
	}

	@Override
	public void show() {		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);	
		uiSkin = ResPack.skinMenuFont(ResPack.MENU_FONT, 36);
		uiSkinReduced = ResPack.skinMenuFont(ResPack.MENU_FONT, 20);

		//setup main table
		//setup main table
		//it will have a 9x7 base grid
		inventoryTable = new Table(uiSkin);
		inventoryTable.setFillParent(true);

		l_inventory = new Label(ResPack.INVENTORY, uiSkin);
		inventoryTable.add(l_inventory).height(HEIGHT).colspan(2);
		inventoryTable.row();
		inventoryTable.setBackground(ResPack.createMonocromeDrawable(Color.GRAY));
		
		//setup tabs and list table
		//it will occupy 6x5 cells
		tabTable = new Table(uiSkin);
		
		tabTable.defaults().width(WIDTH).height(HEIGHT);
		
		consumableTag = new Label(ResPack.CONSUMABLE, uiSkinReduced);
		equipTag = new Label(ResPack.EQUIP,uiSkinReduced);
		keyTag = new Label(ResPack.KEY, uiSkinReduced);
		
		tabTable.add(consumableTag);
		tabTable.add(equipTag);
		tabTable.add(keyTag); 
		tabTable.row();
		
		inventoryTable.debug();
		tabTable.debug();
		//Add actors to stage
		inventoryTable.add(tabTable);
		stage.addActor(inventoryTable);
		
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
