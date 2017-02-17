package com.digitalmango.troubleintheforest.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.digitalmango.troubleintheforest.MainGame;

/**
 * Created by Carlos Zamora on 12/02/2017.
 */

public class MenuCreator {

    private Hud hud;

    private Button gamesButton, playButton, pauseButton;
    private Button rankingButton, logrosButton, rateButton, backButton, recordRateButton;
    public Label bestLabel, scoreLabel;
    private Table bestLabelTable, scoreLabelTable, gamesTable;
    private Image image, imagePausa;

    private int limiteImage = 118;
    private boolean cargarGameover = false;
    private boolean moverDerecha = false;
    private int limiteDerecha = 18 + MainGame.SCREEN_WIDTH;

    public boolean contarGameover = false;
    private float gameOverRegresionCounter = 0;

    private Sound pauseSound;
    private Sound sweepSound;


    public MenuCreator(Hud hud){
        this.hud = hud;

        gamesButton = new Button(hud.uiSkin.getDrawable("games_button"));
        playButton = new Button(hud.uiSkin.getDrawable("play_button"));
        pauseButton= new Button(hud.uiSkin.getDrawable("pause_button"));

        rankingButton = new Button(hud.uiSkin.getDrawable("ranking_button"));
        logrosButton = new Button(hud.uiSkin.getDrawable("achievement_button"));
        rateButton = new Button(hud.uiSkin.getDrawable("rate_button"));
        recordRateButton = new Button(hud.uiSkin.getDrawable("rate_button"));
        backButton = new Button(hud.uiSkin.getDrawable("back_button"));

        bestLabel = new Label("",hud.ls);
        scoreLabel = new Label("",hud.ls);
        image = new Image(hud.uiSkin,"title_panel");
        imagePausa = new Image(hud.uiSkin,"pause");

        bestLabelTable = new Table();
        bestLabelTable.setFillParent(false);
        bestLabelTable.setBounds(0, 161, MainGame.SCREEN_WIDTH, 100);

        scoreLabelTable = new Table();
        scoreLabelTable.setFillParent(false);
        scoreLabelTable.setBounds(0, 106, MainGame.SCREEN_WIDTH, 100);

        gamesTable = new Table();
        gamesTable.setFillParent(false);
        gamesTable.setBounds(0, 0, 199, 188);
        gamesTable.setBackground(hud.uiSkin.getDrawable("services_panel"));
        gamesTable.setVisible(false);

        gamesTable.addActor(rankingButton);
        rankingButton.setPosition(gamesTable.getX() + 56, gamesTable.getY() + 99);
        rateButton.setPosition(gamesTable.getX() + 102, gamesTable.getY() + 44);
        recordRateButton.setPosition(gamesTable.getX() + 58, gamesTable.getY() + 156);
        recordRateButton.setVisible(false);
        logrosButton.setPosition(gamesTable.getX() + 12, gamesTable.getY() + 44);
        backButton.setVisible(false);
        backButton.setPosition(28, 11);
        gamesTable.addActor(logrosButton);
        gamesTable.addActor(rateButton);
        gamesTable.addActor(backButton);

        image.setBounds(18, 118, 199, 271);
        image.setPosition(image.getX(), image.getX() + MainGame.SCREEN_HEIGHT);
        imagePausa.setBounds(0, 0, 240, 400);
        imagePausa.setVisible(false);
        pauseButton.setVisible(false);
        gamesButton.setBounds(28, 11, 84, 50);
        playButton.setBounds(129, 11, 84, 50);
        pauseButton.setBounds(203, 363, 32, 32);

        bestLabelTable.add(bestLabel).expand().align(Align.center);
        scoreLabelTable.add(scoreLabel).expand().align(Align.center);

        hud.stage.addActor(image);
        hud.stage.addActor(gamesTable);
        hud.stage.addActor(recordRateButton);
        hud.stage.addActor(bestLabelTable);
        hud.stage.addActor(scoreLabelTable);
        hud.stage.addActor(gamesButton);
        hud.stage.addActor(playButton);
        hud.stage.addActor(imagePausa);
        hud.stage.addActor(pauseButton);
        hud.stage.addActor(backButton);


        pauseSound = hud.mainGame.assetLoader.getAssetManager().get("sound/rise.ogg",Sound.class);
        sweepSound = hud.mainGame.assetLoader.getAssetManager().get("sound/sweep.ogg",Sound.class);


        //Listeners

        gamesButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                Gdx.app.log("Games", "");
                mostrarGamesMenu();
                return true;
            }
        });

        backButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                ocultarGamesMenu();
                return true;
            }
        });

        logrosButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                if (!MainGame.IN_DESKTOP){
                    MainGame.playServices.showAchievement();
                }

                return true;
            }
        });

        rankingButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                if (!MainGame.IN_DESKTOP){
                    MainGame.playServices.showScore();

                }

                return true;
            }
        });

        rateButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                if (!MainGame.IN_DESKTOP){
                    MainGame.playServices.rateGame();
                }

                return true;
            }
        });

        recordRateButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                if (!MainGame.IN_DESKTOP){
                    MainGame.playServices.rateGame();
                }

                return true;
            }
        });


        playButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                Gdx.app.log("Play", "");
                mostrarTap();
                iniciarJuego();
                return true;
            }

        });

        pauseButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y,
                                      int pointer, int button) {
                Gdx.app.log("Pause", "");
                    pausar();
                return true;
            }
        });

        mostrarMainMenu();

    }



    private void pausar() {
        if(!MainGame.EN_PAUSA){
            pauseButton.setVisible(true);
            MainGame.EN_PAUSA = true;
            imagePausa.setVisible(true);
            pauseSound.play(MainGame.SFX_VOL);

            if(hud.mainGame.levelMusic.isPlaying()){
                hud.mainGame.levelMusic.pause();
            }

            if(hud.mainGame.gameOverMusic.isPlaying()){
                hud.mainGame.gameOverMusic.pause();
            }

        }else{
            MainGame.EN_PAUSA = false;
            imagePausa.setVisible(false);
            hud.mainGame.levelMusic.play();

            if(hud.mainGame.gameOverMusic.isPlaying()){
                hud.mainGame.gameOverMusic.pause();
            }
        }
    }

    public void mostrarGameOver(){

        hud.points.setVisible(false);
        hud.mainGame.setNewScore(MainGame.POINTS);

        if(MainGame.NEW_RECORD){
            image.setDrawable(hud.uiSkin,"record_panel");
            recordRateButton.setVisible(true);
            actualizarLabels(true);
            MainGame.NEW_RECORD = false;
        }else{
            image.setDrawable(hud.uiSkin,"gameover_panel");
            actualizarLabels(false);
        }
        image.setPosition(image.getX(), image.getX() + MainGame.SCREEN_HEIGHT);
        scoreLabelTable.setPosition(scoreLabelTable.getX(), scoreLabelTable.getY() + MainGame.SCREEN_HEIGHT);
        bestLabelTable.setPosition(bestLabelTable.getX(), bestLabelTable.getY() + MainGame.SCREEN_HEIGHT);


        image.setVisible(true);
        bestLabelTable.setVisible(true);
        scoreLabelTable.setVisible(true);

        if(!MainGame.EN_PAUSA){
            pauseButton.setVisible(false);
        }


        cargarGameover = true;

    }

    public void ocultarMenus(){
        image.setVisible(false);
        recordRateButton.setVisible(false);
        hud.points.setVisible(true);
        hud.points.setText(""+0);
        bestLabelTable.setVisible(false);
        scoreLabelTable.setVisible(false);
        gamesButton.setVisible(false);
        playButton.setVisible(false);
        pauseButton.setVisible(true);
    }

    public void actualizarLabels(boolean newRecord){
        if(newRecord){
            bestLabel.setText(MainGame.POINTS + "");
            scoreLabel.setText(MainGame.POINTS + "");
        }else{
            bestLabel.setText(MainGame.BEST_POINTS + "");
            scoreLabel.setText(MainGame.POINTS + "");
        }
    }

    public void animarUI(float delta){
        if(image.getY() > limiteImage){
            image.moveBy(0, -10);
        }else{
            if(cargarGameover){
                hud.mainGame.reproducirMusica(1);
                gamesButton.setVisible(true);
                playButton.setVisible(true);
                cargarGameover = false;
                MainGame.MOSTRAR_BARRA = false;
            }
        }

        regresionGameover(delta);

        if(moverDerecha){
            if(image.getX() < limiteDerecha){
                image.moveBy(20, 0);
            }
        }else{
            if(image.getX() > 18){
                image.moveBy(-20, 0);
            }else{
                gamesTable.setVisible(true);
            }
        }

        gamesTable.setPosition((image.getX() - (18 * 2)) - gamesTable.getWidth() , 112);
        bestLabelTable.setPosition(image.getX() - 18, image.getY() + 44);
        scoreLabelTable.setPosition(image.getX() - 18, image.getY() - 12);
        //rateButton.setPosition(gamesTable.getX() + 102, gamesTable.getY() + 44);
        recordRateButton.setPosition(image.getX() + 58, image.getY() + 156);

    }



    public void mostrarMainMenu(){
        image.setDrawable(hud.uiSkin,"title_panel");

        gamesButton.setVisible(true);
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

    public void mostrarGamesMenu(){
        gamesTable.setVisible(true);
        backButton.setVisible(true);
        gamesButton.setVisible(false);
        playButton.setVisible(false);
        sweepSound.play(MainGame.SFX_VOL);
        moverDerecha = true;
    }

    public void ocultarGamesMenu() {
        backButton.setVisible(false);
        gamesButton.setVisible(true);
        playButton.setVisible(true);
        sweepSound.play(MainGame.SFX_VOL);
        moverDerecha = false;
    }

    public void mostrarTap(){
        ocultarMenus();
        hud.mainGame.gameplayScreen.tapTap.isAlive = true;
    }

    public void iniciarJuego(){
        MainGame.NUMERO_PARTIDAS++;
        hud.mainGame.nuevaPartida();
        MainGame.MOSTRAR_BARRA = true;
    }




}
