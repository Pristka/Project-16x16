package project_16x16.multiplayer;

import org.junit.Assert;
import processing.core.PApplet;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.components.AnimationComponent;
import project_16x16.scene.GameplayScene;

import static org.junit.jupiter.api.Assertions.*;

class MultiplayerTest {

    private static  Multiplayer client;
    private static  Multiplayer server;


    @org.junit.jupiter.api.BeforeAll
    static void beforeTest() {
        SideScroller applet = new SideScroller();
        PApplet.runSketch(new String[] { "--location=760,0", "" }, applet);
        Tileset.load(applet);
        AnimationComponent.applet = applet;
        GameplayScene gameplayScene = new GameplayScene(applet);
        client = new Multiplayer(gameplayScene, "127.0.0.1", 25565);
        server = new Multiplayer(gameplayScene, 25565);
        System.out.println("before test did smth");
    }

    @org.junit.jupiter.api.Test
    void writeDataToClient() {
        Assert.assertFalse(client.c.active());
        System.out.println("before test did smth");
    }

    @org.junit.jupiter.api.Test
    void readDataFromClient() {
        Assert.assertFalse(client.c.available() > 0);
    }

    @org.junit.jupiter.api.Test
    void writeDataToServer() {
        Assert.assertTrue(server.s.active());
    }

    @org.junit.jupiter.api.Test
    void readDataFromServer() {
        Assert.assertNull(server.s.available());
    }

    @org.junit.jupiter.api.Test
    void testCheckIfNotServer() {
        Assert.assertFalse(server.checkIfNotServer());
    }
}