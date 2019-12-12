package com.learn.code.graph.minimumSpanningTree;

import com.learn.code.graph.Graph;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by Chopra on 09/09/19.
 */
public class GraphV2 extends Graph {

    public int e;
    public PriorityQueue<Edge> edges;
    public Map<Integer,ParentRank> parentRankMap;
    public Map<Integer,List<Pair<Integer,Integer>>> adjWithWeight;

    class Edge implements Comparable<Edge>{
        int u;
        int v;
        int w;

        Edge(int u, int v, int w){
            this.u=u;
            this.v=v;
            this.w=w;
        }

        @Override
        public int compareTo(Edge e){
            if(this.w<e.w)
                return -1;
            if(this.w>e.w)
                return 1;
            return 0;
        }
    }

    class ParentRank{
        int parent;
        int rank;

        ParentRank(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    public GraphV2(int v, int e){
        super(v);
        this.e=e;
        this.edges = new PriorityQueue<>();
        this.parentRankMap = new HashMap<>();
        this.adjWithWeight = new HashMap<>();

        for (int i=0;i<v;i++){
            parentRankMap.put(i,new ParentRank(-1,0));
        }

        for (int i=0;i<v;i++){
            adjWithWeight.put(i,new ArrayList<>());
        }
    }

    public void addEdgeToPriorityQueue(int u, int v, int w) {
        edges.add(new Edge(u,v,w));
    }

    public void addEdge(int u, int v, int w, boolean isDirected){
        if (!adjWithWeight.containsKey(u)){
            adjWithWeight.put(u,new ArrayList<>());
        }
        adjWithWeight.get(u).add(new Pair<>(v,w));

        if (!isDirected){
            if (!adjWithWeight.containsKey(v)){
                adjWithWeight.put(v,new ArrayList<>());
            }
            adjWithWeight.get(v).add(new Pair<>(u,w));
        }
    }

}
