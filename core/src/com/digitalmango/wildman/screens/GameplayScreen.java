package com.digitalmango.wildman.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.digitalmango.wildman.MainGame;
import com.digitalmango.wildman.entities.GestorTroncos;
import com.digitalmango.wildman.entities.Nube;
import com.digitalmango.wildman.entities.Pajaro;
import com.digitalmango.wildman.tools.BaseScreen;
import com.digitalmango.wildman.tools.PlayerInputProcessor;
import java.util.ArrayList;
import com.digitalmango.wildman.entities.TroncoAbstracto;

/**
 * Created by Carlos Zamora on 06/02/2017.
 */
public class GameplayScreen extends BaseScreen {

    private SpriteBatch batch;
    public ArrayList<TroncoAbstracto> listaTroncos;
    public ArrayList<Nube> listaNubes;

    //Gráficos
    private Sprite skySprite;
    private Sprite arbustosSprite;
    private Sprite floorSprite;

    //Lógica
    public int limiteBajadaArbol = 71;
    private boolean juegoActivo = false;

    //Input management
    private InputMultiplexer inputMultiplexer;
    private PlayerInputProcessor playerInputProcessor;

    public MainGame mainGame;
    public GestorTroncos gestorTroncos;
    public Pajaro pajaro;

    public GameplayScreen(Game game, MainGame mainGame) {
        super(mainGame);
        this.mainGame = mainGame;

        batch = mainGame.hud.batch;

        skySprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("sky")),0,0,240,400);
        arbustosSprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("arbustos")),0,0,240,400);
        floorSprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("floor")),0,0,240,400);


        listaNubes = new ArrayList<Nube>();
        listaNubes.add(new Nube(mainGame));
        listaNubes.add(new Nube(mainGame));
        listaNubes.add(new Nube(mainGame));
        listaNubes.add(new Nube(mainGame));

        listaTroncos = new ArrayList<TroncoAbstracto>();
        gestorTroncos = new GestorTroncos(this);

        pajaro = new Pajaro(mainGame);

        playerInputProcessor = new PlayerInputProcessor();
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

        for (Nube nube : listaNubes) {
            if(juegoActivo && nube.isAlive){
                //nube.sprite.draw(batch);
                //nube.update();
            }
        }

        arbustosSprite.draw(batch);

        gestorTroncos.updateTroncos();
        gestorTroncos.render(batch);

        floorSprite.draw(batch);

        pajaro.update(delta, batch);

        batch.end();

        //Hud render
        batch.begin();
        mainGame.hud.renderHud(batch);
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



}
