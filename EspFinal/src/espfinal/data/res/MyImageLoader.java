package espfinal.data.res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Christian Oden
 */
public class MyImageLoader {

    public static BufferedImage LoadImage(String path) {

        BufferedImage tempImage = null;
        File f = null;
        try {
            f = new File(path);
            tempImage = ImageIO.read(f);

        } catch (IOException e) {
            System.out.println(e);
        }
        return tempImage;

    }

}
