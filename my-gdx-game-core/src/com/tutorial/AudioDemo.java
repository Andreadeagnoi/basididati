package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class AudioDemo implements ApplicationListener {

	Sound wavSound;
	Long id;

	@Override
	public void create() {
		wavSound = Gdx.audio.newSound(Gdx.files.internal("data/nightmare.wav"));
		// Sound oggSound =
		// Gdx.audio.newSound(Gdx.files.internal("data/ogg.ogg"));
		// Sound mp3Sound =
		// Gdx.audio.newSound(Gdx.files.internal("data/mp3.mp3"));
		// wavSound.play(0.5f);
		// the loop and play methods return an id
		id = wavSound.loop();
		// alter the pitch
		wavSound.setPitch(id, 2f);
		// alter the volume
		wavSound.setVolume(id, 1.0f);
		// pan the sound
		wavSound.setPan(id, 1f, 1f);
		// stop the sound after 5 second
		Timer.schedule(new Task() {
			@Override
			public void run() {
				wavSound.stop(id);
			}
		}, 5.0f);
		// You can also stream music or longer duration sound effect to optimize
		// memory
		Music wavMusic = Gdx.audio.newMusic(Gdx.files
				.internal("data/screams.wav"));
		// there is no id, so this means you can play multiple instances of a
		// single Music file at once. Second, there are a series of VCR style
		// control options. Here is a rather impractical example of playing a
		// Music file
		wavMusic.play();
		wavMusic.setVolume(1.0f);
		wavMusic.pause();
		wavMusic.stop();
		wavMusic.play();
		// As you can see from the log() call, you can get the current playback
		// position of the Music object by calling getPosition(). This returns
		// the current elapsed time into the song in seconds
		Gdx.app.log("SONG", Float.toString(wavMusic.getPosition()));
		// LibGDX also has the ability to work at a lower level using raw PCM
		// data. Basically this is a short (16bit) or float (32bit) array of
		// values composing the wavform to play back. This allows you to create
		// audio effects programmatically. You can also record audio into PCM
		// form.
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {

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
		wavSound.dispose();
		// oggSound.dispose();
		// mp3Sound.dispose();
	}

}
