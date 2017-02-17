package com.digitalmango.troubleintheforest.tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by Carlos Zamora on 13/09/2016.
 */
public abstract class BaseScreen implements Screen {

    protected Game mainGame;

    public BaseScreen(Game mainGame) {
        this.mainGame = mainGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
