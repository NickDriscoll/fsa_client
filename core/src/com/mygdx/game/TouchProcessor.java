package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class TouchProcessor implements InputProcessor {
    private List<Vector2> touches;
    final int MAX_NUMBER_OF_TOUCHES = 10;

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
        System.out.println("Touch at " + screenX + ". " + screenY);
        touches.add(new Vector2(screenX, screenY));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Release at " + screenX + ", " + screenY);
        return true;
    }

    public TouchProcessor() {
        touches = new ArrayList<Vector2>();
    }

    public char getBitmask(ButtonInput[] buttons) {
        char bitmask = 0;

        //Collision check here
        for (int i = 0; i < buttons.length; i++) {
            boolean found = false;
            for(int j = 0; j < touches.size() && !found; j++) {
                if (buttons[i].contains(touches.get(j))) {
                    bitmask |= 1 << i;
                    found = true;
                    System.out.println("Collision at" + touches.get(j).x + ", " + touches.get(j).x);
                }
            }
        }

        //Clear the array of touches
        touches.clear();

        return bitmask;
    }
}
