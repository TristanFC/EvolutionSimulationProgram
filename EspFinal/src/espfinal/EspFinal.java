/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal;

import espfinal.data.res.Assets;
import com.sun.javafx.iio.ImageLoader;
import espfinal.entities.SimCamera;
import espfinal.input.KeyManager;
import espfinal.input.MouseManager;
import espfinal.state.MenueState;
import espfinal.state.SimulationState;
import espfinal.state.State;
import espfinal.world.WorldFileCreator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian Oden
 */
public class EspFinal implements Runnable {

    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;

    public Display display;

    //States
    private State SimulationState;
    private State MenueState;

//inputcontroll
    private KeyManager keyManager;
    private MouseManager mouseManager;
//camera 
    SimCamera camera;
//Handler
    private Handler handler;

    public EspFinal() {

        mouseManager = new MouseManager();
        keyManager = new KeyManager();
        start();

    }

    public static void main(String[] args) {
        // TODO code application logic here
        // System.out.println("espfinal.EspFinal.main()");
        try {
            WorldFileCreator.CreateWorld(25, new File("").getAbsolutePath() + "\\res\\test.text");

            new EspFinal();
        } catch (IOException ex) {
            Logger.getLogger(EspFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void init() {
        display = new Display("ESP", 1600, 900);
        display.getJFrame().addKeyListener(keyManager);

        //asset loading
        Assets.init();

        //init cam
        camera = new SimCamera(this, 0, 0, 1);
        handler = new Handler(this);

//states
        SimulationState = new SimulationState(handler);
        MenueState = new MenueState(handler);
        State.setState(SimulationState);

    }

    private void update() {
        keyManager.tick();
        mouseManager.tick();
        if (State.getState() != null) {
            State.getState().tick();
        }

    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, 1600, 900);
//Draw here->
        if (State.getState() != null) {
            State.getState().render(g);
        }

//<-Draw end
        bs.show();
        g.dispose();

    }

    public void run() {
        init();

        int FPS = 30;
        double timePerTick = 1000000000 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {

                update();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                System.out.println("Frames per second " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public SimCamera getSimCamera() {
        return camera;
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            Logger.getLogger(EspFinal.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
