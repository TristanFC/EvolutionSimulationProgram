/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.state;

import espfinal.Handler;
import java.awt.Graphics;

/**
 *
 * @author Christian Oden
 */
public abstract class State {

    private static State currentState = null;
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    //CLASS
    public abstract void tick();

    public abstract void render(Graphics g);

}
