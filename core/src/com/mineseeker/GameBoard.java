package com.mineseeker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;

public class GameBoard extends Game {

    int width;
    int height;
    int pixelWidth;
    int pixelHeight;
    int x;
    int y;
    int minesCount;
    float tHeight, tWidth;
    Tile tiles[][];
    OrthographicCamera camera;

    public GameBoard(int width, int height, int minesCount, OrthographicCamera camera) {
        this.width = width;
        this.height = height;
        x = 0;
        y = 200;
        pixelWidth = MineSeeker.WIDTH;
        pixelHeight = MineSeeker.HEIGHT - 200;
        this.minesCount = minesCount;
        this.camera = camera;
        tiles = new Tile[width][height];
        tHeight = (float) pixelWidth / (float) width;
        tWidth = (float) pixelWidth / (float) width;
    }

    @Override
    public void create() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(tHeight, tWidth, i * tWidth, j * tHeight, new Texture("BombTileExploded.PNG"), camera);
            }
        }
    }
    @Override
    public void render() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j].draw();
            }
        }
        super.render();
    }
}
