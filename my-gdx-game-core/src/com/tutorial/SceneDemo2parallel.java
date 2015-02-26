package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class SceneDemo2parallel implements ApplicationListener{

	public class MyActor extends Actor {
		Texture texture = new Texture(Gdx.files.internal("data/aereo.png"));
		public boolean started = false;
		
		public MyActor(){
			setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
		}
		
		// here is one important thing to be aware of. The draw() method in
		// MyActor we’ve been using until this point is not capable of
		// displaying rotation or scaling by default. If you wish to enabled
		// scaled/rotated drawing like above, you need to make a minor
		// adjustment to draw()
		@Override
		public void draw(Batch batch, float alpha){
			batch.draw(texture,this.getX(),getY(),this.getOriginX(),this.getOriginY(),this.getWidth(),
		            this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation(),0,0,
		            texture.getWidth(),texture.getHeight(),false,false);
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
		RotateToAction rotateAction = new RotateToAction();
		ScaleToAction scaleAction = new ScaleToAction(); 

		moveAction.setPosition(300f, 0f);
		moveAction.setDuration(5f);
		rotateAction.setRotation(90f);
		rotateAction.setDuration(5f);
		scaleAction.setScale(0.5f);
		scaleAction.setDuration(5f);

		myActor.addAction(moveAction);
		myActor.addAction(rotateAction);
		myActor.addAction(scaleAction);
		
		// Obviously there are going to be many times when you want to delay an
		// action, or run them in sequence. Fortunately LibGdx supports exactly
		// this. Say you wanted to scale then rotate then move your Actor, you
		// can accomplish this using a SequenceAction
		SequenceAction sequenceAction = new SequenceAction();

		moveAction.setPosition(300f, 0f);
		moveAction.setDuration(5f);
		rotateAction.setRotation(90f);
		rotateAction.setDuration(5f);
		scaleAction.setScale(0.5f);
		scaleAction.setDuration(5f);

		sequenceAction.addAction(scaleAction);
		sequenceAction.addAction(rotateAction);
		sequenceAction.addAction(moveAction);


		myActor.addAction(sequenceAction);
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
