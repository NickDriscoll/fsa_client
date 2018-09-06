package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class TouchProcessor implements InputProcessor {
    private List<Vector2> touches;
    private List<Vector2> touchups;
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
        System.out.println("Touch at " + screenX + ", " + screenY);

        //Touch coordinates are top left relative so we have to fix that
        touches.add(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Release at " + screenX + ", " + screenY);

        //Touch coordinates are top left relative so we have to fix that
        touchups.add(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
        return true;
    }

    public TouchProcessor() {
        touches = new ArrayList<Vector2>();
        touchups = new ArrayList<Vector2>();
    }

    public byte[] getBitmask(ButtonInput[] buttons) {
        byte[] bitmasks = {0, 0};

        //Collision check for touches
        for (int i = 0; i < buttons.length; i++) {
            boolean found = false;
            for(int j = 0; j < touches.size() && !found; j++) {
                if (buttons[i].contains(touches.get(j))) {
                    bitmasks[0] |= 1 << i;
                    found = true;
                }
            }
        }

        //Collision check for lifts
        for (int i = 0; i < buttons.length; i++) {
            boolean found = false;
            for (int j = 0; j < touchups.size() && !found; j++) {
                if (buttons[i].contains(touchups.get(j))) {
                    bitmasks[1] |= 1 << i;
                    found = true;
                }
            }
        }

        //Clear the lists
        touches.clear();
        touchups.clear();

        return bitmasks;
    }
}
