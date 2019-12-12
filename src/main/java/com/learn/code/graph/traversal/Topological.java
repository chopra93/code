package com.learn.code.graph.traversal;

import com.learn.code.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Chopra on 08/09/19.
 */
public class Topological{

    public List<Integer> topological(Graph g, int size){
        // time complexity O(V+E)

        boolean visited[] = new boolean[g.v];
        Stack<Integer> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();

        for (int i=0;i<size;i++)
            if (!visited[i])
                topologicalUtil(visited,stack,g,i);

        while (!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;
    }

    private void topologicalUtil(boolean[] visited, Stack<Integer> stack ,Graph g, int u){
        visited[u]=true;
        if (g.adj.containsKey(u)){
            List<Integer> adj = g.adj.get(u);
            for (int v:adj){
                if (!visited[v]){
                    topologicalUtil(visited,stack,g,v);
                }
            }
        }
        stack.push(u);
    }

    public static void main(String[] args) {
        Graph g = new Graph(2);
        g.addEdge(0,1,false);
//        g.addEdge(5, 2,true);
//        g.addEdge(5, 0,true);
//        g.addEdge(4, 0,true);
//        g.addEdge(4, 1,true);
//        g.addEdge(2, 3,true);
//        g.addEdge(3, 1,true);

        Topological topological = new Topological();
        List<Integer> res = topological.topological(g, 2);

        for (int i:res) {
            System.out.println(i);
        }

    }
}
