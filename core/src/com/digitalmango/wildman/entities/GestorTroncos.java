package com.digitalmango.wildman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.digitalmango.wildman.MainGame;
import com.digitalmango.wildman.screens.GameplayScreen;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Carlos Zamora on 11/02/2017.
 */

public class GestorTroncos {
    public int troncoAdestruir = 0;
    public int contador = 0;
    public int ultimoTipoTronco = 1;
    private int troncoRepetido;
    private int contadorTroncosBuenos = 0;
    private GameplayScreen gameplayScreen;
    private ArrayList<TroncoAbstracto> listaTroncos;

    private Sound picotazoSound;

    public GestorTroncos(GameplayScreen gameplayScreen){
        this.gameplayScreen = gameplayScreen;
        listaTroncos = gameplayScreen.listaTroncos;
        resetearTroncos();
        picotazoSound = gameplayScreen.mainGame.assetLoader.getAssetManager().get("sound/hit.ogg",Sound.class);
    }

    public void insertarTronco (){
        int random = 0;

        if(ultimoTipoTronco== 0 && contadorTroncosBuenos > randomizarEnRangoInt(0 ,1)){
            random = randomizarEnRangoInt(1,2);
            if(troncoRepetido > 1 && random == ultimoTipoTronco){
                random = randomizarEnRangoInt(1,2);
            }
            switch (random){
                case 1 : listaTroncos.add(contador, new TroncoRamaLeft(gameplayScreen.mainGame,contador));
                    ultimoTipoTronco = 1;
                    break;
                case 2 : listaTroncos.add(contador, new TroncoRamaRight(gameplayScreen.mainGame,contador));
                    ultimoTipoTronco = 2;
                    break;
            }
            contadorTroncosBuenos = 0;
        }else{
            listaTroncos.add(contador, new Tronco(gameplayScreen.mainGame,contador));
            contadorTroncosBuenos++;
            ultimoTipoTronco = 0;
        }

        if(ultimoTipoTronco == random){
            troncoRepetido++;
        }else{
            troncoRepetido = 0;
        }
        contador++;

    }

    public void destruirTronco (int direction){
        if(MainGame.EN_PARTIDA){
            listaTroncos.get(troncoAdestruir).direction = direction;
            gameplayScreen.pajaro.atacar(direction);
            listaTroncos.get(troncoAdestruir).isAlive = false;
            troncoAdestruir++;
            if (listaTroncos.get(troncoAdestruir).tipo == 1 && direction == 1){
                gameplayScreen.mainGame.gameOver();
                gameplayScreen.pajaro.death();
            }else if(listaTroncos.get(troncoAdestruir).tipo == 2 && direction == 0){
                gameplayScreen.mainGame.gameOver();
                gameplayScreen.pajaro.death();
            }else{
                picotazoSound.play(MainGame.SFX_VOL);
                MainGame.POINTS+=1;

                if(MainGame.TIEMPO <=8){
                    MainGame.TIEMPO+=0.5f;
                }
                gameplayScreen.mainGame.hud.actualizarPuntos(MainGame.POINTS);
            }
        }

    }

    public void updateTroncos(){
        if(listaTroncos.size() > 0 ){
            listaTroncos.get(troncoAdestruir).bajarTronco();

            for(TroncoAbstracto tronco : listaTroncos){
                if (tronco.id!= troncoAdestruir && tronco.isAlive){
                    tronco.sprite.setPosition(0,(listaTroncos.get(tronco.id - 1).sprite.getY() + 64));
                }
                tronco.descartarTronco();
            }

        }

    }

    public void render(Batch batch){
        for(int i = 5000; i >= 0; i--){
            listaTroncos.get(i).sprite.draw(batch);
        }
    }

    public int randomizarEnRangoInt (int min, int max){
        if (min >= max) {
            throw new IllegalArgumentException("Max debería ser mayor que Min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void resetearTroncos(){
            ultimoTipoTronco = 1;
            contadorTroncosBuenos = 0;
            contador = 0;
            troncoAdestruir = 0;
            listaTroncos.clear();
            for (int i = -1; i < 5001; i ++){
                insertarTronco();
            }

    }

}
