package com.learn.code.graph.minimumSpanningTree;


import com.learn.code.graph.Graph;

import java.util.*;

/**
 * Created by Chopra on 08/09/19.
 */
public class ArticulationPoint {
    // undirected connected graph
    // O(V+E)

    class Node{
        Map<Integer,Integer> d;
        Map<Integer,Integer> p;
        Map<Integer,Integer> l;

        Node(){
            this.d = new HashMap<>();
            this.p = new HashMap<>();
            this.l = new HashMap<>();
        }
    }

    int time;
    public Set<Integer> articulationPoints(Graph g){
        int v = g.v;
        time = 0;

        Set<Integer> res = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        Node store = new Node();
        Map<Integer, List<Integer>> adj = g.adj;

        for(int i=0;i<v;i++){
            store.p.put(i,-1);
            if (!visited.contains(i))
                articulationPointsUtil(i,res,visited,store,adj);
        }

        return res;
    }

    private void articulationPointsUtil(int i, Set<Integer> res, Set<Integer> visited, Node store, Map<Integer, List<Integer>> adj){
        visited.add(i);

        int child = 0;
        store.d.put(i,time);
        store.l.put(i,time);

        time++;

        List<Integer> adjacent = adj.get(i);
        for (int j:adjacent){
            if (!visited.contains(j)){
                child++;
                store.p.put(j,i);
                articulationPointsUtil(j,res,visited,store,adj);

                store.l.put(i,Math.min(store.l.get(i),store.l.get(j)));

                if (store.p.get(i)==-1 && child>=2)
                    res.add(i);

                if (store.p.get(i)!=-1 && store.l.get(j)>=store.d.get(i))
                    res.add(i);
            }
            else{
                if (j!=store.p.get(i)){
                    store.l.put(i,Math.min(store.d.get(j),store.l.get(i)));
                }
            }
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0,1,false);
        graph.addEdge(0,2,false);
        graph.addEdge(0,3,false);
        graph.addEdge(3,4,false);

        ArticulationPoint articulationPoint = new ArticulationPoint();
        Set<Integer> points = articulationPoint.articulationPoints(graph);

        for (int i:points){
            System.out.println(i);
        }
    }
}
