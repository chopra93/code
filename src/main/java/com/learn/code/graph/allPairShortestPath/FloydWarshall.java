package com.learn.code.graph.allPairShortestPath;

/**
 * Created by Chopra on 08/09/19.
 */
public class FloydWarshall {
    // all pair shortest path
    // O(V3)

    int v;
    int dist[][];

    FloydWarshall(int v){
        this.v = v;
        dist = new int[v][v];
        for (int i=0;i<v;i++){
            for (int j=0;j<v;j++){
                dist[i][j]= 99999;
            }
        }
    }

    public void allPairShortestPath(){

        for (int i=0;i<v;i++){
            for (int j=0;j<v;j++){
                for (int k=0;k<v;k++){
                    dist[j][k] = Math.min(dist[j][k],dist[j][i]+dist[i][k]);
                }
            }
        }

    }

    void addEdge(int u, int v, int w){
        dist[u][v]=w;
    }

    void print(){
        for (int i=0;i<v;i++){
            for (int j=0;j<v;j++){
                if (dist[i][j] == 99999)
                    System.out.print("- ");
                else
                    System.out.print(dist[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FloydWarshall floydWarshall = new FloydWarshall(4);
        floydWarshall.addEdge(0,3,10);
        floydWarshall.addEdge(0,1,5);
        floydWarshall.addEdge(1,2,3);
        floydWarshall.addEdge(2,3,1);

        floydWarshall.allPairShortestPath();
        floydWarshall.print();
    }
}
