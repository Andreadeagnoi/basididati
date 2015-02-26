package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Pool;

public class SceneDemo2otheractions implements ApplicationListener{

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
		// There are several other actions, such as Int or FloatAction, that
		// allow you to modify a value over time, DelayAction for running an
		// action, um, after a delay, RepeatAction, for performing an Action
		// over and over. However, they all follow the same basic layout, so you
		// should be able to figure them out easily at this point. One last
		// thing to point out, all Actions are poolable. This means you can keep
		// a pool of actions instead of allocating them over and over. Here is
		// one such example of a Pool of MoveToActions:
		final MyActor myActor = new MyActor();
		Pool<MoveToAction> actionPool = new Pool<MoveToAction>(){
		    protected MoveToAction newObject(){
		        return new MoveToAction();
		    }
		};

		MoveToAction moveAction = actionPool.obtain();
		moveAction.setDuration(5f);
		moveAction.setPosition(300f, 0);

		myActor.addAction(moveAction);

		// You can also have an action simply be code. Consider the above
		// example, let’s say you wanted to log when the MoveToAction was
		// complete. You could accomplish this by doing the following:
//		myActor.addAction(sequence(moveAction,
//		        run(new Runnable(){
//		            @Override
//		            public void run() {
//		                Gdx.app.log("STATUS", "Action complete");
//		            }
//		            
//		})));
//		
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
