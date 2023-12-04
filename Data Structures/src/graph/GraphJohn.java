/********************************************************************************
 * Name: Joao Henrique Silva Pinto (202210958)
 * Function: Graph analyzer and classifier
 * Date: 2022-11-04
 * Subject: Data Structures and Algorithms
 * Course: Computer Science
 * University: Universidade Estadual do Sudoeste da Bahia (UESB), Brazil
 * Professor: Maria Luisa Ghizoni Gonzalez
 * ******************************************************************************/

package graph;

import java.util.Arrays;
import java.util.Scanner;

public class GraphJohn { // Graph class
    public static void main(String[] args) { // main method

        Scanner scanner = new Scanner(System.in); // scanner object

        System.out.print("Enter the number of vertices: "); // prompt user for number of vertices
        int numVertices = scanner.nextInt(); // store number of vertices

        int[][] adjMatrix = new int[numVertices][numVertices]; // adjacency matrix

        System.out.println("Enter the adjacency matrix row by row (0 or 1+ for each edge):"); // prompt user for adjacency matrix
        for (int i = 0; i < numVertices; i++) { // loop through rows
            for (int j = 0; j < numVertices; j++) { // loop through columns
                adjMatrix[i][j] = scanner.nextInt(); // store value in adjacency matrix
            } // end inner for
        } // end outer for

        scanner.close(); // close scanner

        analyzeGraph(adjMatrix); // analyze and classify graph
    } // end main

    /**
     * Analyzes and classifies a graph based on its adjacency matrix
     * @param adjMatrix Adjacency matrix of the graph
     * @return A string with the classification of the graph
     */
    public static void analyzeGraph(int[][] adjMatrix) { // analyzeGraph method
        int numVertices = adjMatrix.length; // number of vertices
        boolean hasLoop = false; // has loop
        boolean isSimple = true; // is simple
        boolean isComplete = true; // is complete
        boolean isConnected = true; // is connected
        boolean isPseudograph = false; // is pseudograph
        boolean isMultigraph = false; // is multigraph
        boolean isDirectedMultigraph = false; // is directed multigraph
        int[] degrees = new int[numVertices]; // degrees of vertices (for simple graphs)
        int[] inDegrees = new int[numVertices]; // in-degrees of vertices (for directed graphs)
        int[] outDegrees = new int[numVertices]; // out-degrees of vertices (for directed graphs)

        for (int i = 0; i < numVertices; i++) { // loop through rows
            for (int j = 0; j < numVertices; j++) { // loop through columns
                if (i == j && adjMatrix[i][j] >= 1) { // if there is a loop
                    hasLoop = true; // set hasLoop to true
                } // end if

                if (i != j) { // if not a loop
                    int edgeCount = adjMatrix[i][j]; // store edge count
                    if (edgeCount > 1) { // if there is more than one edge
                        isMultigraph = true; // set isMultigraph to true
                    } // end if

                    if (edgeCount > 0) { // if there is an edge
                        degrees[i] += edgeCount; // increment degree of vertex
                        outDegrees[i] += edgeCount; // increment out-degree of vertex
                        inDegrees[j] += edgeCount; // increment in-degree of vertex
                    } // end if
                } // end if

                if (i != j && adjMatrix[i][j] == 0) { // if there is no edge
                    isComplete = false; // set isComplete to false
                }
            } // end inner for
        } // end outer for

        for (int i = 1; i < numVertices; i++) { // loop through degrees
            if (!isConnected(adjMatrix, 0, i, new boolean[numVertices])) { // if not connected
                isConnected = false; // set isConnected to false
                break; // break loop
            }
        } // end for

        if (!isDirected(adjMatrix) && hasLoop && !isMultigraph) { // if simple graph
            isPseudograph = true; // set isPseudograph to true
        } // end if

        if (isDirected(adjMatrix) && hasLoop && isMultigraph) { // if directed multigraph
            isDirectedMultigraph = true; // set isDirectedMultigraph to true
        } // end if

        System.out.println("Adjacency Matrix:"); // print adjacency matrix
        printMatrix(adjMatrix); // print adjacency matrix

        System.out.println("Graph properties:"); // print graph properties
        System.out.println("Is Simple: " + isSimple); // print isSimple
        System.out.println("Is Complete: " + isComplete); // print isComplete
        System.out.println("Has Loop: " + hasLoop); // print hasLoop
        System.out.println("Is Connected: " + isConnected); // print isConnected

        if (isPseudograph) { // if isPseudograph
            System.out.println("Graph is a Pseudograph"); // print isPseudograph
        } else if (isDirectedMultigraph) { // if isDirectedMultigraph
            System.out.println("Graph is a Directed Multigraph"); // print isDirectedMultigraph
        } else if (isMultigraph) { // if isMultigraph
            System.out.println("Graph is Multigraph"); // print isMultigraph
        } else if (isDirected(adjMatrix)) { // if isDirected
            System.out.println("Graph is a Directed Graph"); // print isDirected
        } else { // if none of the above
            System.out.println("Graph is a Simple Graph"); // print isSimple
        } // end if

        if (!isDirected(adjMatrix)) { // if not directed
            System.out.println("Degrees of Vertices: " + Arrays.toString(degrees)); // print degrees
        }

        if (isDirected(adjMatrix)) { // if directed
            System.out.println("Degrees of Vertices:  "); // print degrees
            System.out.println("\tIn-Degrees: " + Arrays.toString(inDegrees)); // print in-degrees
            System.out.println("\tOut-Degrees: " + Arrays.toString(outDegrees)); // print out-degrees
        } // end if
    } // end analyzeGraph

