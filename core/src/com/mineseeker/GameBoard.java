package com.mineseeker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class GameBoard extends Game {

    int width;
    int height;
    int pixelWidth;
    int pixelHeight;
    int x;
    int y;
    int minesCount, minesLeft;
    float tHeight, tWidth;
    Random random = new Random();
    Tile[][] tiles;
    OrthographicCamera camera;

    public GameBoard(int width, int height, int minesCount, OrthographicCamera camera) {
        this.width = width;
        this.height = height;
        x = 0;
        y = 200;
        pixelWidth = MineSeeker.WIDTH;
        pixelHeight = MineSeeker.HEIGHT - 200;
        this.minesCount = minesCount;
        this.minesLeft = minesCount;
        this.camera = camera;
        tiles = new Tile[width][height];
        tHeight = (float) pixelWidth / (float) width;
        tWidth = (float) pixelWidth / (float) width;
    }


    @Override
    public void create() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() { //the minefield should be created in 3 steps, create the empty field, add bombs, calculate bombs around every tile
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        tiles[i][j] = new Tile(tHeight, tWidth, i * tWidth, j * tHeight, new Texture("EmptyTile.PNG"), camera);
                    }
                }
                while (minesLeft > 0) { //keep running until all the bombs are set
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            if (!tiles[i][j].isBomb()) if (random.nextInt(100) <= 5) {
                                tiles[i][j].setBomb(true);
                                minesLeft--;
                            }
                            if (minesLeft == 0) break;
                        }
                    }
                }
                for (int i = 0; i < width; i++) { //check all bombs
                    for (int j = 0; j < height; j++) {
                        int bombCount = 0;
                        if (i > 0) {
                            if (tiles[i - 1][j].isBomb()) bombCount++;
                            if (j < height - 1) {
                                if (tiles[i - 1][j + 1].isBomb()) bombCount++;
                            }
                        }
                        if (j > 0) {
                            if (tiles[i][j - 1].isBomb()) bombCount++;
                            if (i < width - 1) {
                                if (tiles[i + 1][j - 1].isBomb()) bombCount++;
                            }
                        }
                        if (j > 0 && i > 0) {
                            if (tiles[i - 1][j - 1].isBomb()) bombCount++;
                        }

                        if (i < width - 1) {
                            if (tiles[i + 1][j].isBomb()) bombCount++;
                        }

                        if (j < height - 1) {
                            if (tiles[i][j + 1].isBomb()) bombCount++;
                        }

                        if (i < width - 1 && j < height - 1) {
                            if (tiles[i + 1][j + 1].isBomb()) bombCount++;
                        }

                        tiles[i][j].setBombsAroundCount(bombCount);
                    }
                }

            }
        });
        //this has to be run, start will not work
        t.run();
    }

    private void revealAllBombs(int i, int j) {
        if (Gdx.input.isKeyPressed(Input.Keys.B)) {
            tiles[i][j].revealIfBomb();
        }
    }

    @Override
    public void render() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j].draw();
                revealAllBombs(i, j);

                int mouseX = Gdx.input.getX();
                int mouseY = Gdx.input.getY();

                float tileX = tiles[i][j].getX();
                float tileY = tiles[i][j].getY();

                if (!tiles[i][j].isFlagged() && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                        (mouseX >= tileX &&
                                mouseX <= tileX + tiles[i][j].getWidth() &&
                                mouseY >= tileY &&
                                mouseY <= tileY + tiles[i][j].getHeight())) {

                    DFS(i, j);

                    if (tiles[i][j].isBomb())
                        tiles[i][j].setRevealedTex(new Texture("BombTileExploded.PNG"));

                    tiles[i][j].debugTile();
                }
            }
        }
        super.render();
    }

    public void DFS(int i, int j) {
        if (tiles[i][j].getBombsAroundCount() == 0 && !tiles[i][j].isRevealed()) {
            if (i > 0) {
                if (!tiles[i - 1][j].isBomb()) {
                    tiles[i - 1][j].reveal();
                    DFS(i - 1, j);
                }
                if (j < height - 1) {
                    if (!tiles[i - 1][j + 1].isBomb()) {
                        tiles[i - 1][j + 1].reveal();
                        DFS(i - 1, j + 1);
                    }
                }
            }
            if (j > 0) {
                if (!tiles[i][j - 1].isBomb()) ;
                if (i < width - 1) {
                    if (!tiles[i + 1][j - 1].isBomb()) {
                        tiles[i + 1][j - 1].reveal();
                        DFS(i + 1, j - 1);
                    }
                }
            }
            if (j > 0 && i > 0) {
                if (!tiles[i - 1][j - 1].isBomb()) {
                    tiles[i - 1][j - 1].reveal();
                    DFS(i - 1, j - 1);
                }
            }

            if (i < width - 1) {
                if (!tiles[i + 1][j].isBomb()) {
                    tiles[i + 1][j].reveal();
                    DFS(i + 1, j);
                }
            }

            if (j < height - 1) {
                if (!tiles[i][j + 1].isBomb()) {
                    tiles[i][j + 1].reveal();
                    DFS(i, j + 1);
                }
            }

            if (i < width - 1 && j < height - 1) {
                if (!tiles[i + 1][j + 1].isBomb()) {
                    tiles[i + 1][j + 1].reveal();
                    DFS(i + 1, j + 1);
                }
            }
        }
    }
}


