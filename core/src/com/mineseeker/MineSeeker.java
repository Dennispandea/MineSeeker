package com.mineseeker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.text.DecimalFormat;

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
    StringBuilder debugString;
    private OrthographicCamera camera;
    boolean debugMode = false;

    public static int TOTAL_MINES = 20;

    @Override
    public void create() {
        cameraInit();
        debugInit();
        gameboardInit();

    }

    private void cameraInit() {
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.setToOrtho(true, WIDTH, HEIGHT);
    }

    private void gameboardInit() {
        gameBoard = new GameBoard(16, 16, 40, camera);
        gameBoard.create();
    }

    private void debugInit() {
        debugFont.setColor(1, 0, 0, 1);
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
        debugModeCheck();
        debugFont.draw(batch, Gdx.input.getX() + " " + Gdx.input.getY(), 64, HEIGHT-64);
        flagsRemainingFont.draw(batch, Integer.toString(TOTAL_MINES - scoreboard.getUsedFlags()), 80, HEIGHT-80);

        batch.end();
//        batch.end();
    }


    public String debugUpdate() {
        debugString = new StringBuilder();
        debugString.append("X:");
        debugString.append(Gdx.input.getX());
        debugString.append("   ");
        debugString.append("Y:");
        debugString.append(Gdx.input.getY());
        debugString.append('\n');

        debugString.append("FPS: ");
        debugString.append(Gdx.graphics.getFramesPerSecond());
        debugString.append('\n');

        debugString.append("totalDrawTime: ");
        debugString.append(gameBoard.getDrawTime());
        debugString.append("ms");
        debugString.append('\n');

        debugString.append("Memory usage: ");
        debugString.append(util.memUsageCalc(Gdx.app.getJavaHeap()));
        debugString.append('\n');

        debugString.append("Window Size: ");
        debugString.append(Gdx.graphics.getWidth());
        debugString.append(" ");
        debugString.append(Gdx.graphics.getHeight());

        return debugString.toString();
    }

    private void debugModeCheck() {
        if (debugMode) {
            font.draw(batch, debugUpdate(), 10, 10);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            debugMode = !debugMode;
            System.out.println("Toggled debug");
        }
    }

    @Override
    public void dispose() {
    }
}
