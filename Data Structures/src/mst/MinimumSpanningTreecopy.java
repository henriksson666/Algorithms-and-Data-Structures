package mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Edge { 
    int src, dest, weight; 

    public Edge(int src, int dest, int weight) { 
        this.src = src;
        this.dest = dest;
        this.weight = weight; 
    } 
}

class Graph {
    int V, E;
    ArrayList<Edge> edges;

    public Graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new ArrayList<>(E);
    }

    void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    void kruskalMST() {
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        int[] parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;

        ArrayList<Edge> result = new ArrayList<>();

        int i = 0, e = 0;
        while (e < V - 1 && i < E) {
            Edge nextEdge = edges.get(i++);
            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);

            if (x != y) {
                result.add(nextEdge);
                union(parent, x, y);
                e++;
            }
        }

        System.out.println("Kruskal's Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        } 
    }

    int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        parent[xRoot] = yRoot;
    }

    void primMST() {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        boolean[] inMST = new boolean[V];
        ArrayList<Edge> result = new ArrayList<>();

        int startVertex = 0;
        inMST[startVertex] = true;

        for (Edge edge : edges) {
            if (edge.src == startVertex) {
                minHeap.add(edge);
            }
        }

        while (result.size() < V - 1) {
            if (minHeap.isEmpty()) {
                System.out.println("The graph is not connected.");
                return;
            }

            Edge minEdge = minHeap.poll();

            if (!inMST[minEdge.dest]) {
                inMST[minEdge.dest] = true;
                result.add(minEdge);

                for (Edge edge : edges) {
                    if (edge.src == minEdge.dest && !inMST[edge.dest]) {
                        minHeap.add(edge);
                    }
                }
            }
        }

        System.out.println("\nPrim's Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }
}

public class MinimumSpanningTreecopy {
   
    public static void main(String[] args) {
        int V = 5;
        int E = 5;

        Graph graph = new Graph(V, E);

        graph.addEdge(0, 1, 3);
        graph.addEdge(1, 4, 4);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 2, 2);
        graph.addEdge(0, 3, 3);

        graph.kruskalMST();

        graph.primMST();
    }
}