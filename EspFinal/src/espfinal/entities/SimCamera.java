/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.entities;

import espfinal.EspFinal;

/**
 *
 * @author Christian Oden
 */
public class SimCamera {

    private EspFinal esp;
    private float xoffset;
    private float yoffset;
    private float zoom = 1;

    public SimCamera(EspFinal esp, float xoffset, float yoffset, float zoom) {
        this.xoffset = xoffset;
        this.yoffset = yoffset;
        this.zoom = zoom;
        this.esp = esp;
    }

    public void move(float xAmt, float yAmt) {
        xoffset += xAmt;
        yoffset += yAmt;

    }

    public void centerOnentity(Entity e) {

        xoffset = e.getX() - esp.display.getWidth() / 2;
        yoffset = e.getY() - esp.display.getHeight() / 2;

    }

    /**
     * @return the xoffset
     */
    public float getXoffset() {
        return xoffset;
    }

    /**
     * @param xoffset the xoffset to set
     */
    public void setXoffset(float xoffset) {
        this.xoffset = xoffset;
    }

    /**
     * @return the yoffset
     */
    public float getYoffset() {
        return yoffset;
    }

    /**
     * @param yoffset the yoffset to set
     */
    public void setYoffset(float yoffset) {
        this.yoffset = yoffset;
    }

    /**
     * @return the zoom
     */
    public float getZoom() {
        return zoom;
    }

    /**
     * @param zoom the zoom to set
     */
    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

}
