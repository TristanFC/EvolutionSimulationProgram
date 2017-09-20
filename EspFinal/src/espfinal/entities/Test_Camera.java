/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.entities;

import espfinal.EspFinal;
import espfinal.Handler;
import espfinal.data.res.Assets;
import espfinal.state.State;
import espfinal.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Christian Oden
 */
public class Test_Camera extends Entity {

    public Test_Camera(Handler handler, float x, float y, int widht, int height) {
        super(handler, x, y, widht, height);

        //  bounds.x = 16;
        //  bounds.y = 32;
        //  bounds.height = 32;
        // bounds.width = 32;
    }

    @Override
    public void tick() {

        handler.getSimCamera().centerOnentity(this);

        if (handler.getKeyManager().up) {
            setY(getY() - 5);
        }
        if (handler.getKeyManager().down) {
            setY(getY() + 5);
        }
        if (handler.getKeyManager().left) {
            setX(getX() - 5);
        }
        if (handler.getKeyManager().right) {
            setX(getX() + 5);
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.sand, (int) (getX() - handler.getSimCamera().getXoffset()), (int) (getY() - handler.getSimCamera().getYoffset()), this.getWidht(), this.getHeight(), null);

        g.setColor(Color.red);
        g.fillRect((int) (x + bounds.x - handler.getSimCamera().getXoffset()), (int) (y + bounds.y - handler.getSimCamera().getYoffset()), (int) bounds.width, (int) bounds.height);

    }

}
