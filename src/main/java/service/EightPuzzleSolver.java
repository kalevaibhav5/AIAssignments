package service;

import domain.Node;

import java.util.*;

public class EightPuzzleSolver {

   /**
    * @Author: Vaibhav Kale
    * This Class solves 8 puzzle algorithm using 3 known searching techniques -
    * 1) Breadth First Search
    * 2) Depth First Search
    * 3) Uniform Cost Search
    **/


    /**
     * Breadth First Algorithm
     * uses Node class to hold the 3x3 state and configurations of Initial and goal configurations.
     * @param initialNode
     * @param goalNode
     */
    public static void bfsSearch(Node initialNode, Node goalNode){

        //Initialize the path. This will hold list of actions taken to reach goal configurations from the initial configuration.
        String path = "";

        //Initialized countered to get total number of nodes compared in the search algorithm.

        Integer nodeCompared = 0;

        /*Initialize the Frontier with an empty queue to add successor nodes. Queue data structure will enforce BFS algorithm behavior by adding all nodes at same level
          and retrieve them one by one before expanding any successor nodes at next level.
         */

        Queue<Node> frontier = new LinkedList<>();

        // Add the root node to frontier.

        frontier.add(initialNode);

        //Initialize explored nodes to Set data structure. This will ensure that once a node is compared it will not be added back to frontier again.
        // Also if a node repeats it will not be added again to explored set.

        Set<Node> exploredNodes = new HashSet<>();

        //Loop and compare while frontier is not empty or the goal state is reached

        while(!frontier.isEmpty()){
            //get the first node in the queue (FIFO)

            Node currentNode = frontier.remove();

            //add the Node to the path this will be either Root, U for UP , L for Left, R for Right and D for Down actions on the blank space ( represented by 0)
            path = path.concat(currentNode.nodeType.getNodeType() + "  ");

            //Add the Node to explored node so it will not be added to Frontier next time.
            exploredNodes.add(currentNode);

            //Increment the node comparision counter.

            nodeCompared++;

            //Print the nodes compared after every 100 comparisions.

            if(nodeCompared % 100 == 0){
                System.out.println("Total comparisons so far: " + nodeCompared);
            }

            //compare the current node to goal node and terminate the search if the node match .

            if(goalNode.equals(currentNode)){
                System.out.println("Success in finding goal node at depth: " + currentNode.depth);
                System.out.println("Total Node compared: " + nodeCompared);
                System.out.println("Path to goalNode: " + path);
                return;
            }

            // Generate and get the successors to frontier queue if the goal node is not matched in previous step.

            Node.getSuccesorNodes(currentNode);

            //add all possible children to the frontier. Say if the blank position is at (0,0) then only action possible is Right and Down so only 2 children
            //will be added.

            addToFrontier(frontier, exploredNodes, currentNode.upNode);
            addToFrontier(frontier, exploredNodes, currentNode.leftNode);
            addToFrontier(frontier, exploredNodes, currentNode.downNode);
            addToFrontier(frontier, exploredNodes, currentNode.rightNode);
        }

        //If no solution is found then print and return

        System.out.println("could not find solution");
        return;
    }

    /**
     * Check the successor children if they are not present in explored or frontier then add it to frontier
     * @param frontier
     * @param exploredNodes
     * @param node
     */
    private static void addToFrontier(Queue<Node> frontier, Set<Node> exploredNodes, Node node) {
        if (node != null) {
            if (!exploredNodes.contains(node) && !frontier.contains(node)) {
                frontier.add(node);
            }
        }
    }


    /**
     * Depth First Algorithm
     * uses Node class to hold the 3x3 state and configurations of Initial and goal configurations.
     * @param initialNode
     * @param goalNode
     */
    public static void dfsSearch(Node initialNode, Node goalNode) {

        //Initialize the path. This will hold list of actions taken to reach goal configurations from the initial configuration.

        String path = "";

        //Initialized countered to get total number of nodes compared in the search algorithm.

        Integer nodeCompared = 0;

        //Initialize the stack data structure as frontier. This will implement the DFS search behavior by always retrieving along the branch before expanding nodes at same level.

        Stack<Node> frontier = new Stack<>();

        // Add the root node to frontier.

        frontier.push(initialNode);

        //Initialize explored nodes to Set data structure. This will ensure that once a node is compared it will not be added back to frontier again.
        // Also if a node repeats it will not be added again to explored set.

        Set<Node> exploredNodes = new HashSet<>();

        //Loop and compare while frontier is not empty or the goal state is reached

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.pop();
            path = path.concat(currentNode.nodeType.getNodeType() + "  ");
            exploredNodes.add(currentNode);

            nodeCompared++;
            if (nodeCompared % 100 == 0) {
                System.out.println("Total comparisons so far: " + nodeCompared);
            }
            if (goalNode.equals(currentNode)) {
                System.out.println("Success in finding goal node at depth: " + currentNode.depth);
                System.out.println("Total Node compared: " + nodeCompared);
                System.out.println("Path to goalNode: " + path);
                return;
            }

            Node.getSuccesorNodes(currentNode);
            addToFrontier(frontier, exploredNodes, currentNode.rightNode);
            addToFrontier(frontier, exploredNodes, currentNode.downNode);
            addToFrontier(frontier, exploredNodes, currentNode.leftNode);
            addToFrontier(frontier, exploredNodes, currentNode.upNode);
        }

        System.out.println("could not find solution");
        return;
    }


    /**
     * Helper method to print the state of Node
     * @param node
     */
    public static void printNode(Node node){

        if(node==null){
            System.out.println("Node is null");
            return;
        }
        System.out.println("");
        for(int i=0,k=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(node.state[i][j] + "  ");
            }
            System.out.println("  ");
        }
    }



    private static void addToFrontier(Stack<Node> frontier, Set<Node> exploredNodes, Node node) {
        if (node != null) {
            if (!exploredNodes.contains(node) && !frontier.contains(node)) {
                frontier.add(node);
            }
        }
    }

    public static HashSet<Node> ucsSearch(Node initialNode, Node goalNode){

        String path = "";
        HashSet<Node> allNodes = new HashSet<>();

        Integer nodeCompared = 0;
        Queue<Node> frontier = new PriorityQueue<>((node1, node2) -> Integer.compare(node2.getCost(goalNode), node1.getCost(goalNode)));
        allNodes.add(initialNode);
        frontier.add(initialNode);

        Set<Node> exploredNodes = new HashSet<>();

        while(!frontier.isEmpty()){
            Node currentNode = frontier.remove();
            path = path.concat(currentNode.nodeType.getNodeType() + "  ");
            exploredNodes.add(currentNode);

            nodeCompared++;
            if(nodeCompared % 100 == 0){
                System.out.println("Total comparisons so far: " + nodeCompared);
            }
            if(goalNode.equals(currentNode)){
                System.out.println("Success in finding goal node at depth: " + currentNode.depth);
                System.out.println("Total Node compared: " + nodeCompared);
                System.out.println("Path to goalNode: " + path);
                return allNodes;
            }

            Node.getSuccesorNodes(currentNode);
            addToFrontier(frontier, exploredNodes, currentNode.upNode);
            addToFrontier(frontier, exploredNodes, currentNode.leftNode);
            addToFrontier(frontier, exploredNodes, currentNode.downNode);
            addToFrontier(frontier, exploredNodes, currentNode.rightNode);
        }

        System.out.println("could not find solution");
        return allNodes;
    }
}
