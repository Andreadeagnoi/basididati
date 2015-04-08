package com.basi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//reference to color rows http://stackoverflow.com/questions/24250791/make-scene2d-ui-table-with-alternate-row-colours
public class MenuView implements Screen{

	private SadogashimaEditor editor;
	private Stage stage;
	private Table buttons;
	private Table titleRow;
	
	//buttons
	private TextButton status;
	private TextButton equip;
	private TextButton skills;
	private TextButton inventory;
	private Label title;

	public MenuView(final SadogashimaEditor editor) {
		this.editor = editor;
	}

	@Override
	public void show() {
				
		
				//set up the stage and connect an input processor to it
				stage = new Stage();
				Gdx.input.setInputProcessor(stage);
				
				//Load save data from db
				ResPack.db.loadSavedData();
				
				//Setting up the table containing the buttons
				buttons = new Table(ResPack._SKIN);	
				buttons.setFillParent(true);
				buttons.center();
				buttons.defaults().width(300);

				//Adding head label of the buttons table
				titleRow = new Table(ResPack._SKIN);
				title = new Label("MENU DI GIOCO", ResPack._SKIN);		
				title.setAlignment(Align.center);
				titleRow.add(title);
				
				
				titleRow.setBackground(ResPack.createMonocromeDrawable(Color.GRAY));
				buttons.add(titleRow).colspan(2).width(600);
				
				buttons.row();
				
				//Adding the status menu button
				status = new TextButton(ResPack.STATUS, ResPack._SKIN);	
				status.addListener(new ClickListener(){
					@Override 
					public void clicked(InputEvent event, float x, float y){
						editor.setScreen(new StatusMenuView(editor));
					}
				});
				buttons.add(status);


				//Adding the equip menu button
				equip = new TextButton(ResPack.EQUIP, ResPack._SKIN);
				equip.addListener(new ClickListener(){
					@Override 
					public void clicked(InputEvent event, float x, float y){

					}
				});
				buttons.add(equip);
				buttons.row();

				//Adding the skills menu button
				skills = new TextButton(ResPack.SKILLS, ResPack._SKIN);
				skills.addListener(new ClickListener(){
					@Override 
					public void clicked(InputEvent event, float x, float y){
						
						
					}
				});
				buttons.add(skills);
				
				//Adding the inventory menu button
				inventory = new TextButton(ResPack.INVENTORY, ResPack._SKIN);
				inventory.addListener(new ClickListener(){
					@Override 
					public void clicked(InputEvent event, float x, float y){
						editor.setScreen(new InventoryMenuView(editor));
						
					}
				});
				buttons.add(inventory);

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
