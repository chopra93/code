package com.learn.code.graph.traversal;

import com.learn.code.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chopra on 08/09/19.
 */
public class Dfs {

    private List<Integer> dfs(Graph g, int start) {
        // time complexity O(V+E)
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[g.v];

        dfsUtil(res,visited,g,start);
        return res;
    }

    private void dfsUtil(List<Integer> res, boolean[] visited, Graph g, int u){
        visited[u] = true;
        res.add(u);

        if (g.adj.containsKey(u)){
            List<Integer> adj = g.adj.get(u);
            for (int v:adj){
                if (!visited[v]){
                    dfsUtil(res,visited,g,v);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0,2,true);
        g.addEdge(2,0,true);
        g.addEdge(0,1,true);
        g.addEdge(1,2,true);
        g.addEdge(2,3,true);
        g.addEdge(3,3,true);


        Dfs dfs = new Dfs();
        List<Integer> res = dfs.dfs(g, 2);

        for (int i:res){
            System.out.println(i);
        }
    }
}
