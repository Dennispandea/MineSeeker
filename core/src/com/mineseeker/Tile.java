package com.mineseeker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tile {
    private static int TEX_SIZE = 128;
    private boolean flagged;
    private boolean revealed;
    private boolean isBomb;
    private int bombsAroundCount;
    OrthographicCamera camera;
    Texture overlayTex;
    Texture flaggedTex;
    Texture revealedTex;
    private SpriteBatch tileSprite;
    private Rectangle tile;
    Sprite testSprite;

    public Tile(float height, float width, float x, float y, Texture tex, OrthographicCamera camera) {
        tile = new Rectangle(x, y, width, height);

        overlayTex = new Texture("DefaultTile.PNG");
        flaggedTex = new Texture("FlagTile.PNG");
        revealedTex = tex;
        testSprite = new Sprite(overlayTex);
        tileSprite = new SpriteBatch();
        testSprite.setPosition(tile.getX(),tile.getY());
        testSprite.setOrigin(0, 0);
        tileSprite.setProjectionMatrix(camera.combined);
        testSprite.flip(false, true);
        this.camera=camera;
    }

    public void debugTile() {
        System.out.println("Rectangle X,Y "+tile.getX()+" "+tile.getY());
        System.out.println("Sprite X,Y "+testSprite.getX()+" "+testSprite.getY());
    }

    public void draw() {
        testSprite.setScale(tile.width / TEX_SIZE);
//        testSprite.setX(tile.x);
//        testSprite.setY(tile.y);

        tileSprite.begin();

        testSprite.draw(tileSprite);
        if (revealed) {
            testSprite.setTexture(revealedTex);
        } else if (flagged) {
            testSprite.setTexture(flaggedTex);
        }
        else
            testSprite.setTexture(overlayTex);
        //tileSprite.draw(overlayTex, tile.x, tile.y);

        tileSprite.end();
        checkClicked();

    }

    public void checkClicked() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                (Gdx.input.getX() >= tile.getX() &&
                        Gdx.input.getX() <= tile.getX() + tile.getWidth()
                        && Gdx.input.getY() >= tile.getY() &&
                        Gdx.input.getY() <= tile.getY() + tile.getHeight())
        ) {
            revealed = true;
            debugTile();
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) &&
                (Gdx.input.getX() >= tile.getX() &&
                        Gdx.input.getX() <= tile.getX() + tile.getWidth()
                        && Gdx.input.getY() >= tile.getY() &&
                        Gdx.input.getY() <= tile.getY() + tile.getHeight())
        ) {
            flagged = true;
        }
    }

    public float getHeight() {
        return tile.getHeight();
    }

    public float getWidth() {
        return tile.getWidth();
    }

//    public boolean getIsBomb() {
//        return tile.isBomb;
//    }
}
