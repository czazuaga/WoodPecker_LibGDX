package com.digitalmango.wildman.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.digitalmango.wildman.MainGame;

/**
 * Created by Carlos Zamora on 06/02/2017.
 */
public class Hud implements Disposable {

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public StretchViewport viewport;

    //UI variables Stage stage;
    public Stage stage;
    private Button aButton, bButton;
    private Skin uiSkin;

    private Label points;
    private MainGame mainGame;


    public Hud(MainGame mainGame){
        this.mainGame = mainGame;
        batch = new SpriteBatch();

        camera = new OrthographicCamera(MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT);
        camera.setToOrtho(false, MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT);

        viewport = new StretchViewport(MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT, camera);
        viewport.apply();

        stage = new Stage();
        stage.setViewport(viewport);

        uiSkin = new Skin();
        uiSkin.addRegions(mainGame.getAssetAtlas());

        aButton = new Button(uiSkin.getDrawable("buttons"));
        bButton = new Button(uiSkin.getDrawable("buttons"));


        Label.LabelStyle ls = new Label.LabelStyle(mainGame.pixelFont, Color.WHITE);
        points = new Label("",ls);
        actualizarPuntos(MainGame.POINTS);

        createTables();
    }



    public void renderHud(Batch batch){
        stage.draw();

    }


    @Override
    public void dispose() {
        batch.dispose();
        uiSkin.dispose();
        stage.dispose();
    }

    private void createTables() {

        Table pointsTable = new Table();
        pointsTable.setFillParent(true);
        pointsTable.pad(15f);
        pointsTable.add(points).expand().align(Align.top);


        Table fullTableLeft = new Table();
        fullTableLeft.add(aButton).expand().align(Align.center).fill();

        Table fullTableRight = new Table();
        fullTableRight.add(bButton).expand().align(Align.center).size(MainGame.SCREEN_WIDTH/2,MainGame.SCREEN_HEIGHT);

        Table tableFullScreen = new Table();
        tableFullScreen.setFillParent(true);
        tableFullScreen.add(fullTableLeft).expand().align(Align.left).size(MainGame.SCREEN_WIDTH/2,MainGame.SCREEN_HEIGHT);
        tableFullScreen.add(fullTableRight).expand().align(Align.right).size(MainGame.SCREEN_WIDTH/2,MainGame.SCREEN_HEIGHT);


        stage.addActor(pointsTable);
        stage.addActor(tableFullScreen);

        //Listeners

        fullTableRight.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                mainGame.gameplayScreen.gestorTroncos.destruirTronco(0);
                return true;
            }
        });


        fullTableLeft.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                mainGame.gameplayScreen.gestorTroncos.destruirTronco(1);
                return true;
            }

        });
    }

    public void actualizarPuntos(int puntos){
            points.setText(puntos + "");
    }

}