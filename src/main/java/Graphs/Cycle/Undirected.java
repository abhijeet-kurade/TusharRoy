package Graphs.Cycle;

import java.util.*;

public class Undirected {
    public static void main(String[] args) {
        int nodes = 7;
        int[][] edges = {{1,2},{2,4},{2,5},{3,5},{3,6},{5,7}};
        System.out.println(isCycleDFSRecursive(nodes, edges));
    }

    public static boolean isCycleBFS(int nodes, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node =1; node<=nodes; node++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Queue<int[]> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        for(int node =1; node<=nodes; node++){
            if(!visited.contains(node)){
                queue.add(new int[]{node, -1});
                visited.add(node);
                while (!queue.isEmpty()){
                    int[] nd = queue.poll();
                    int curr = nd[0], parent = nd[1];
                    for(int neighbor : graph.get(curr)){
                        if(neighbor == parent) continue;
                        if(visited.contains(neighbor)) return true;
                        queue.add(new int[]{neighbor, curr});
                        visited.add(neighbor);
                    }
                }
            }
        }

        return false;
    }

    public static boolean isCycleDFSRecursive(int nodes, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node =1; node<=nodes; node++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        HashSet<Integer> visited = new HashSet<>();

        for(int node =1; node<=nodes; node++){
            if(!visited.contains(node)){
                if(isCycleRec(node, -1, visited, graph)) return true;
            }
        }
        return false;
    }
    public static boolean isCycleRec(int curr, int parent, HashSet<Integer> visited, HashMap<Integer, ArrayList<Integer>> graph){
        if(visited.contains(curr)) return true;
        visited.add(curr);
        for(int neighbor : graph.get(curr)){
            if(parent == neighbor) continue;
            boolean res = isCycleRec(neighbor, curr, visited, graph);
            if(res == true) return true;
        }
        return false;
    }
}
