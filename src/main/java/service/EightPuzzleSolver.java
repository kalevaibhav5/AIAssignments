package service;

import domain.Node;
import domain.NodeType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Vaibhav Kale
 * This Class solves 8 puzzle algorithm using 3 known searching techniques -
 * 1) Breadth First Search
 * 2) Depth First Search
 * 3) Uniform Cost Search
 **/
public class EightPuzzleSolver {

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
            //get the first node in the queue (First In First Out)

            Node currentNode = frontier.remove();

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
                //get the path to reach goal state. This will be either Root, U for UP , L for Left, R for Right and D for Down actions on the blank space ( represented by 0)
                path = getPath(exploredNodes, currentNode);

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
     * Traverse the parent nodes from goal node until Root Node is reached.
     * @param exploredNodes
     * @param currentNode
     * @return
     */
    private static String getPath(Set<Node> exploredNodes, Node currentNode) {
        //initialize path

        String path = "[ ";

        Stack<String> pathStack = new Stack<>();

        //get the map of all the explored nodes with key as NodeID and value as Node object

        Map<Integer, Node> allNodes = exploredNodes.stream().collect(Collectors.toMap(node -> node.nodeID, node -> node));

        //get the current Node and add its action to reach node
        Node node = allNodes.get(currentNode.nodeID);
        pathStack.push(node.nodeType.getNodeType());

        while (node.nodeType!=NodeType.ROOT){
            node = allNodes.get(node.parentNodeId) ;

            pathStack.push(node.nodeType.getNodeType());
        }
        while(!pathStack.isEmpty()){
            path = path.concat(pathStack.pop()).concat(" ");
        }
        return path.concat("]");
    }

    /**
     * Check the successor child if it is not present in explored or frontier then add it to frontier
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

            //get the first node in the stack (Last In First Out)

            Node currentNode = frontier.pop();


            //Add the Node to explored node so it will not be added to Frontier next time.

            exploredNodes.add(currentNode);

            //Increment the node comparision counter.

            nodeCompared++;

            //Print the nodes compared after every 100 comparisions.

            if (nodeCompared % 100 == 0) {
                System.out.println("Total comparisons so far: " + nodeCompared);
            }

            //compare the current node to goal node and terminate the search if the node match .

            if (goalNode.equals(currentNode)) {
                System.out.println("Success in finding goal node at depth: " + currentNode.depth);
                System.out.println("Total Node compared: " + nodeCompared);

                //get the path to reach goal state. This will be either Root, U for UP , L for Left, R for Right and D for Down actions on the blank space ( represented by 0)
                path = getPath(exploredNodes, currentNode);

                System.out.println("Path to goalNode: " + path);
                return;
            }

            // Generate and get the successors to frontier Stack if the goal node is not matched in previous step.

            Node.getSuccesorNodes(currentNode);

            //add all possible children to the frontier. Say if the blank position is at (0,0) then only action possible is Right and Down so only 2 children
            //will be added.

            addToFrontier(frontier, exploredNodes, currentNode.rightNode);
            addToFrontier(frontier, exploredNodes, currentNode.downNode);
            addToFrontier(frontier, exploredNodes, currentNode.leftNode);
            addToFrontier(frontier, exploredNodes, currentNode.upNode);
        }

        //If no solution is found then print and return

        System.out.println("could not find solution");
        return;
    }

    /**
     * This method checks if the successor child Node is not present in frontier and explored then add the Node to frontier.
     * @param frontier
     * @param exploredNodes
     * @param node
     */
    private static void addToFrontier(Stack<Node> frontier, Set<Node> exploredNodes, Node node) {
        if (node != null) {
            if (!exploredNodes.contains(node) && !frontier.contains(node)) {
                frontier.add(node);
            }
        }
    }

    /**
     * Uniform Cost Search Algorithm
     * uses Node class to hold the 3x3 state and configurations of Initial and goal configurations.
     * @param initialNode
     * @param goalNode
     */
    public static void ucsSearch(Node initialNode, Node goalNode){

        //Initialize the path. This will hold list of actions taken to reach goal configurations from the initial configuration.

        String path = "";

        //Initialized countered to get total number of nodes compared in the search algorithm.

        Integer nodeCompared = 0;

        /*Initialize the Frontier with an empty Priority queue to add successor nodes. This Priority Queue will use cost function
         Cost is calculated based on how many cells are matching between goal node and the node to be compared.
         Priority Queue data structure will enforce UCS algorithm behavior by always retrieving  the node with highest matching cells first.
         */
        Queue<Node> frontier = new PriorityQueue<>((node1, node2) -> Integer.compare(node2.getCost(goalNode), node1.getCost(goalNode)));

        // Add the root node to frontier.

        frontier.add(initialNode);

        //Initialize explored nodes to Set data structure. This will ensure that once a node is compared it will not be added back to frontier again.

        Set<Node> exploredNodes = new HashSet<>();

        //Loop and compare while frontier is not empty or the goal state is reached

        while(!frontier.isEmpty()){

            //get the first node in the Priority Queue

            Node currentNode = frontier.remove();

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

                //get the path to reach goal state. This will be either Root, U for UP , L for Left, R for Right and D for Down actions on the blank space ( represented by 0)
                path = getPath(exploredNodes, currentNode);

                System.out.println("Path to goalNode: " + path);
                return;
            }

            // Generate and get the successors to frontier Priority Queue if the goal node is not matched in previous step.

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
     * Helper method to print the state of Node
     * @param node
     */
    public static void printNode(Node node){

        if(node==null){
            System.out.println("Node is null");
            return;
        }
        System.out.println("Node " + node.nodeType + " :");
        for(int i=0,k=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(node.state[i][j] + "  ");
            }
            System.out.println("  ");
        }
        System.out.println();
    }

}
