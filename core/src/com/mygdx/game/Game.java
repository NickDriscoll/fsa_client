package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.net.Socket;

public class Game extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	BitmapFont font;
	Texture controls;
	Socket socket;
	boolean connected;
	boolean touched;
	ButtonInput[] buttons;

	char input_bitmask;

	const int NUMBER_OF_BUTTONS = 6;

	@Override
	public boolean keyDown(int a) {
		return false;
	}

	@Override
	public boolean keyUp(int a) {
		return false;
	}

	@Override
	public boolean keyTyped(char c) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touched = true;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touched = false;
		return true;
	}

	private boolean connect_to_server() {
		//Try all ip addresses looking for one where port 1337 is open
		String addr_header = "192.168.1.";
		int last_number = 2;

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
		font = new BitmapFont();
		font.getData().setScale(5f);
		controls = new Texture(Gdx.files.internal("touch_buttons_0.png"));

		Gdx.input.setInputProcessor(this);

		input_bitmask = 0;
		touched = false;

		//Initialize the six buttons
		buttons = new ButtonInput[NUMBER_OF_BUTTONS];
		buttons[0] = new ButtonInput(0, Gdx.graphics.getHeight() * (5.0 / 6.0));

		connected = connect_to_server();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Get touchscreen state

		//Update

		//Draw
		batch.begin();
		batch.draw(controls, 0, 0, Gdx.graphics.getWidth(), controls.getHeight() * (Gdx.graphics.getWidth() / controls.getWidth()));
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	enum Buttons {
		Left,
		Right,
		Up,
		Down,
		B,
		A
	}
}
