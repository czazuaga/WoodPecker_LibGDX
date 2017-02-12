package com.digitalmango.wildman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.digitalmango.wildman.MainGame;

/**
 * Created by Carlos Zamora on 06/02/2017.
 */
public abstract class TroncoAbstracto {

    public int id;
    public int direction;
    public Sprite sprite;
    private MainGame mainGame;
    public int tipo;

    //Lógica
    public boolean isAlive = true;

    public TroncoAbstracto(MainGame mainGame, int id){
        this.mainGame = mainGame;
        this.id = id;
    }

    public abstract void update(float delta);

    public void bajarTronco() {
        if(isAlive){

            if(sprite.getY() < mainGame.gameplayScreen.limiteBajadaArbol){
                sprite.setPosition(0,71);
            }

            if(sprite.getY() > mainGame.gameplayScreen.limiteBajadaArbol){
                sprite.translateY(-10);
            }
        }
    }

    public void descartarTronco() {
        if(!isAlive){
            if(sprite.getX() < MainGame.SCREEN_WIDTH && sprite.getX() > -sprite.getRegionWidth()){
                if(direction == 0){
                    sprite.translate(-10,1);
                }else{
                    sprite.translate(10,1);
                }
                rotate();
            }
        }
    }

    public void rotate() {
        sprite.setOrigin((sprite.getOriginX()),(sprite.getOriginY()));
            if(direction == 1){
                    sprite.rotate(-8f);
            }else if(direction == 0){
                    sprite.rotate(8f);
            }


    }

}
