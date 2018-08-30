package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

public class TouchProcessor implements InputProcessor {
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
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    public TouchProcessor() {

    }

    public char getBitmask() {
        return 0;
    }
}
