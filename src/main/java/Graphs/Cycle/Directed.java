package Graphs.Cycle;

import java.util.*;

public class Directed {
    public static void main(String[] args) {
        int nodes = 7;
        int[][] edges = {{1,2},{2,4},{2,5},{3,5},{3,6},{5,7},{7,1}};
        System.out.println(isCycle(nodes, edges));

    }
    public static boolean isCycle(int nodes, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node =1; node<=nodes; node++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges) graph.get(edge[0]).add(edge[1]);
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> path = new HashSet<>();
        for(int node : graph.keySet()){
            if(visited.contains(node)) continue;
            if(isCycleRec(node, visited, path, graph)) return true;
        }
        return false;
    }

    public static boolean isCycleRec(int curr, HashSet<Integer> visited, HashSet<Integer> path, HashMap<Integer, ArrayList<Integer>> graph){
        if(path.contains(curr)) return true;
        visited.add(curr);
        path.add(curr);
        for(int neighbor : graph.get(curr)){
            boolean res = isCycleRec(neighbor, visited, path, graph);
            if(res == true) return true;
        }
        path.remove(curr);
        return false;
    }


    public static boolean isCycleBFS(int nodes, int[][] edges) {
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
        return sorted.size() != nodes ;
    }
}
