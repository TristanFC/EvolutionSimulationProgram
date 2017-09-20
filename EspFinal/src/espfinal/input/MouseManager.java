/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.input;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Christian Oden
 */
public class MouseManager implements MouseInputListener {

    public float factor = 0;

    public void tick() {
        factor = 0;

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("espfinal.input.MouseManager.mouseClicked()");
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        System.out.println("espfinal.input.MouseManager.mouseMoved()");
    }

}
