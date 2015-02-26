package com.tutorial;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SceneDemo2staticimport implements ApplicationListener{

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
		// By statically importing Actions, like so:
		// import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
		// You can also chain actions, so it’s possible to express the above
		// action like:
		
		myActor.addAction(sequence(scaleTo(0.5f,0.5f,5f),rotateTo(90.0f,5f),moveTo(300.0f,0f,5f)));
		stage.addActor(myActor);

		// Or run in parallel, like so: 
		
//		myActor.addAction(parallel(scaleTo(0.5f,0.5f,5f),rotateTo(90.0f,5f),moveTo(300.0f,0f,5f)));
//		stage.addActor(myActor);
		
		// You can also have an action simply be code. Consider the above
		// example, let’s say you wanted to log when the MoveToAction was
		// complete. You could accomplish this by doing the following:
		myActor.addAction(sequence(moveTo(300.0f,0f,5f),
		        run(new Runnable(){
		            @Override
		            public void run() {
		                Gdx.app.log("STATUS", "Action complete");
		            }
		            
		})));
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
