package GraphDemo;

import java.util.*;

//Class representing a graph
class Graph {
 private int V; // Number of vertices
 private LinkedList<Integer>[] adjacencyList; // Adjacency list representation

 // Constructor to initialize the graph
 public Graph(int vertices) {
     this.V = vertices;
     this.adjacencyList = new LinkedList[V];
     for (int i = 0; i < V; i++) {
         adjacencyList[i] = new LinkedList<>();
     }
 }

 // Method to add an edge between two vertices
 public void addEdge(int v, int w) {
     adjacencyList[v].add(w);
     adjacencyList[w].add(v); // Undirected graph, so add edge in both directions
 }

//Method to print the graph
public void printGraph() {
  for (int i = 0; i < V; ++i) {
      System.out.print("Node " + i + " is connected to: ");
      // Create a set to keep track of visited nodes
      Set<Integer> visited = new HashSet<>();
      for (Integer neighbor : adjacencyList[i]) {
          // If the neighbor hasn't been visited before, print it
          if (!visited.contains(neighbor)) {
              System.out.print(neighbor + " ");
              visited.add(neighbor);
          }
      }
      System.out.println();
  }
}

 // Method for breadth-first traversal
 public void breadthFirstTraversal(int startNode) {
     boolean[] visited = new boolean[V]; // Array to mark visited nodes
     Queue<Integer> queue = new LinkedList<>(); // Queue for BFS traversal

     visited[startNode] = true; // Mark the starting node as visited
     queue.add(startNode); // Add the starting node to the queue

     System.out.println("Breadth-First Traversal starting from node " + startNode + ": ");

     while (!queue.isEmpty()) { // Continue until the queue is empty
         int current = queue.poll(); // Remove and retrieve the first element from the queue
         System.out.print(current + " "); // Print the current node

         // Visit all neighbors of the current node
         for (Integer neighbor : adjacencyList[current]) {
             if (!visited[neighbor]) { // If neighbor is not visited
                 visited[neighbor] = true; // Mark it as visited
                 queue.add(neighbor); // Add it to the queue for further traversal
             }
         }
     }
     System.out.println();
 }
}

//Class for demonstration
public class GraphDemo {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     System.out.print("Enter the number of nodes in the graph: ");
     int numNodes = scanner.nextInt();

     Graph graph = new Graph(numNodes); // Create a graph instance

     System.out.println("Enter the connections between nodes (e.g., enter '0 1' for an edge between nodes 0 and 1):");
     for (int i = 0; i < numNodes; i++) {
         System.out.print("Connections for node " + i + " (enter -1 to finish): ");
         int connectedNode;
         while ((connectedNode = scanner.nextInt()) != -1) { // Keep reading connections until -1 is entered
             graph.addEdge(i, connectedNode); // Add edge between nodes
         }
     }

     System.out.println("Graph representation:");
     graph.printGraph(); // Print the graph

     System.out.print("Enter the starting node for breadth-first traversal: ");
     int startNode = scanner.nextInt();
     graph.breadthFirstTraversal(startNode); // Perform breadth-first traversal

     scanner.close();
 }
}