package com.learn.code.graph.traversal;

import com.learn.code.graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Chopra on 08/09/19.
 */
public class Bfs {
    private List<Integer> bfs(Graph g, int start){
        // time complexity O(V+E)

        List<Integer> res = new ArrayList<>();

        boolean visited[] = new boolean[g.v];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()){
            Integer u = queue.remove();
            res.add(u);
            if (g.adj.containsKey(u)){
                List<Integer> adj = g.adj.get(u);

                for (int v:adj){
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.add(v);
                    }
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0,2,true);
        g.addEdge(2,0,true);
        g.addEdge(0,1,true);
        g.addEdge(1,2,true);
        g.addEdge(2,3,true);
        g.addEdge(3,3,true);


        Bfs bfs = new Bfs();
        List<Integer> res = bfs.bfs(g, 2);

        for (int i:res){
            System.out.println(i);
        }
    }

}
