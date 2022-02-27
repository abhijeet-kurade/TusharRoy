package Graphs.Bridge;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class CutEdge {
    public static void main(String[] args) {
        int nodes = 15;
        int[][] edges = {{1,2},{2,3},{3,1},{4,2},{5,4},{5,3}, {5,6}, {6,7},{3,7},{6,2},{3,8},{8,9},{9,10},{8,10},{9,11},{11,12},{10,12},{11,13},{13,14},{13,15},{14,15}};
        System.out.println(bridges(nodes, edges, 1));
    }

    public static ArrayList<ArrayList<Integer>> bridges(int nodes, int[][] edges, int start){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> visit = new HashMap<>();
        HashMap<Integer, Integer> low = new HashMap<>();
        int M = Integer.MAX_VALUE;
        for(int node =1; node<=nodes; node++){
            graph.put(node, new ArrayList<>());
            visit.put(node, M);
            low.put(node, M);
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        ArrayList<ArrayList<Integer>> bridges = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        int[] time = {1};
        for(int node : graph.keySet()){
            if(visited.contains(node)) continue;
            depthFirstSearch(node, -1, visit, low, graph, time, visited, bridges);
        }
        return bridges;
    }

    public static int depthFirstSearch(int curr, int parent, HashMap<Integer, Integer> visit,
                                        HashMap<Integer, Integer> low, HashMap<Integer, ArrayList<Integer>> graph,
                                        int[] time, HashSet<Integer> visited, ArrayList<ArrayList<Integer>> bridges){


        visited.add(curr);
        visit.put(curr, time[0]);
        low.put(curr, time[0]);
        time[0] += 1;
        for(int neighbor : graph.get(curr)){
            if(visited.contains(neighbor)) continue;

            int knownLow = depthFirstSearch(neighbor, curr, visit, low, graph, time, visited, bridges);
            if(knownLow > visit.get(curr)){
                bridges.add(new ArrayList<>(Arrays.asList(curr, neighbor)));
            }

        }

        int lowest = Integer.MAX_VALUE;
        for(int neighbor : graph.get(curr)){
            if(neighbor == parent) continue;
            lowest = Math.min(lowest, low.get(neighbor));
        }
        low.put(curr, lowest);
        return lowest;
    }
}
