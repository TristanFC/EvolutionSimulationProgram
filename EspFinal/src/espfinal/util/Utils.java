/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Christian Oden
 */
public class Utils {

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();

    }

    public static int parseInt(String number) {
        try {

            return Integer.parseInt(number);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static int set0INT(int x) {
        if (x > 0) {
            return x;
        } else {
            return 0;
        }
    }

    public static double set0Double(double x) {
        if (x > 0) {
            return x;
        } else {
            return 0;
        }
    }

    public static float set0FLOAT(float x) {
        if (x > 0) {
            return x;
        } else {
            return 0;
        }
    }

    public static int setMAXInt(int x, int max) {
        if (x < max) {
            return x;
        } else {
            return max;
        }
    }

    public static double setMAXDouble(double x, double max) {
        if (x < max) {
            return x;
        } else {
            return max;
        }
    }

    public static float setMAXFloat(float x, float max) {
        if (x < max) {
            return x;
        } else {
            return max;
        }
    }

    public static float Sigmoid(float x) {
        float exp = (float) (1 + (Math.pow(Math.E, -x)));
        return 1 / exp;
    }

    public static int ClampInt(int min, int max, int now) {
        if (now > min && now < max) {
            return now;
        }
        if (now > max) {
            return max;
        }
        if (now < min) {
            return min;

        }
        return 0;
    }

    public static double ClampDouble(double min, double max, double now) {
        if (now > min && now < max) {
            return now;
        }
        if (now > max) {
            return max;
        }
        if (now < min) {
            return min;
        }
        return 0;
    }

    public static float ClampFloat(float min, float max, float now) {
        if (now > min && now < max) {
            return now;
        }
        if (now > max) {
            return max;
        }
        if (now < min) {
            return min;
        }
        return 0;
    }

    public static boolean IntInRange(int min, int max, int now) {
        if (now > min && now < max) {
            return true;
        }
        if (now > max) {
            return false;
        }
        if (now < min) {
            return false;

        }
        return false;
    }

    public static boolean DoubleInRange(double min, double max, double now) {
        if (now > min && now < max) {
            return true;
        }
        if (now > max) {
            return false;
        }
        if (now < min) {
            return false;

        }
        return false;
    }

    public static boolean FloatInRange(float min, float max, float now) {
        if (now > min && now < max) {
            return true;
        }
        if (now > max) {
            return false;
        }
        if (now < min) {
            return false;

        }
        return false;
    }

}
