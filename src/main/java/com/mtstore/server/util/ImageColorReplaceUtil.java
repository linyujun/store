package com.mtstore.server.util;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

public class ImageColorReplaceUtil {

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }


    public static String convert(InputStream inputStream, String colorStr) {
        try {
            BufferedImage image;
            image = ImageIO.read(inputStream);
            Color targetColor = hex2Rgb("#fd2d3b");  // specify the color you want to replace
            Color replacementColor = hex2Rgb(colorStr);  // specify the replacement color
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);
                    Color currentColor = new Color(pixel);
                    if (currentColor.equals(targetColor)) {
                        image.setRGB(x, y, replacementColor.getRGB());
                    }
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String convert(String imgUrl, String colorStr) {
        try {
            BufferedImage image;
            URL imageURL = new URL(imgUrl);
            image = ImageIO.read(imageURL);
            Color targetColor = hex2Rgb("#fd2d3b");  // specify the color you want to replace
            Color replacementColor = hex2Rgb(colorStr);  // specify the replacement color
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);
                    Color currentColor = new Color(pixel);

                    if (currentColor.equals(targetColor)) {
                        image.setRGB(x, y, replacementColor.getRGB());
                    }
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(image, "png", new File("output_image.png"));
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(convert("https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/theme/3.png", "#fd2d3b"));
    }
}

