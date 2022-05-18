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
    int lastDraw=15;
    //    SpriteBatch batch;
//    Texture img;
    Tile tileTest;
    GameBoard gameBoard;
    BitmapFont font;
    SpriteBatch batch;
    StringBuilder debugString;
    private OrthographicCamera camera;
    boolean debugMode = false;


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
        font = new BitmapFont(true);
        font.setColor(1, 0, 0, 1);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
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

    String test="";
    private void debugModeCheck() {
        if (debugMode) {
            lastDraw++;
            if (lastDraw > 5){
               test= debugUpdate();
                lastDraw=0;
            }
            font.draw(batch, test, 10, 10);
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
