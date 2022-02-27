package Graphs.TopologicalSort;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class BFS {
    public static void main(String[] args) {
        int nodes = 9;
        int[][] edges = {{1,2},{1,3},{1,5},{2,4},{3,6},{5,4},{4,7},{7,8},{7,9},{8,9}};
        System.out.println(topologicalSort(nodes, edges));
    }
    public static ArrayList<Integer> topologicalSort(int nodes, int[][] edges) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        for (int node = 1; node <= nodes; node++){
            graph.put(node, new ArrayList<>());
            inDegree.put(node, 0);
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        for(int node : graph.keySet()){
            for(int neighbor : graph.get(node)){
                inDegree.put(neighbor, inDegree.get(neighbor)+1);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int node : inDegree.keySet()){
            if(inDegree.get(node) == 0) queue.add(node);
        }
        ArrayList<Integer> sorted = new ArrayList<>();

        while (!queue.isEmpty()){
            int curr = queue.poll();
            sorted.add(curr);
            for (int neighbor : graph.get(curr)){
                if(inDegree.get(neighbor) == 0) continue;
                inDegree.put(neighbor, inDegree.get(neighbor)-1);
                if(inDegree.get(neighbor) == 0) queue.add(neighbor);
            }
        }
        if(sorted.size() != nodes) return new ArrayList<>();
        return sorted;
    }
}
