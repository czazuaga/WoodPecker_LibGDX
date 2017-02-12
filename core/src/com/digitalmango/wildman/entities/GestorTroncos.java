package com.digitalmango.wildman.entities;

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
    private int contadorTroncosBuenos = 0;
    private GameplayScreen gameplayScreen;
    private ArrayList<TroncoAbstracto> listaTroncos;

    public GestorTroncos(GameplayScreen gameplayScreen){
        this.gameplayScreen = gameplayScreen;
        listaTroncos = gameplayScreen.listaTroncos;

        for (int i = -1; i < 5001; i ++){
            insertarTronco();
        }

    }

    public void insertarTronco (){
        int r;
        if (contadorTroncosBuenos < 4){
            r = randomizarEnRangoInt(0,2);
        }else{
            r = randomizarEnRangoInt(1,2);
            contadorTroncosBuenos = 0;
        }


        if(ultimoTipoTronco!= 1 && ultimoTipoTronco!=2){
            switch (r){
                case 0 : listaTroncos.add(contador, new Tronco(gameplayScreen.mainGame,contador));
                    contadorTroncosBuenos++;
                    break;
                case 1 : listaTroncos.add(contador, new TroncoRamaLeft(gameplayScreen.mainGame,contador));
                    break;
                case 2 : listaTroncos.add(contador, new TroncoRamaRight(gameplayScreen.mainGame,contador));
                    break;
            }
        }else{
            listaTroncos.add(contador, new Tronco(gameplayScreen.mainGame,contador));
            contadorTroncosBuenos++;
        }


        ultimoTipoTronco = r;
        contador++;

    }

    public void destruirTronco (int direction){
        listaTroncos.get(troncoAdestruir).direction = direction;
        gameplayScreen.pajaro.atacar(direction);
        listaTroncos.get(troncoAdestruir).isAlive = false;
        troncoAdestruir++;
        if (listaTroncos.get(troncoAdestruir).tipo == 1 && direction == 1){
            gameplayScreen.mainGame.gameOver();
        }else if(listaTroncos.get(troncoAdestruir).tipo == 2 && direction == 0){
            gameplayScreen.mainGame.gameOver();
        }else{
            MainGame.POINTS+=1;
            gameplayScreen.mainGame.hud.actualizarPuntos(MainGame.POINTS);
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

}
