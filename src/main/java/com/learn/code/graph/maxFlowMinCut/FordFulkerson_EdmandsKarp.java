package com.learn.code.graph.maxFlowMinCut;

import java.util.*;

/**
 * Created by Chopra on 08/09/19.
 */
public class FordFulkerson_EdmandsKarp {

    int v;
    Map<Integer,Map<Integer,Integer>> adj;

    FordFulkerson_EdmandsKarp(int v){
        this.v = v;
        this.adj = new HashMap<>();
        for(int i=0;i<v;i++){
            adj.put(i,new HashMap<>());
        }
    }

    private boolean bfs(int s, int t, int[] parent){
        Set<Integer> visited = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);
        parent[s]=-1;

        while (!queue.isEmpty()){
            if (visited.contains(t))
                break;

            int u = queue.remove();
            Map<Integer, Integer> integerIntegerMap = adj.get(u);

            for (Map.Entry<Integer,Integer> entry:integerIntegerMap.entrySet()){
                int v = entry.getKey();
                if (!visited.contains(v) && adj.get(u).get(v)!=0){
                    visited.add(v);
                    parent[v]=u;
                    queue.add(v);
                }
            }
        }
        return visited.contains(t);

    }

    public int minCutMaxFlow(int s, int t){
        int maxFLow = 0;
        int[] parent = new int[v];

        while (bfs(s,t,parent)){
            int minCut = Integer.MAX_VALUE;
            for (int v=t;v!=s;v=parent[v]){
                int u = parent[v];
                minCut = Math.min(minCut,adj.get(u).get(v));
            }
            for (int v=t;v!=s;v=parent[v]){
                int u = parent[v];
                if (!adj.get(u).containsKey(v)){
                    adj.get(u).put(v,0);
                }
                adj.get(u).put(v,adj.get(u).get(v)-minCut);

                if (!adj.get(v).containsKey(u)){
                    adj.get(v).put(u,0);
                }
                adj.get(v).put(u,adj.get(v).get(u)+minCut);
            }
            maxFLow+=minCut;
        }
        return maxFLow;
    }

    public void addEdge(int u, int v, int w){
        if (!adj.containsKey(u)){
            adj.put(u,new HashMap<>());
        }
        adj.get(u).put(v,w);
    }
    public static void main(String[] args) {
        FordFulkerson_EdmandsKarp fordFulkerson_edmandsKarp = new FordFulkerson_EdmandsKarp(6);
        fordFulkerson_edmandsKarp.addEdge(0,1,16);
        fordFulkerson_edmandsKarp.addEdge(0,2,13);
        fordFulkerson_edmandsKarp.addEdge(1,2,10);
        fordFulkerson_edmandsKarp.addEdge(2,1,4);
        fordFulkerson_edmandsKarp.addEdge(1,3,12);
        fordFulkerson_edmandsKarp.addEdge(3,2,9);
        fordFulkerson_edmandsKarp.addEdge(2,4,14);
        fordFulkerson_edmandsKarp.addEdge(4,3,7);
        fordFulkerson_edmandsKarp.addEdge(3,5,20);
        fordFulkerson_edmandsKarp.addEdge(4,5,4);

        System.out.println(fordFulkerson_edmandsKarp.minCutMaxFlow(0,5));


    }
}
