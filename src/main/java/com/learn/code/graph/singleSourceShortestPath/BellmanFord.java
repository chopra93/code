package com.learn.code.graph.singleSourceShortestPath;

import com.learn.code.graph.minimumSpanningTree.GraphV2;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chopra on 08/09/19.
 */
public class BellmanFord {
    // single source shortest path with negative edge value
    // directed graph O(V*E)

    GraphV2 g;
    Map<Integer,Integer> distance;

    BellmanFord(GraphV2 g){
        this.g = g;
        this.distance = new HashMap<>();
    }

    public void singleSourceShortestPath(int source){

        for(int i=0;i<g.v;i++){
            distance.put(i,Integer.MAX_VALUE);
        }

        distance.put(source,0);

        int i=1;
        while (i<g.v){
            for(int u=0;u<g.v;u++){
                List<Pair<Integer, Integer>> pairs = g.adjWithWeight.get(u);
                for (Pair<Integer,Integer> pair: pairs){
                    int v = pair.getKey();
                    int d = pair.getValue();

                    if (distance.get(u)!=Integer.MAX_VALUE && distance.get(u)+d < distance.get(v)){
                        distance.put(v,distance.get(u)+d);
                    }
                }
            }
            i++;
        }

        for(int u=0;u<g.v;u++){
            List<Pair<Integer, Integer>> pairs = g.adjWithWeight.get(u);
            for (Pair<Integer,Integer> pair: pairs){
                int v = pair.getKey();
                int d = pair.getValue();

                if (distance.get(v)!=Integer.MAX_VALUE && distance.get(u)+d < distance.get(v)){
                    System.out.println("error contain cycle with negative");;
                }
            }
        }

    }

    public static void main(String[] args) {
        GraphV2 g = new GraphV2(5,8);
        g.addEdge(0,1,-1,true);
        g.addEdge(0,2,4,true);
        g.addEdge(1,2,3,true);
        g.addEdge(3,2,5,true);
        g.addEdge(3,1,1,true);
        g.addEdge(1,3,2,true);
        g.addEdge(1,4,2,true);
        g.addEdge(4,3,-3,true);


        BellmanFord bellmanFord = new BellmanFord(g);
        bellmanFord.singleSourceShortestPath(0);

        for (Map.Entry<Integer,Integer> entry: bellmanFord.distance.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
    }
}
