/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.entities;

import espfinal.EspFinal;
import espfinal.Handler;
import espfinal.state.State;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Christian Oden
 */
public abstract class Entity {

    protected float x;
    protected float y;
    protected int widht;
    protected int height;
    protected Handler handler;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int widht, int height) {
        this.x = x;
        this.y = y;
        this.widht = widht;
        this.height = height;
        this.handler = handler;

        bounds = new Rectangle(0, 0, widht, height);

    }

    public void move(float mx, float my) {
        x += mx;
        y += my;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the widht
     */
    public int getWidht() {
        return widht;
    }

    /**
     * @param widht the widht to set
     */
    public void setWidht(int widht) {
        this.widht = widht;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    protected boolean CollisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public Rectangle getBounds() {
        return bounds;

    }
}
