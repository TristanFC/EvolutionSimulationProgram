/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.data.res;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author Christian Oden
 */
public class Assets {

    private static final int width = 512, height = 512;
    public static BufferedImage water, sand, grass, grass25, grass50, grass75, grass100, pet;
    public static BufferedImage[] WaterAni;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(MyImageLoader.LoadImage(new File("").getAbsolutePath() + "\\res\\SpriteSheet_1.png"));
        WaterAni = new BufferedImage[2];
        WaterAni[0] = sheet.Crop(0, 0, width, height);
        WaterAni[1] = sheet.Crop(width * 3, height, width, height);

        pet = sheet.Crop(0, height * 2, (int) (width * 2), height);

        water = sheet.Crop(0, 0, width, height);
        sand = sheet.Crop(width, 0, width, height);
        grass = sheet.Crop(width * 2, 0, width, height);
        grass25 = sheet.Crop(width * 2, height, width, height);
        grass50 = sheet.Crop(width * 1, height, width, height);
        grass75 = sheet.Crop(width * 0, height, width, height);
        grass100 = sheet.Crop(width * 3, 0, width, height);

    }
}
