package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.net.Socket;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	Socket socket;

	private boolean connect_to_server() {
		//Try all ip addresses looking for one where port 1337 is open
		String addr_header = "192.168.1.";
		int last_number = 0;

		while (last_number < 256)
		{
			try {
				socket = new Socket(addr_header + last_number, 1337);
				return true;
			} catch (IOException e) {
				socket = null;
			}
			last_number++;
		}
		return false;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		font.getData().setScale(5f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (connect_to_server())
			font.draw(batch, "Connected to server!",Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		else
			font.draw(batch, "Unable to find server.",Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
