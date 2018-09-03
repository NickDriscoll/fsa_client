package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.io.OutputStream;
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
	OutputStream to_server;

	char input_bitmask;

	final int NUMBER_OF_BUTTONS = 6;

	private boolean connect_to_server() {
	    try {
            socket = new Socket("192.168.1.188", 1337);
        } catch (IOException e) {
	        return false;
        }
        return true;
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

		connected = connect_to_server();
		try {
            to_server = socket.getOutputStream();
        } catch (IOException e) {
		    System.out.println(e.getMessage());
        }
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Get touchscreen state
		input_bitmask = touch_processor.getBitmask(buttons);

		//Update

		//Send bitmask to server
        try {
            to_server.write(input_bitmask);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
