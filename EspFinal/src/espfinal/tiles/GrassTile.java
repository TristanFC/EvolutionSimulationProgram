/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.tiles;

import espfinal.data.res.Assets;
import espfinal.util.Utils;

/**
 *
 * @author Christian Oden
 */
public class GrassTile extends Tile {

    private float Lvalue;

    public GrassTile(int id) {
        super(Assets.grass, id);
    }

    @Override
    public void tick() {

    }

    public Tile[] getTilesArround(int id, Tile[] tiles) {
        Tile[] Ltiles = new Tile[8];

        Ltiles[0] = tiles[Utils.ClampInt(0, 9999, (id - 1))];
        Ltiles[1] = tiles[Utils.ClampInt(0, 9999, (id + 1))];
        Ltiles[2] = tiles[Utils.ClampInt(0, 9999, (id - 99))];
        Ltiles[3] = tiles[Utils.ClampInt(0, 9999, (id - 100))];
        Ltiles[4] = tiles[Utils.ClampInt(0, 9999, (id - 101))];
        Ltiles[5] = tiles[Utils.ClampInt(0, 9999, (id + 99))];
        Ltiles[6] = tiles[Utils.ClampInt(0, 9999, (id + 100))];
        Ltiles[7] = tiles[Utils.ClampInt(0, 9999, (id + 101))];

        return Ltiles;
    }
}
