package hu.halasz.pathfinding.astar;


import halasz.map.hexagon.Hexagon;
import halasz.map.hexagon.Point2D;
import org.javatuples.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.BiFunction;

public class AStar {

    PriorityQueue<Pair> frontier = new PriorityQueue<>(
            Comparator.comparingDouble(p -> ((Float)p.getValue1()).doubleValue()));

    public Map<Hexagon, Hexagon> cameFrom = new HashMap<>();
    Map<Hexagon, Integer> costSoFar = new HashMap<>();

    BiFunction<Hexagon, Hexagon, Float> heuristic = (h1, h2) -> Point2D.distance(h1.getX(), h1.getY(), h2.getX(), h2.getY());

    public AStar() {

    }

    public void searchPath(Hexagon start, Hexagon goal) {
        frontier.clear();
        frontier.add(new Pair<>(start, 0));
        cameFrom.clear();
        costSoFar.clear();
        cameFrom.put(start, null);
        costSoFar.put(start, start.getTerrainType().getMovementCost());

        while (!frontier.isEmpty()) {
            Hexagon current = (Hexagon) frontier.poll().getValue0();

            if (start.equals(goal)) {
                break;
            }

            for (Hexagon next : current.getNeighbors()) {
                int newCost = costSoFar.get(current) + next.getTerrainType().getMovementCost();
                if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
                    costSoFar.put(next, newCost);
                    Float priority = newCost + heuristic.apply(goal, next);
                    frontier.add(new Pair<>(next, priority));
                    cameFrom.put(next, current);

                }
            }
        }
    }
}
