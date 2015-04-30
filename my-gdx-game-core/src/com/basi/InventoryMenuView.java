package com.basi;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
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
	private Table partyTable;

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
	
	//iteminfo table
	private Label infoTag;
	private Label t_itemName;
	private Label l_description;
	private ScrollPane scroll_description;
	private Label t_description;
	private Label l_quantity;
	private Label t_quantity;
	private Button deleteButton;
	private Label l_delete;
	private Dialog throwDialog;
	
	//data structures
	private ArrayList<ItemData> currentItemList;
	private ItemData currentItem;
	private CharacterData currentChar;
	private Table charInfo;
	private Label characterHP;
	private Label characterMP;
	private Label characterName;
	private ScrollPane scrollParty;
	private Label characterLevel;
	private ArrayList<Table> charCells = new ArrayList<Table>();
	int i = 0;

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
		l_inventory.setAlignment(Align.center);
		titleRow.add(l_inventory).width(WIDTH*6);
		
		inventoryTable.add(titleRow).height(HEIGHT).colspan(2);
		inventoryTable.row();
		inventoryTable.setBackground(ResPack.createMonocromeDrawable(Color.GRAY));
		
		//setup tabs and list table
		//it will occupy 6x5 cells
		//setup tabs table
		//it will occupy 1x5 cells
		tabTable = new Table(uiSkin);		
		tabTable.defaults().width(WIDTH).height(HEIGHT);
		
		consumableTag = new Label(ResPack.CONSUMABLE, uiSkin);
		consumableTag.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(!currentTab.equals("consumables")){
					currentTab = "consumables";
					fillItemList();
				}		
			}
		});
		
		equipTag = new Label(ResPack.EQUIP,uiSkin);
		equipTag.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(!currentTab.equals("equips")){
					currentTab = "equips";
					fillItemList();
				}		
			}
		});
		
		keyTag = new Label(ResPack.KEY, uiSkin);
		keyTag.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(!currentTab.equals("keys")){
					currentTab = "keys";
					fillItemList();
				}		
			}
		});
		
		tabTable.add(consumableTag);
		tabTable.add(equipTag);
		tabTable.add(keyTag); 
		tabTable.row();
		
		//setup item list table
		//it will occupy 5x5 cells
		itemListTable = new Table(uiSkin);
		itemListTable.top();
		itemListTable.defaults().width(WIDTH*2).height(HEIGHT);
		currentTab = "consumables";
		fillItemList();
		tabTable.add(itemListTable).height(HEIGHT*5).width(WIDTH*5).colspan(3);

		//setup item's info table
		//it will occupy 6x2 cells
		itemInfoTable = new Table(uiSkin);
		itemInfoTable.defaults().width(WIDTH*2).height(HEIGHT);
		infoTag = new Label(ResPack.INFO, uiSkin);
		itemInfoTable.add(infoTag).colspan(2).row();
		t_itemName = new Label("",uiSkin);
		itemInfoTable.add(t_itemName).colspan(2).row();
		l_description = new Label(ResPack.DESCRIPTION, uiSkinReduced);
		itemInfoTable.add(l_description).height((int)(HEIGHT*0.2)).colspan(2).row();
		t_description = new Label(ResPack.SELECTITEM, uiSkin);
		t_description.setWrap(true); // to have newline on bounds
		scroll_description = new ScrollPane(t_description,uiSkin);
		scroll_description.getStyle();
		itemInfoTable.add(scroll_description).height((int)(HEIGHT*1.8)).colspan(2).pad(10).row();
		l_quantity = new Label(ResPack.QUANTITY+":  ", uiSkin);
		l_quantity.setAlignment(Align.center);
		itemInfoTable.add(l_quantity).width(WIDTH).height(HEIGHT-10);
		t_quantity = new Label("", uiSkin);
		itemInfoTable.add(t_quantity).width(WIDTH).height(HEIGHT-10).row();
		l_delete = new Label(ResPack.THROW, uiSkin);
		deleteButton = new Button(l_delete, uiSkin);
		//listener on delete button that opens a dialog
		deleteButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(currentItem == null){
					return;
				}
				throwDialog = new Dialog(ResPack.THROW,uiSkinReduced);
				throwDialog.text("Sei pazzo? Dirai addio a tutta la scorta di " + currentItem.getName() );
				TextButton dialogOk = new TextButton("OK", uiSkin);				
				dialogOk.addListener(new ClickListener(){
					@Override 
					public void clicked(InputEvent event, float x, float y){
						ResPack.inventory.remove(String.valueOf(currentItem.getId()), currentItem.getQuantity());
						currentItem = null;
						t_itemName.setText(""); 
						t_description.setText(ResPack.SELECTITEM);
						t_quantity.setText("");
						fillItemList();
						throwDialog.hide();
					}
				});
				throwDialog.getButtonTable().add(dialogOk);
				TextButton dialogCancel = new TextButton("Annulla", uiSkin);
				dialogCancel.addListener(new ClickListener(){
					@Override 
					public void clicked(InputEvent event, float x, float y){
						
						throwDialog.hide();
					}
				});
				throwDialog.getButtonTable().add(dialogCancel);
				throwDialog.show(stage);
			}
		});
		itemInfoTable.add(deleteButton).colspan(2);
		
		//party list table
		
		partyTable = new Table(uiSkin);
		scrollParty = new ScrollPane(partyTable, uiSkin);
		scrollParty.setScrollingDisabled(false, true);
		partyTable.left();
		Collection<CharacterData> party =  ResPack.party.values();
		for(CharacterData currentChar : party){
			genCharTable(currentChar);
		}
		
		
		//debug lines
		inventoryTable.debug();
		tabTable.debug();
		itemListTable.debug();
		itemInfoTable.debug();
		partyTable.debug();
		//Add actors to stage
		inventoryTable.add(tabTable);
		inventoryTable.add(itemInfoTable);
		inventoryTable.row();
		inventoryTable.add(scrollParty).left().height(HEIGHT*2).width(WIDTH*7).colspan(2);
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
		itemListTable.clear();
		currentItemList = ResPack.inventory.toArrayList(currentTab);
		boolean colonnaDestra = false;
		Label itemLabel = null;
		for(final ItemData item : currentItemList){
			if(item.getQuantity() == 0){
				continue;
			}
			itemLabel = new Label(item.getName(),uiSkin);
			itemLabel.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y){
					currentItem = ResPack.inventory.get(String.valueOf(item.getId()));
					t_itemName.setText(currentItem.getName());
					t_description.setText(currentItem.getDescription());
					t_quantity.setText(String.valueOf(currentItem.getQuantity()));
				}
			});
			itemListTable.add(itemLabel).width(WIDTH*2).padLeft(10);
			
			if (!colonnaDestra){
				itemListTable.add().width(WIDTH-20);
			}
			else {
				itemListTable.row();
			}
			
			colonnaDestra = !colonnaDestra;
		}
		if(colonnaDestra){
			itemListTable.add().width(WIDTH*2).padLeft(10);
		}
		
	}
	
	private void genCharTable(CharacterData currentChar){
		
			charInfo = new Table(uiSkin);
			characterName = new Label(currentChar.getName(),uiSkin);
			characterLevel = new Label("Lv. " + currentChar.getClassLevel(), uiSkin);
			characterHP = new Label(ResPack.HP + " " + currentChar.getC_hp() + "/" + currentChar.getC_MaxHp(), uiSkin);
			characterHP.setName("HP");
			characterMP = new Label(ResPack.MP + " " + currentChar.getC_mp() + "/" + currentChar.getC_MaxMp(), uiSkin);
			charInfo.add(characterName).height(HEIGHT/2).row();
			charInfo.add(characterLevel).height(HEIGHT/2).row();
			charInfo.add(characterHP).height(HEIGHT/2).row();
			charInfo.add(characterMP).height(HEIGHT/2);
			charCells.add(charInfo);
			final String idChar = String.valueOf(currentChar.getId());
			final int partyIndex = i++;
			charInfo.addListener(new ClickListener(){
					@Override 
					public void clicked(InputEvent event, float x, float y){
						if (currentItem instanceof ConsumableItemData){
							ConsumableItemData consumeItem = (ConsumableItemData) currentItem;
							consumeItem.useOn(ResPack.party.get(idChar));
							Label label = charCells.get(Integer.valueOf(partyIndex)).findActor("HP");
							label.setText(ResPack.HP + " " + ResPack.party.get(idChar).getC_hp()+"/"+ResPack.party.get(idChar).getC_MaxHp());
							if(currentItem.getQuantity() == 0){
								fillItemList();
								currentItem = null;
								t_itemName.setText("");
								t_description.setText(ResPack.SELECTITEM);
								t_quantity.setText("");
							}
							else{
								t_quantity.setText(String.valueOf(currentItem.getQuantity()));
							}
							return;
						}
							
					}
				});
			partyTable.add(charInfo).width(WIDTH*2).align(Align.left);
		
		
	}

}
