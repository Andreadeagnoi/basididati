package com.basi;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.basi.Item.ConsumableItemData;
import com.basi.Item.ItemData;

public class InventoryMenuView implements Screen{

	private SadogashimaEditor editor;
	private Stage stage;
	private Skin uiSkin;
	private Skin uiSkinReduced;
	private String currentTab;
	//CONSTANTS FOR TABLES
	private static final int WIDTH = 160;
	private static final int HEIGHT = 80;
	//TABLES
	private Table inventoryTable;
	private Table titleRow;
	private Table tabTable;
	private Table itemListTable;
	private Table itemInfoTable;
	private Table party;
	private Table charInfo;

	//InventoryTable
	//title table
	
	private Label l_inventory;
	private Texture backTexture;
	private ImageButton backToMenu;
	//tab table
	private Label consumableTag;
	private Label equipTag;
	private Label keyTag;
	private boolean changedTab;
	//items table
	
	//data structures
	private ArrayList<ItemData> currentItems;
	
	


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
		titleRow = new Table(uiSkin);
		//add back button
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
		//add title
		l_inventory = new Label(ResPack.INVENTORY, uiSkin);
		titleRow.add(l_inventory);
		
		inventoryTable.add(titleRow).height(HEIGHT);
		inventoryTable.row();
		inventoryTable.setBackground(ResPack.createMonocromeDrawable(Color.GRAY));
		
		//setup tabs and list table
		//it will occupy 6x5 cells
		//setup tabs table
		//it will occupy 1x5 cells
		tabTable = new Table(uiSkin);		
		tabTable.defaults().width(WIDTH).height(HEIGHT);
		
		consumableTag = new Label(ResPack.CONSUMABLE, uiSkinReduced);
		equipTag = new Label(ResPack.EQUIP,uiSkinReduced);
		keyTag = new Label(ResPack.KEY, uiSkinReduced);
		
		tabTable.add(consumableTag);
		tabTable.add(equipTag);
		tabTable.add(keyTag); 
		tabTable.row();
		
		//setup item list table
		//it will occupy 5x5 cells
		itemListTable = new Table(uiSkin);
		itemListTable.defaults().width(WIDTH).height(HEIGHT);
		currentTab = "consumables";
		fillItemList();
		
		tabTable.add(itemListTable).height(HEIGHT*5).width(WIDTH*5);
		
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

		if(changedTab){
			fillItemList();
		}
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
	
	private void fillItemList(){
		
		if(currentTab.equals("consumables")){
			currentItems = ResPack.inventory.toArrayList("consumables");
		}
		
		for(ItemData item : currentItems){
			itemListTable.add(new Label(item.getName(),uiSkin));
		}
	}

}
