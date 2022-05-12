package com.mineseeker;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mineseeker.MineSeeker;

import static com.mineseeker.MineSeeker.HEIGHT;
import static com.mineseeker.MineSeeker.WIDTH;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {


    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(WIDTH, HEIGHT);
        config.useVsync(true);
        config.setForegroundFPS(60);
        config.setTitle("MineSeeker");
        new Lwjgl3Application(new MineSeeker(), config);
    }
}
