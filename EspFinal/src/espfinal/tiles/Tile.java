/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.tiles;

import espfinal.data.res.Animation;
import espfinal.data.res.Assets;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javafx.geometry.Bounds;

/**
 *
 * @author Christian Oden
 */
public class Tile {

    //static stuff
    public static Tile[] tiles = new Tile[10000];
    public static Tile grassTile = new GrassTile(2);
    public static Tile sandTile = new SandTile(1);
    public static Tile waterTile = new WaterTile(0);

    public static final int TileWidht = 32, TileHeight = 32;

    // protected float value;
    protected BufferedImage texture;
    private int id;
    private Animation waterAnimation;
    protected Rectangle bounds;

    public Tile(BufferedImage texture, int id) {
        waterAnimation = new Animation(1000, Assets.WaterAni);

        this.texture = texture;
        this.id = id;

        tiles[id] = this;
        bounds = new Rectangle(0, 0, TileWidht, TileHeight);
    }

    public void tick() {
        waterAnimation.tick();

    }

    public void render(Graphics g, int x, int y, float width, float height, float lvalue) {

        if (lvalue > 0) {
            float value = lvalue;
            BufferedImage ltexture = null;

            if (value < 25) {
                ltexture = Assets.grass25;
            }
            if (value < 50 && value > 25) {
                ltexture = Assets.grass50;
            }
            if (value < 75 && value > 50) {
                ltexture = Assets.grass75;
            }
            if (value < 100 && value > 75) {
                ltexture = Assets.grass100;
            }

            if (value >= 100) {
                ltexture = Assets.grass;
            }
            g.drawImage(ltexture, x, y, (int) (TileWidht * width), (int) (TileHeight * height), null);

        }
        if (id == 0) {
            g.drawImage(waterAnimation.getCurrentFrame(), x, y, (int) (TileWidht * width), (int) (TileHeight * height), null);

        }
        if (id == 1) {

            g.drawImage(texture, x, y, (int) (TileWidht * width), (int) (TileHeight * height), null);
        }
        //  bounds.setLocation(x, y);

    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }

    public Rectangle getBounds() {
        return bounds;
    }

}
