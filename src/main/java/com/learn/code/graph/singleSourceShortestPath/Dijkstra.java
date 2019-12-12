package com.learn.code.graph.singleSourceShortestPath;

import com.learn.code.graph.minimumSpanningTree.GraphV2;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by Chopra on 08/09/19.
 */
public class Dijkstra {
    // undirected
    // O(ELogV)

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

    // single source shortes path algorithm with no negative edge
    public List<Edge> dijkastra(int i, GraphV2 g) {
        List<Edge> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(i,0,-1));

        while (!priorityQueue.isEmpty()){
            Node curr = priorityQueue.poll();

            if (!visited.contains(curr.v)){
                res.add(new Edge(curr.p,curr.v,curr.d));
            }

            visited.add(curr.v);

            List<Pair<Integer, Integer>> pairs = g.adjWithWeight.get(curr.v);

            for (Pair<Integer,Integer> p:pairs){
                if (!visited.contains(p.getKey())){
                    priorityQueue.add(new Node(p.getKey(),curr.d+p.getValue(),curr.v));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        GraphV2 g = new GraphV2(5,8);
        g.addEdge(0,1,4,false);
        g.addEdge(0,7,8,false);
        g.addEdge(1,7,11,false);
        g.addEdge(1,2,8,false);
        g.addEdge(2,8,2,false);
        g.addEdge(8,7,7,false);
        g.addEdge(7,6,1,false);
        g.addEdge(8,6,6,false);

        g.addEdge(2,3,7,false);
        g.addEdge(2,5,4,false);
        g.addEdge(6,5,2,false);

        g.addEdge(3,5,14,false);
        g.addEdge(3,4,9,false);
        g.addEdge(4,5,10,false);


        Dijkstra dijkstra = new Dijkstra();
        List<Edge> dijkastraResponse = dijkstra.dijkastra(0, g);

        for (Edge e:dijkastraResponse){
            System.out.println(e.v+" - "+e.w);
        }
    }

}
