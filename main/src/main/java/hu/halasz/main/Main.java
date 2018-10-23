package hu.halasz.main;

import hu.halasz.hexamap.HexagonMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    HexagonMap hexagonMap = new HexagonMap();
        hexagonMap.createEmptyMapGridInREctangularForm(10, 10);
    java.util.Map<Hexagon, Hexagon> map = hexagonMap.getMap();


    AStar aStar = new AStar();
    Hexagon start = new Hexagon(0, 0, 0);
    Hexagon goal = new Hexagon(5, 9);
        aStar.searchPath(map.get(start), map.get(goal));

    java.util.Map<Hexagon, Hexagon> cameFrom = aStar.cameFrom;
    List<Hexagon> path = new ArrayList<>();
    recursion(start, goal, path, cameFrom);

    JFrame frame = new JFrame("OvalPaint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    OvalPaint panel = new OvalPaint(hexagonMap, path);

        frame.add(panel);

        frame.setSize(300, 200);
        frame.setVisible(true);
}

    private static void recursion(Hexagon start, Hexagon current, List<Hexagon> path,
                                  java.util.Map<Hexagon, Hexagon> cameFrom) {
        Hexagon hexagon = cameFrom.get(current);

        path.add(hexagon);
        if (hexagon.equals(start)){
            return;
        }

        recursion(start, hexagon, path, cameFrom);
    }

private static class OvalPaint extends JPanel {
    HexagonMap hexagonMap;
    List<Hexagon> path;

    OvalPaint(HexagonMap hexagonMap,  List<Hexagon> path){
        this.hexagonMap = hexagonMap;
        this.path = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        hexagonMap.getMap().forEach((k,v) -> {
            if (path.contains(k)){
                g.setColor(Color.BLUE);
                List<Point2D> point2DS = hexagonMap.getLayout().polygonCorners(v);
                int[] xPoints = (point2DS.stream().mapToInt(p -> (int) p.x -1)).toArray();
                int[] yPoints =(point2DS.stream().mapToInt(p -> (int)p.y-1)).toArray();
                g.drawPolygon(xPoints, yPoints, point2DS.size());
            } else {
                g.setColor(Color.RED);
                List<Point2D> point2DS = hexagonMap.getLayout().polygonCorners(v);
                int[] xPoints = (point2DS.stream().mapToInt(p -> (int) p.x )).toArray();
                int[] yPoints =(point2DS.stream().mapToInt(p -> (int)p.y)).toArray();
                g.drawPolygon(xPoints, yPoints, point2DS.size());
            }



        });
    }
}
}
