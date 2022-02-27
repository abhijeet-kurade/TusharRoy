package Graphs.Bipartite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        int nodes = 8;
        int[][] edges = {{1,2},{2,3},{3,4},{4,5},{2,8}};
        System.out.println(isBipartite1(nodes, edges));

    }
    public static boolean isBipartite(int nodes, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node =1; node<=nodes; node++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        HashMap<Integer, Integer> coloring = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();
        for(int node : graph.keySet()){
            if(coloring.containsKey(node)) continue;
            queue.add(new int[]{node, 0});
            while(!queue.isEmpty()){
                int[] cn = queue.poll();
                int curr = cn[0], color=cn[1];
                coloring.put(curr, color);
                for(int neighbor : graph.get(curr)){
                    int clr = (color+1)%2;
                    if(coloring.containsKey(neighbor)){
                        if(coloring.get(neighbor) == clr) continue;
                        else return false;
                    }
                    queue.add(new int[]{neighbor, clr});
                }
            }
        }
        return true;

    }
    public static boolean isBipartite1(int nodes, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node =1; node<=nodes; node++) graph.put(node, new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        HashMap<Integer, Integer> coloring = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int node : graph.keySet()){
            if(coloring.containsKey(node)) continue;
            queue.add(node);
            int color = 0;
            while(!queue.isEmpty()){
                int s = queue.size();
                for(int i=0; i<s; i++){
                    int curr = queue.poll();
                    coloring.put(curr, color);
                    for(int neighbor : graph.get(curr)){
                        if(coloring.containsKey(neighbor)){
                            if(coloring.get(neighbor) == (color+1)%2) continue;
                            else return false;
                        }
                        queue.add(neighbor);
                    }
                }
                color = (color+1)%2;
            }
        }
        return true;

    }

}
