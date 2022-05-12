package com.mineseeker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MineSeeker extends ApplicationAdapter {
    public static int WIDTH = 600;
    public static int HEIGHT = 800;
    //    SpriteBatch batch;
//    Texture img;
    Tile tileTest;
    GameBoard gameBoard;
    BitmapFont font;
    SpriteBatch batch;
    private OrthographicCamera camera;


    @Override
    public void create() {
        //batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.setToOrtho(true, WIDTH, HEIGHT);
        font = new BitmapFont(true);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        //tileTest = new Tile(64, 64, 0, 0, new Texture("BombTile.PNG"), camera);
        gameBoard = new GameBoard(18, 18, 23, camera);
        gameBoard.create();

    }

    @Override
    public void render() {
        ScreenUtils.clear(.5f, .5f, .5f, 1);
        camera.update();
        //tileTest.draw();
//      batch.begin();
//      batch.draw(img, 0, 0);
        //draw background, objects, etc.

        batch.begin();
        gameBoard.render();
        font.draw(batch, Gdx.input.getX() + " " + Gdx.input.getY(), 64, HEIGHT-64);
        batch.end();
//        batch.end();
    }

    @Override
    public void dispose() {
//        batch.dispose();
//        img.dispose();
    }
}
