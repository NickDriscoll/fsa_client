package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import sun.rmi.runtime.Log;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Socket socket;
	boolean connected;
	ButtonInput[] buttons;
	TouchProcessor touch_processor;
	PrintWriter to_server;

	char input_bitmask;

	final int NUMBER_OF_BUTTONS = 6;

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

		touch_processor = new TouchProcessor();
		Gdx.input.setInputProcessor(touch_processor);

		input_bitmask = 0;

		//Initialize the six buttons
		buttons = new ButtonInput[NUMBER_OF_BUTTONS];
        ButtonInput.init_buttons(buttons);

		//connected = connect_to_server();

		/*try {
			to_server = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Get touchscreen state
		input_bitmask = touch_processor.getBitmask();

		//Update

		//Send bitmask to server
		//to_server.write(input_bitmask);

		//Get game state from server


		//Draw
		batch.begin();

		for (ButtonInput button : buttons) {
			if (button != null)
				button.draw(batch);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
