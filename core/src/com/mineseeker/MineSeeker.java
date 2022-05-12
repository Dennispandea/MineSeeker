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
    BitmapFont debugFont;
    BitmapFont flagsRemainingFont;
    SpriteBatch batch;

    public static Scoreboard scoreboard = new Scoreboard(0, 0);
    private OrthographicCamera camera;

    private static int TOTAL_MINES = 20;

    @Override
    public void create() {
        //batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.setToOrtho(true, WIDTH, HEIGHT);
        debugFont = new BitmapFont(true);
        flagsRemainingFont = new BitmapFont(true);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        //tileTest = new Tile(64, 64, 0, 0, new Texture("BombTile.PNG"), camera);
        gameBoard = new GameBoard(12, 12, TOTAL_MINES, camera);

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
        debugFont.draw(batch, Gdx.input.getX() + " " + Gdx.input.getY(), 64, HEIGHT-64);
        flagsRemainingFont.draw(batch, Integer.toString(TOTAL_MINES - scoreboard.getUsedFlags()), 80, HEIGHT-80);

        batch.end();
//        batch.end();
    }

    @Override
    public void dispose() {
//        batch.dispose();
//        img.dispose();
    }

    public int getTotalMines() {
        return TOTAL_MINES;
    }
}
