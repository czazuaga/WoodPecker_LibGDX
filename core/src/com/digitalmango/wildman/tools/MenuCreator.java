package com.digitalmango.wildman.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.digitalmango.wildman.MainGame;

/**
 * Created by Carlos Zamora on 12/02/2017.
 */

public class MenuCreator {

    private Hud hud;

    private Button rankingButton, playButton;
    public Label bestLabel, scoreLabel;
    private Table bestLabelTable, scoreLabelTable;
    private Image image;

    private int limiteImage = 118;
    private boolean cargarGameover = false;

    public boolean contarGameover = false;
    private float gameOverRegresionCounter = 0;


    public MenuCreator(Hud hud){
        this.hud = hud;

        rankingButton = new Button(hud.uiSkin.getDrawable("ranking_button"));
        playButton = new Button(hud.uiSkin.getDrawable("play_button"));
        bestLabel = new Label("",hud.ls);
        scoreLabel = new Label("",hud.ls);
        image = new Image(hud.uiSkin,"title_panel");

        bestLabelTable = new Table();
        bestLabelTable.setFillParent(false);
        bestLabelTable.setBounds(0, 161, MainGame.SCREEN_WIDTH, 100);

        scoreLabelTable = new Table();
        scoreLabelTable.setFillParent(false);
        scoreLabelTable.setBounds(0, 106, MainGame.SCREEN_WIDTH, 100);

        image.setBounds(18, 118, 199, 271);
        rankingButton.setBounds(28, 11, 84, 50);
        playButton.setBounds(129, 11, 84, 50);

        bestLabelTable.add(bestLabel).expand().align(Align.center);
        scoreLabelTable.add(scoreLabel).expand().align(Align.center);

        hud.stage.addActor(image);
        hud.stage.addActor(bestLabelTable);
        hud.stage.addActor(scoreLabelTable);
        hud.stage.addActor(rankingButton);
        hud.stage.addActor(playButton);

        //Listeners

        rankingButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                Gdx.app.log("Ranking", "");
                return true;
            }
        });


        playButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                Gdx.app.log("Play", "");
                ocultarMenus();
                return true;
            }

        });

        mostrarMainMenu();

    }

    public void mostrarGameOver(){
        image.setDrawable(hud.uiSkin,"gameover_panel");

        image.setPosition(image.getX(), image.getX() + MainGame.SCREEN_HEIGHT);
        scoreLabelTable.setPosition(scoreLabelTable.getX(), scoreLabelTable.getY() + MainGame.SCREEN_HEIGHT);
        bestLabelTable.setPosition(bestLabelTable.getX(), bestLabelTable.getY() + MainGame.SCREEN_HEIGHT);

        actualizarLabels();
        image.setVisible(true);
        bestLabelTable.setVisible(true);
        scoreLabelTable.setVisible(true);

        cargarGameover = true;

    }

    public void ocultarMenus(){
        image.setVisible(false);
        bestLabelTable.setVisible(false);
        scoreLabelTable.setVisible(false);
        rankingButton.setVisible(false);
        playButton.setVisible(false);

        MainGame.NUMERO_PARTIDAS++;
        hud.mainGame.nuevaPartida();

        MainGame.MOSTRAR_BARRA = true;
    }

    public void actualizarLabels(){
        bestLabel.setText(MainGame.BEST + "");
        scoreLabel.setText(MainGame.POINTS + "");
    }

    public void animarUI(float delta){
        if(image.getY() > limiteImage){
            image.moveBy(0, -10);
        }else{
            if(cargarGameover){
                hud.mainGame.reproducirMusica(1);
                rankingButton.setVisible(true);
                playButton.setVisible(true);
                cargarGameover = false;
                MainGame.MOSTRAR_BARRA = false;
            }
        }

        bestLabelTable.setPosition(bestLabelTable.getX(), image.getY() + 44);
        scoreLabelTable.setPosition(scoreLabelTable.getX(), image.getY() - 12);


        regresionGameover(delta);

    }



    public void mostrarMainMenu(){
        image.setDrawable(hud.uiSkin,"title_panel");

        rankingButton.setVisible(true);
        playButton.setVisible(true);
    }

    private void regresionGameover(float delta) {
        if(contarGameover){
            gameOverRegresionCounter = gameOverRegresionCounter + 1 * delta;
            if(gameOverRegresionCounter > 1.0f){
                mostrarGameOver();
                contarGameover = false;
            }
        }else{
            gameOverRegresionCounter = 0;
        }
    }


}
