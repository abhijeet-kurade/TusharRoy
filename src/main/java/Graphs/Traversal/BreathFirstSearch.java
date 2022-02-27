package Graphs.Traversal;

import java.util.*;

public class BreathFirstSearch {
    public static void main(String[] args) {
        int nodes = 7;
        int[][] edges = {{1,2},{1,5},{2,3},{2,4},{4,3},{5,4},{4,6},{4,7},{6,7},{3,6}};
        System.out.println(breathFirstSearchLevelWise(nodes, edges, 6));

    }
    public static ArrayList<Integer> breathFirstSearch(int nodes, int[][] edges, int source){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node=1; node<=nodes; node++) graph.put(node, new ArrayList<>());

        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<Integer> path = new ArrayList<>();
        queue.add(source);
        visited.add(source);

        while(!queue.isEmpty()){
            int curr = queue.poll();
            path.add(curr);
            for(int neighbor : graph.get(curr)){
                if(!visited.contains(neighbor)){
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return path;
    }

    public static ArrayList<ArrayList<Integer>> breathFirstSearchLevelWise(int nodes, int[][] edges, int source){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node=1; node<=nodes; node++) graph.put(node, new ArrayList<>());

        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<ArrayList<Integer>> path = new ArrayList<>();
        queue.add(source);
        visited.add(source);

        while(!queue.isEmpty()){
            int s = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for(int i=0; i<s; i++){
                int curr = queue.poll();
                level.add(curr);
                for(int neighbor : graph.get(curr)){
                    if(!visited.contains(neighbor)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            path.add(level);
        }
        return path;
    }
}
