package hu.halasz.bmpmap;

import net.sf.image4j.codec.bmp.BMPDecoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("read bmp start: " + LocalDateTime.now());

        BufferedImage image = BMPDecoder.read(new File("C:\\Users\\Attila\\Desktop\\prog\\hexwars\\bmpmap\\hun.bmp"));
        BufferedImage image2 = BMPDecoder.read(new File("C:\\Users\\Attila\\Desktop\\prog\\hexwars\\bmpmap\\hunoriginal.bmp"));

        int width = image.getWidth();
        int height = image.getHeight();

        Map<Integer, Province> provinceMap = new HashMap<>();
        //List<Pixel> pixelList = new ArrayList<>();
        Map<Pixel, Pixel> pixelMap = new HashMap<>();

        System.out.println("phase1 started: " + LocalDateTime.now());

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

        System.out.println("phase2 started: " + LocalDateTime.now());

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

        System.out.println(LocalDateTime.now());

        JFrame frame = new JFrame("OvalPaint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OvalPaint panel = new OvalPaint(provinceMap, image, image2);

        frame.add(panel);

        frame.setSize(1000, 800);
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
        BufferedImage image;
        Province province;
        BufferedImage bgImage;

        public OvalPaint(Map<Integer, Province> provinceMap, BufferedImage image,  BufferedImage image2) {
            this.provinceMap = provinceMap;
            this.image = image;
            this.bgImage = image2;
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    int rgb = image.getRGB(x, y);
                    province = provinceMap.get(rgb);
                    repaint();
                }
            });
        }



        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(bgImage, 0, 0, null);
            if (province != null){
                g.setColor(new Color(province.getRgbId()));
                Set<Pixel> borderPixels = province.getBorderPixels();
                for (Pixel borderPixel : borderPixels) {
                    g.drawLine(borderPixel.getX(), borderPixel.getY(), borderPixel.getX(), borderPixel.getY());
                }
            }
            /*for (Province province : provinceMap.values()) {
                g.setColor(new Color(province.getRgbId()));
                Set<Pixel> borderPixels = province.getBorderPixels();
                for (Pixel borderPixel : borderPixels) {
                    g.drawLine(borderPixel.getX(), borderPixel.getY(), borderPixel.getX(), borderPixel.getY());
                }
            }*/

        }
    }
}
