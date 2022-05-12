package com.mineseeker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.event.MouseEvent;

public class Tile {
    private static int TEX_SIZE = 128;
    private boolean flagged;
    private boolean revealed;
    private boolean isBomb;
    private int bombsAroundCount;
    Texture overlayTex;
    Texture revealedTex;
    private SpriteBatch tileSprite;
    private Rectangle tile;
    Sprite testSprite;

    public Tile(float height, float width, float x, float y, Texture tex, OrthographicCamera camera) {
        tile = new Rectangle(x, y, width, height);
        overlayTex = new Texture("DefaultTile.PNG");
        revealedTex = tex;
        testSprite = new Sprite(overlayTex);
        tileSprite = new SpriteBatch();
        testSprite.setOriginBasedPosition(tile.getX(), tile.getY());
        testSprite.setOrigin(TEX_SIZE, TEX_SIZE);
        tileSprite.setProjectionMatrix(camera.combined);
        testSprite.flip(false, true);
        initTex();
    }

    private void initTex() {

    }

    public void draw() {
        testSprite.setScale(tile.width * 2 / TEX_SIZE);
//        testSprite.setX(tile.x);
//        testSprite.setY(tile.y);

        tileSprite.begin();

        testSprite.draw(tileSprite);
        if (revealed) {
            testSprite.setTexture(revealedTex);
        } else
            testSprite.setTexture(overlayTex);
        //tileSprite.draw(overlayTex, tile.x, tile.y);

        tileSprite.end();
        checkClicked();
    }

    public void checkClicked() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)
                && (Gdx.input.getX() >= tile.getX() && Gdx.input.getX() <= tile.getX() + tile.getWidth()
                && Gdx.input.getY() >= tile.getY() && Gdx.input.getY() <= tile.getY() + tile.getHeight())
        ) {
            revealed = true;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
            revealed = false;
    }

    public float getHeight() {
        return tile.getHeight();
    }

    public float getWidth() {
        return tile.getWidth();
    }

}
