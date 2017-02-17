package com.digitalmango.troubleintheforest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.digitalmango.troubleintheforest.entities.TapTap;

import java.util.ArrayList;

/**
 * Created by Carlos Zamora on 06/02/2017.
 */
public class GameplayScreen extends com.digitalmango.troubleintheforest.tools.BaseScreen {

    private SpriteBatch batch;
    public ArrayList<com.digitalmango.troubleintheforest.entities.TroncoAbstracto> listaTroncos;
    public ArrayList<com.digitalmango.troubleintheforest.entities.Nube> listaNubes;

    //Gráficos
    private Sprite skySprite;
    private Sprite arbustosSprite;
    private Sprite floorSprite;

    //Lógica
    public int limiteBajadaArbol = 71;
    private boolean juegoActivo = false;

    //Input management
    private InputMultiplexer inputMultiplexer;
    private com.digitalmango.troubleintheforest.tools.PlayerInputProcessor playerInputProcessor;

    public com.digitalmango.troubleintheforest.MainGame mainGame;
    public com.digitalmango.troubleintheforest.entities.GestorTroncos gestorTroncos;
    public com.digitalmango.troubleintheforest.entities.Pajaro pajaro;
    public com.digitalmango.troubleintheforest.entities.Barra barra;
    private com.digitalmango.troubleintheforest.entities.Flor flor;
    public TapTap tapTap;

    public GameplayScreen(Game game, com.digitalmango.troubleintheforest.MainGame mainGame) {
        super(mainGame);
        this.mainGame = mainGame;

        batch = mainGame.hud.batch;

        skySprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("sky")),0,0,240,400);
        arbustosSprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("arbustos")),0,0,240,400);
        floorSprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("floor")),0,0,240,400);


        listaNubes = new ArrayList<com.digitalmango.troubleintheforest.entities.Nube>();
        listaNubes.add(new com.digitalmango.troubleintheforest.entities.Nube(mainGame));
        listaNubes.add(new com.digitalmango.troubleintheforest.entities.Nube(mainGame));
        listaNubes.add(new com.digitalmango.troubleintheforest.entities.Nube(mainGame));
        listaNubes.add(new com.digitalmango.troubleintheforest.entities.Nube(mainGame));

        listaTroncos = new ArrayList<com.digitalmango.troubleintheforest.entities.TroncoAbstracto>();
        gestorTroncos = new com.digitalmango.troubleintheforest.entities.GestorTroncos(this);

        pajaro = new com.digitalmango.troubleintheforest.entities.Pajaro(mainGame);
        tapTap = new TapTap(mainGame);
        barra = new com.digitalmango.troubleintheforest.entities.Barra(mainGame);
        flor = new com.digitalmango.troubleintheforest.entities.Flor(mainGame);

        playerInputProcessor = new com.digitalmango.troubleintheforest.tools.PlayerInputProcessor();
        inputMultiplexer = new InputMultiplexer();

    }

    //Screen interface

    @Override
    public void show() {
        //Input Management
        inputMultiplexer.addProcessor(mainGame.hud.stage);
        inputMultiplexer.addProcessor(playerInputProcessor);
        Gdx.input.setInputProcessor(inputMultiplexer);

        juegoActivo = true;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(mainGame.hud.camera.combined);

        batch.begin();
        skySprite.draw(batch);

        for (com.digitalmango.troubleintheforest.entities.Nube nube : listaNubes) {
            if(juegoActivo && nube.isAlive){
                nube.update();
                nube.sprite.draw(batch);
            }
        }

        arbustosSprite.draw(batch);

        gestorTroncos.updateTroncos();
        gestorTroncos.render(batch);

        floorSprite.draw(batch);

        barra.update(delta);
        barra.sprite.draw(batch);
        pajaro.update(delta, batch);

        if(tapTap.isAlive){
            tapTap.update(delta,batch);
        }

        flor.update(delta,batch);

        batch.end();

        //Hud render
        batch.begin();
        mainGame.hud.renderHud(batch, delta);
        batch.end();

        update(delta);

    }

    private void update(float delta) {

    }

    @Override
    public void resize(int width, int height)
    {
        mainGame.hud.viewport.update(width, height);
        mainGame.hud.stage.getViewport().update(width, height);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void nuevaPartida(){
        pajaro.cambiarPosicion(1);
        gestorTroncos.resetearTroncos();

    }


}
