package Graphs.Bipartite;

import java.util.ArrayList;
import java.util.HashMap;

public class DFS {
    public static void main(String[] args) {
        int nodes = 8;
        int[][] edges = {{1,2},{2,3},{3,4},{4,5},{2,8}};
        System.out.println(isBipartite(nodes, edges));
    }

    public static boolean isBipartite(int nodes, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node =1; node<=nodes; node++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        HashMap<Integer, Integer> coloring = new HashMap<>();

        for(int node : graph.keySet()){
            if(coloring.containsKey(node)) continue;
            boolean res = isBipartite(nodes, 0, coloring, graph);
            if(res == false) return false;
        }

        return true;

    }

    public static boolean isBipartite(int curr, int lastColor,  HashMap<Integer, Integer> coloring, HashMap<Integer, ArrayList<Integer>> graph){
        if(coloring.containsKey(curr)){
            if(coloring.get(curr) == (lastColor+1)%2) return true;
            else return false;
        }
        coloring.put(curr, (lastColor+1)%2);
        for(int neighbor : graph.get(curr)){
            boolean res = isBipartite(neighbor, (lastColor+1)%2, coloring, graph);
            if(res == false) return false;
        }
        return true;
    }
}
