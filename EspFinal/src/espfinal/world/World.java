/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.world;

import espfinal.EspFinal;
import espfinal.Handler;
import espfinal.data.res.Animation;
import espfinal.data.res.Assets;
import espfinal.entities.TestPet;
import espfinal.tiles.Tile;
import espfinal.util.Utils;
import java.awt.Graphics;

/**
 *
 * @author Christian Oden
 */
public class World {

    double rt = 0;
    private int height = 10;
    private int width = 10;
    private Handler handler;
    private int[][] tiles;
    private float[][] values;
    private final float growRate = 0.5f;

    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
 
    }

    public int getTileInt(int x, int y) {
        if (x < 0 || x > width-1 || y < 0 || y > height-1) {
            return 0;
        } else {
            return tiles[x][y];
        }
    }

    public float getTileValue(int x, int y) {
        if (x < 0 || x > height-1 || y < 0 || y > height-1) {
            return 0;
        } else {
            return values[x][y];
        }
    }

    public void tick() {

        if (handler.getPets().size() < 5) {
            handler.getPets().add(new TestPet(handler, (float) Math.random() * width*Tile.TileWidht, (float) Math.random() * height*Tile.TileHeight, 25, 25,"Pet Thread",true));
            System.out.println("espfinal.world.World.tick()");
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).tick();

                if (tiles[x][y] == 2 && Math.floor(values[x][y]) == 100) {
                    GROW(x, y);

                }
                if (tiles[x][y] == 2 && values[x][y] < 100) {

                    values[x][y] += growRate * Math.random() * Math.random();

                }
                if (tiles[x][y] == 2 && values[x][y] <= 0) {

                    values[x][y] = 0;
                    tiles[x][y] = 1;

                }
            }

        }

        for (int i = 0; i < handler.getPets().size(); i++) {

            TestPet p = handler.getPets().get(i);
            int petX = (int) (handler.getPets().get(i).getX() / Tile.TileWidht);
            int petY = (int) (handler.getPets().get(i).getY() / Tile.TileHeight);
            int petFX = (int) (((p.getX() + p.getFX()) * 2 / Tile.TileWidht) / 1.5);
            int petFY = (int) (((p.getY() + p.getFY()) * 2 / Tile.TileHeight) / 1.5);

// System.out.println(getTile((int)(handler.getPets().get(i).getX())/Tile.TileWidht,(int)(handler.getPets().get(i).getY())/Tile.TileHeight));    
            //    System.out.println(""+(int)(handler.getPets().get(i).getX())/Tile.TileWidht +"  "+(int)(handler.getPets().get(i).getY())/Tile.TileHeight+"");
        }

// System.out.println(getTile((int)(handler.getSimCamera().getXoffset()+803)/Tile.TileWidht,(int)(handler.getSimCamera().getYoffset()+464)/Tile.TileHeight));
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getSimCamera().getXoffset() / Tile.TileWidht);
        int xEnd = (int) Math.min(width, (handler.getSimCamera().getXoffset() + handler.getWidth()) / Tile.TileWidht + 1);
        int yStart = (int) Math.max(0, handler.getSimCamera().getYoffset() / Tile.TileHeight);
        int yEnd = (int) Math.min(height, (handler.getSimCamera().getYoffset() + handler.getHeight()) / Tile.TileHeight + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {

                getTile(x, y).render(g,
                        (int) (x * Tile.TileWidht - handler.getSimCamera().getXoffset()),
                        (int) (y * Tile.TileWidht - handler.getSimCamera().getYoffset()),
                        handler.getKeyManager().zoom, handler.getKeyManager().zoom, values[x][y]);
            }

        }

    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.waterTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.waterTile;
        }

        return t;
    }

    public boolean canEat(int x, int y) {
        if (x < 0 || x > height-1 || y < 0 || y > height-1) {
            return false;
        } else {
            if (values[x][y] <= 0) {
                return false;
            }
            return true;
        }

    }

    public void eatTile(int x, int y, float value) {
        if (x < 0 || x > height-1 || y < 0 || y > height-1) {

        } else {
            values[x][y] -= value;
        }

    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        tiles = new int[width][height];
        values = new float[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
                values[x][y] = 0;
            }

        }

    }

    private void GROW(int x, int y) {
        if (tiles[Utils.ClampInt(0, height-1, x - 1)][Utils.ClampInt(0, height-1, y - 1)] == 1) {
            tiles[Utils.ClampInt(0, height-1, x - 1)][Utils.ClampInt(0, height-1, y - 1)] = 2;
            //  values[Utils.ClampInt(0, 99, x - 1)][Utils.ClampInt(0, 99, y - 1)] = 0;

        }
        if (tiles[x][Utils.ClampInt(0, height-1, y - 1)] == 1) {
            tiles[x][Utils.ClampInt(0, height-1, y - 1)] = 2;
            // values[x][Utils.ClampInt(0, 99, y - 1)] = 0;

        }
        if (tiles[Utils.ClampInt(0, height-1, x + 1)][Utils.ClampInt(0, height-1, y - 1)] == 1) {
            tiles[Utils.ClampInt(0, height-1, x + 1)][Utils.ClampInt(0, height-1, y - 1)] = 2;
            //  values[Utils.ClampInt(0, 99, x + 1)][Utils.ClampInt(0, 99, y - 1)] = 0;
        }
        if (tiles[Utils.ClampInt(0, height-1, x - 1)][Utils.ClampInt(0, height-1, y + 1)] == 1) {
            tiles[Utils.ClampInt(0, height-1, x - 1)][Utils.ClampInt(0, height-1, y + 1)] = 2;
            //   values[Utils.ClampInt(0, 99, x - 1)][Utils.ClampInt(0, 99, y + 1)] = 0;
        }
        if (tiles[x][Utils.ClampInt(0, height-1, y + 1)] == 1) {
            tiles[x][Utils.ClampInt(0, height-1, y + 1)] = 2;
            // values[x][Utils.ClampInt(0, 99, y + 1)] = 0;
        }
        if (tiles[Utils.ClampInt(0, height-1, x + 1)][Utils.ClampInt(0, height-1, y + 1)] == 1) {
            tiles[Utils.ClampInt(0, height-1, x + 1)][Utils.ClampInt(0, height-1, y + 1)] = 2;
            // values[Utils.ClampInt(0, 99, x + 1)][Utils.ClampInt(0, 99, y + 1)] = 0;
        }
        if (tiles[Utils.ClampInt(0, height-1, x - 1)][y] == 1) {
            tiles[Utils.ClampInt(0, height-1, x - 1)][y] = 2;
            //   values[Utils.ClampInt(0, 99, x - 1)][y] = 0;
        }
        if (tiles[Utils.ClampInt(0, height-1, x + 1)][y] == 1) {
            tiles[Utils.ClampInt(0, height-1, x + 1)][y] = 2;
            // values[Utils.ClampInt(0, 99, x + 1)][y] = 0;

        }
    }
}