    /**
     * Checks if two vertices are connected in a graph using DFS algorithm (recursive)
     * @param adjMatrix Adjacency matrix of the graph
     * @param start Starting vertex
     * @param target Target vertex
     * @param visited Array of visited vertices
     * @return True if the vertices are connected, false otherwise
     */
    public static boolean isConnected(int[][] adjMatrix, int start, int target, boolean[] visited) { // isConnected method
        if (start == target) { // if start is target
            return true; // return true
        } // end if

        visited[start] = true; // set start to visited

        for (int i = 0; i < adjMatrix.length; i++) { // loop through vertices
            if (adjMatrix[start][i] >= 1 && !visited[i]) { // if there is an edge and vertex is not visited
                if (isConnected(adjMatrix, i, target, visited)) { // if connected
                    return true; // return true
                } // end if
            } // end if
        } // end for

        visited[start] = false; // set start to not visited
        return false; // return false
    } // end isConnected

    /**
     * Checks if a graph is directed or not based on its adjacency matrix (symmetric)
     * @param adjMatrix Adjacency matrix of the graph
     * @return True if the graph is directed, false otherwise
     */
    public static boolean isDirected(int[][] adjMatrix) { // isDirected method
        for (int i = 0; i < adjMatrix.length; i++) { // loop through rows
            for (int j = 0; j < adjMatrix.length; j++) { // loop through columns
                if (adjMatrix[i][j] != adjMatrix[j][i]) { // if not symmetric
                    return true; // return true
                }
            } // end inner for
        } // end outer for
        return false;
    } // end isDirected

    /**
     * Prints a matrix in a readable format (for debugging) (not used in final version)
     * @param matrix Matrix to be printed
     * @return A string with the matrix
     */
    public static void printMatrix(int[][] matrix) { // printMatrix method
        System.out.print("["); // print opening bracket
        for (int i = 0; i < matrix.length; i++) { // loop through rows
            System.out.print(" ["); // print opening bracket
            for (int j = 0; j < matrix[i].length; j++) { // loop through columns
                System.out.print(matrix[i][j]); // print value
                if (j < matrix[i].length - 1) { // if not last column
                    System.out.print(", "); // print comma
                } // end if
            } // end inner for

            System.out.print("]"); // print closing bracket

            if (i < matrix.length - 1) { // if not last row
                System.out.println(","); // print comma
            } else { // if last row
                System.out.println(" ]"); // print closing bracket
            } // end if
        } // end outer for
    } // end printMatrix
} // end Graph class