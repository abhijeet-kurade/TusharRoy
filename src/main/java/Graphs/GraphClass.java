package Graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphClass {
    public static void main(String[] args) {
        int nodes = 4;
        int[][] edges = {{1,2},{1,4},{2,4},{4,3}};
        graphInHashMap(nodes, edges);
    }

    public static void graphInHashMap(int nodes, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

        for(int node = 1; node <= nodes; node ++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges){
            int s = edge[0], d = edge[1];
            graph.get(s).add(d);
            graph.get(d).add(s);
        }

        System.out.println(graph);

    }
}
