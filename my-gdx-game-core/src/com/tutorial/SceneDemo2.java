package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class SceneDemo2 implements ApplicationListener{

	public class MyActor extends Actor {
		Texture texture = new Texture(Gdx.files.internal("data/aereo.png"));
		public boolean started = false;
		
		public MyActor(){
			setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
		}
		
		@Override
		public void draw(Batch batch, float alpha){
			batch.draw(texture,this.getX(),this.getY());
			
		}
	}
	
	private Stage stage;
	
	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		MyActor myActor = new MyActor();
		// Here we create a MoveToAction, which moves the attached Actor to a
		// given position over time. You set the position to move to with the
		// method setPosition() and the total duration of the action using
		// setDuration(). You assign the action to the Actor using addAction().
		// All actions are derived from the Action class, while MoveToAction is
		// derived from TemporalAction, which is an action that has a duration.
		// In addition to MoveToAction, there are a number of others such as
		// MoveByAction, ScaleToAction, ColorAction, DelayAction, RepeatAction,
		// RotateByAction, etc.
		MoveToAction moveAction = new MoveToAction();
		moveAction.setPosition(300f, 0f);
		moveAction.setDuration(10f);
		myActor.addAction(moveAction);
		
		stage.addActor(myActor);	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
