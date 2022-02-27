package Graphs.Traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch {
    public static void main(String[] args) {
        int nodes = 7;
        int[][] edges = {{1,2},{1,5},{2,3},{2,4},{4,3},{5,4},{4,6},{4,7},{6,7},{3,6}};
        System.out.println(depthFirstSearch(nodes, edges, 4));
    }
    public static ArrayList<Integer> depthFirstSearch(int nodes, int[][] edges, int source){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node=1; node<=nodes; node++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        ArrayList<Integer> path = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        if(!visited.contains(source)) dfsIterative(source,  path, graph);


        return path;
    }

    public static void dfs(Integer node, HashSet<Integer> visited, ArrayList<Integer> path, HashMap<Integer, ArrayList<Integer>> graph){
        if(visited.contains(node)) return;
        visited.add(node);
        path.add(node);
        for(int neighbor : graph.get(node)){
            dfs(neighbor, visited, path, graph);
        }
    }
    public static void dfs1(Integer node, HashSet<Integer> visited, ArrayList<Integer> path, HashMap<Integer, ArrayList<Integer>> graph){
        visited.add(node);
        path.add(node);
        for(int neighbor : graph.get(node)){
            if(!visited.contains(neighbor)) dfs1(neighbor, visited, path, graph);
        }
    }

    public static void dfsIterative(Integer source, ArrayList<Integer> path, HashMap<Integer, ArrayList<Integer>> graph){
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.add(source);
        visited.add(source);

        while(!stack.isEmpty()){
            Integer curr = stack.pop();
            path.add(curr);
            for(int neighbor : graph.get(curr)){
                if(!visited.contains(neighbor)){
                    stack.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }


    }


}
