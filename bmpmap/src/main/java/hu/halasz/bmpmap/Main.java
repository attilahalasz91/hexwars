package hu.halasz.bmpmap;

import net.sf.image4j.codec.bmp.BMPDecoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage image = BMPDecoder.read(new File("C:\\Users\\Attila\\Desktop\\prog\\hexwars\\bmpmap\\Untitled.bmp"));

        int width = image.getWidth();
        int height = image.getHeight();

        Map<Integer, Province> provinceMap = new HashMap<>();
        //List<Pixel> pixelList = new ArrayList<>();
        Map<Pixel, Pixel> pixelMap = new HashMap<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                Pixel pixel = new Pixel(i, j, rgb);
                pixelMap.put(pixel, pixel);

                Province province;
                if (!provinceMap.containsKey(rgb)) {
                    province = new Province(rgb);
                    provinceMap.put(rgb, province);
                } else {
                    province = provinceMap.get(rgb);
                }
                province.addPixel(pixel);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                Pixel pixel = pixelMap.get(new Pixel(i, j, 0));
                Province province = provinceMap.get(rgb);

                int left = i - 1;
                if (left >= 0) {
                    Pixel leftPixel = pixelMap.get(new Pixel(left, j, 0));
                    addNeighbors(rgb, pixel, province, leftPixel);
                } else {
                    province.addBorderPixel(pixel);
                }

                int right = i + 1;
                if (right < width) {
                    Pixel rightPixel = pixelMap.get(new Pixel(right, j, 0));
                    addNeighbors(rgb, pixel, province, rightPixel);
                } else {
                    province.addBorderPixel(pixel);
                }

                int up = j - 1;
                if (up >= 0) {
                    Pixel upPixel = pixelMap.get(new Pixel(i, up, 0));
                    addNeighbors(rgb, pixel, province, upPixel);
                } else {
                    province.addBorderPixel(pixel);
                }

                int down = j + 1;
                if (down < height) {
                    Pixel downPixel = pixelMap.get(new Pixel(i, down, 0));
                    addNeighbors(rgb, pixel, province, downPixel);
                } else {
                    province.addBorderPixel(pixel);
                }

            }
        }

        JFrame frame = new JFrame("OvalPaint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        OvalPaint panel = new OvalPaint(provinceMap);

        frame.add(panel);

        frame.setSize(300, 200);
        frame.setVisible(true);

        System.out.println("end");
    }

    private static void addNeighbors(int rgb, Pixel currentPixel, Province province, Pixel neighborPixel) {
        currentPixel.addNeighbourPixel(neighborPixel);
        if (neighborPixel.getRgbId() != rgb) {
            province.addBorderPixel(currentPixel);
        }
    }

    private static class OvalPaint extends JPanel {

        Map<Integer, Province> provinceMap;

        public OvalPaint(Map<Integer, Province> provinceMap) {
            this.provinceMap = provinceMap;
        }

        @Override
        protected void paintComponent(Graphics g) {
            for (Province province : provinceMap.values()) {
                g.setColor(new Color(province.getRgbId()));
                Set<Pixel> borderPixels = province.getBorderPixels();
                for (Pixel borderPixel : borderPixels) {
                    g.drawLine(borderPixel.getX(), borderPixel.getY(), borderPixel.getX(), borderPixel.getY());
                }
            }

        }
    }
}
