package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

public class TouchProcessor implements InputProcessor {
    private class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Point[] touches;
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
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Release at " + screenX + ", " + screenY);
        return true;
    }

    public TouchProcessor() {
        touches = new Point[MAX_NUMBER_OF_TOUCHES];
    }

    public char getBitmask() {
        return 0;
    }
}
