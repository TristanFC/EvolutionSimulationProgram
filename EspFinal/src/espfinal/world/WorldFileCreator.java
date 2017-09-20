/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.world;

import espfinal.util.PerlinNoise2D;
import espfinal.util.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author Christian Oden
 */
public class WorldFileCreator {

    public static void CreateWorld(int size, String path) throws IOException {
        int MapSize = size;

        int[][] MAP = new int[size][size];

        PerlinNoise2D NoiseGEN = new PerlinNoise2D();

        int xMoveR;
        int yMoveR;
        float CurrentPlace;
        float Spliter = (float) 0.3;

        xMoveR = (int) (Math.random() * 10);
        yMoveR = (int) (Math.random() * 10);

        for (int x = 0; x < MapSize; x++) {
            for (int y = 0; y < MapSize; y++) {

                CurrentPlace = (float) Math.abs(NoiseGEN.noise((x * 0.05) + xMoveR, (y * 0.05) + yMoveR));

                if (CurrentPlace > Spliter) {
                    MAP[x][y] = 1;

                }
                if (CurrentPlace < Spliter) {
                    MAP[x][y] = 0;

                }

            }

        }

        for (int x = 0; x < MapSize; x++) {
            for (int y = 0; y < MapSize; y++) {

                if (MAP[x][y] == 0) {

                    for (int T : GetPartsArround(MAP, MapSize, x, y)) {
                        if (T == 1) {
                            MAP[x][y] = 2;

                        }
                    }
                }

            }
        }
        saveMap(path, MAP, MapSize);

    }

    public static int[] GetPartsArround(int[][] TileMap, int Size, int PosX, int PosY) {
        Size = Size - 1;
        int[] PartsArround = new int[4];
        PartsArround[0] = TileMap[PosX][Utils.setMAXInt(PosY + 1, Size)];
        PartsArround[1] = TileMap[Utils.set0INT(PosX - 1)][PosY];
        PartsArround[2] = TileMap[Utils.setMAXInt(PosX + 1, Size)][PosY];
        PartsArround[3] = TileMap[PosX][Utils.set0INT(PosY - 1)];

        return PartsArround;
    }

    public static void saveMap(String path, int[][] map, int size) throws IOException {
        File file = new File(path);

        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write(size + " " + size);
        bw.newLine();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                bw.write(" " + map[x][y]);

            }
            bw.newLine();
        }

        bw.close();

    }

}
