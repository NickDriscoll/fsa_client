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
    final int scale = 5;

    public ButtonInput(float x, float y, int index) {
        //Initialize static texture if it doesn't already exist
        if (texture == null)
            texture = new Texture(Gdx.files.internal("buttons.png"));

        this.x = x;
        this.y = y;
        this.index = index;
        width = texture.getWidth() / NUMBER_OF_BUTTONS;
        height = texture.getHeight();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, (int)width * index, 0, (int)width, (int)height);
    }

    public static void init_buttons(ButtonInput[] buttons) {
        buttons[Buttons.Left.getValue()] = new ButtonInput(0f, 0, Buttons.Left.getValue());
        buttons[Buttons.Down.getValue()] = new ButtonInput(texture.getWidth() / NUMBER_OF_BUTTONS, 0, Buttons.Down.getValue());
        buttons[Buttons.Right.getValue()] = new ButtonInput(texture.getWidth() / NUMBER_OF_BUTTONS * 2, 0, Buttons.Right.getValue());
        buttons[Buttons.Up.getValue()] = new ButtonInput(texture.getWidth() / NUMBER_OF_BUTTONS, texture.getHeight(), Buttons.Up.getValue());
        buttons[Buttons.A.getValue()] = new ButtonInput(Gdx.graphics.getWidth() - texture.getWidth() / NUMBER_OF_BUTTONS, 0, Buttons.B.getValue());
        buttons[Buttons.B.getValue()] = new ButtonInput(Gdx.graphics.getWidth() - texture.getWidth() * 2 / NUMBER_OF_BUTTONS, 0, Buttons.B.getValue());
    }

    public enum Buttons {
        Left(0),
        Down(1),
        Up(2),
        Right(3),
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
