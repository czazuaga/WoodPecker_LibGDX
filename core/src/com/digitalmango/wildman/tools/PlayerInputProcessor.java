package com.digitalmango.wildman.tools;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Carlos Zamora on 24/09/2016.
 */
public class PlayerInputProcessor implements InputProcessor {


    public PlayerInputProcessor() {

    }

    @Override
    public boolean keyDown(int keycode) {


            if (keycode == Input.Keys.S) {
                Gdx.app.log("Puso S","");
            }

            if (keycode == Input.Keys.A) {
                Gdx.app.log("Puso A","");
            }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}

