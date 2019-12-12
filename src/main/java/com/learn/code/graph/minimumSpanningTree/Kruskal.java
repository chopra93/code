package com.learn.code.graph.minimumSpanningTree;

import com.learn.code.graph.minimumSpanningTree.GraphV2.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Chopra on 08/09/19.
 */
public class Kruskal {
    // Minimum spanning tree  O(ELogE + VLogE)
    // union find pattern

    public int find(int u, Map<Integer,ParentRank> parentRankMap){
        if (parentRankMap.get(u).parent!= -1)
            return find(parentRankMap.get(u).parent,parentRankMap);
        return u;
    }

    public void union(int pu, int pv, Map<Integer,ParentRank> parentRankMap){
        int puu =  find(pu,parentRankMap);
        int pvv = find(pv,parentRankMap);

        if (parentRankMap.get(puu).rank < parentRankMap.get(pv).rank){
            parentRankMap.get(puu).parent = pvv;
        }
        else
        if(parentRankMap.get(puu).rank > parentRankMap.get(pv).rank){
            parentRankMap.get(pvv).parent = puu;
        }
        else {
            parentRankMap.get(puu).parent = pvv;
            parentRankMap.get(pvv).rank++;
        }
    }

    public List<Edge> spanningTree(GraphV2 g){
        List<Edge> res = new ArrayList<>();

        PriorityQueue<Edge> pq = g.edges;
        int ver = g.v;
        Map<Integer, ParentRank> parentRankMap = g.parentRankMap;

        int i = 0;
        while (i<(ver)){
            Edge currEdge = pq.poll();

            int u = currEdge.u;
            int v = currEdge.v;

            int pu = find(u,parentRankMap);
            int pv = find(v,parentRankMap);

            if (pu!=pv){
                res.add(currEdge);
                union(pu,pv,parentRankMap);
            }
            i++;
        }

        return res;
    }
    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal();

        GraphV2 g = new GraphV2(4,5);
        g.addEdgeToPriorityQueue(0,1,10);

        g.addEdgeToPriorityQueue(0,2,6);
        g.addEdgeToPriorityQueue(0,3,5);
        g.addEdgeToPriorityQueue(1,3,15);
        g.addEdgeToPriorityQueue(2,3,4);

        List<Edge> edges = kruskal.spanningTree(g);
        for (Edge e:edges){
            System.out.println(e.u +" - "+e.v+" : "+e.w);
        }
    }

}
