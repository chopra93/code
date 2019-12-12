package com.learn.code.graph.minimumSpanningTree;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by Chopra on 08/09/19.
 */
public class Prims {
    // Minimum spanning tree
    // O(ELogV)
    int v;
    Map<Integer,List<Pair<Integer,Integer>>> adj;

    Prims(int v){
        this.v = v;
        this.adj = new HashMap<>();
    }

    class Edge{
        int u;
        int v;
        int w;

        Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    class Node implements Comparable<Node>{
        int v;
        int d;
        int p;

        Node(int v, int d, int p){
            this.v = v;
            this.d = d;
            this.p = p;
        }

        @Override
        public int compareTo(Node n){
            if (this.d<n.d)
                return -1;
            if(this.d>n.d)
                return 1;
            return 0;
        }
    }

    public void addEdge(int u, int v, int w){
        if (!adj.containsKey(u)){
            adj.put(u,new ArrayList<>());
        }
        adj.get(u).add(new Pair<>(v,w));

        if (!adj.containsKey(v)){
            adj.put(v,new ArrayList<>());
        }
        adj.get(v).add(new Pair<>(u,w));
    }

    public List<Edge> spanningTree(int i) {
        List<Edge> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(i,0,-1));

        while (!priorityQueue.isEmpty()){
            Node curr = priorityQueue.poll();

            if (!visited.contains(curr.v) && curr.p!=-1){
                res.add(new Edge(curr.p,curr.v,curr.d));
            }

            visited.add(curr.v);

            List<Pair<Integer,Integer>> adjtocurr = adj.get(curr.v);

            for (Pair<Integer,Integer> p:adjtocurr){
                if (!visited.contains(p.getKey())){
                    priorityQueue.add(new Node(p.getKey(),p.getValue(),curr.v));
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Prims prims = new Prims(5);
        prims.addEdge(0,1,2);
        prims.addEdge(0,3,6);
        prims.addEdge(1,2,3);
        prims.addEdge(1,3,8);
        prims.addEdge(1,4,5);
        prims.addEdge(2,4,7);
        prims.addEdge(3,4,9);

        List<Edge> edges = prims.spanningTree(0);

        for (Edge e : edges) {
            System.out.println(e.u + " - " + e.v + " : " + e.w);
        }

    }
}
