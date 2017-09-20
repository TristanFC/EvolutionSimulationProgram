/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Christian Oden
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right, zin, zout;
    public float zoom = 1;

    public KeyManager() {
        keys = new boolean[256];

    }

    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        zin = keys[KeyEvent.VK_R];
        zout = keys[KeyEvent.VK_F];
        if (zin) {
            zoom += 0.01;
        }
        if (zout) {
            zoom -= 0.01;
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keys[ke.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;

    }

}
