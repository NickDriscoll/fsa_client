package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ButtonInput extends Rectangle {
    private static Texture texture; //Texture containing all buttons
    private int index;  //Where to read from the texture
    private float x, y;

    static final int NUMBER_OF_BUTTONS = 5;

    public ButtonInput(float x, float y, int index) {
        //Initialize static texture if it doesn't already exist
        if (texture == null)
            texture = new Texture(Gdx.files.internal("buttons.png"));

        this.x = x;
        this.y = y;
        this.index = index;
        width = texture.getWidth() / NUMBER_OF_BUTTONS; //Only assign to WIDTH here please
        height = texture.getHeight();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, (int)width * index, 0, (int)width, (int)height);
    }



    public enum Buttons {
        Left(0),
        Right(1),
        Up(2),
        Down(3),
        B(4),
        A(5);

        private int value;

        Buttons(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }
    }
}
