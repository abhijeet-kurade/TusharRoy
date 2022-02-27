package Graphs.TopologicalSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class DFS {
    public static void main(String[] args) {
        int nodes = 9;
        int[][] edges = {{1,2},{1,3},{1,5},{2,4},{3,6},{5,4},{4,7},{7,8},{7,9},{8,9}};
        System.out.println(topologicalSort(nodes, edges));

    }
    public static ArrayList<Integer> topologicalSort(int nodes, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node =1; node<=nodes; node++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
        }

        HashSet<Integer> visited = new HashSet<>();
        ArrayList<Integer> sorted = new ArrayList<>();

        for(int node : graph.keySet()){
            if(visited.contains(node)) continue;
            depthFirstSearch(node, visited, graph, sorted);
        }
        Collections.reverse(sorted);
        return sorted;
    }
    public static void depthFirstSearch(int curr, HashSet<Integer> visited, HashMap<Integer, ArrayList<Integer>> graph, ArrayList<Integer> sorted){
        if(visited.contains(curr)) return;
        visited.add(curr);
        for(int neighbor : graph.get(curr)){
            depthFirstSearch(neighbor, visited, graph, sorted);
        }
        sorted.add(curr);
    }
}
