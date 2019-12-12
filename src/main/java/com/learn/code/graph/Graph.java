package com.learn.code.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chopra on 08/09/19.
 */
public class Graph {
    public int v;
    public Map<Integer, List<Integer>> adj;

    public Graph(int v) {
        this.v = v;
        this.adj = new HashMap<>();
    }

    public void addEdge(int u, int v, boolean isDirected) {
        if (isDirected) {
            if (!adj.containsKey(u)) {
                adj.put(u, new LinkedList<>());
            }
            adj.get(u).add(v);
        } else {
            if (!adj.containsKey(u)) {
                adj.put(u, new LinkedList<>());
            }
            adj.get(u).add(v);

            if (!adj.containsKey(v)) {
                adj.put(v, new LinkedList<>());
            }
            adj.get(v).add(u);
        }
    }
}
