package dfs;

import domain.Node;
import domain.NodeType;

import java.util.*;

public class EightPuzzleSolver {

    public static Map<Integer, HashSet<Node>> nodesByDepth;

    public static void main(String [] args){

        Integer [][] initState = {{2,8,3}, {1,6,4}, {7,0,5}};
        Integer [][] goalState = {{1,2,3}, {8,0,4}, {7,6,5}};

        Node initializedNode = new Node(initState, NodeType.ROOT);
        initializedNode.depth=0;

        Node goalNode =  new Node(goalState, NodeType.GOAL);
        printNode(initializedNode);
        printNode(goalNode);

        Integer maxDepth = null;
        try {
            HashSet<Node> nodes = bfsSearch(initializedNode, goalNode);
            Map<Integer, Set<Node>> nodesByDepth = new HashMap<>();
            for (Node node : nodes) {
                nodesByDepth.computeIfAbsent(node.getDepth(), k -> new HashSet<>()).add(node);
            }
            maxDepth = nodesByDepth.keySet().stream().max(Integer::compareTo).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(maxDepth);

    }

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

    public static HashSet<Node> bfsSearch(Node initialNode, Node goalNode){

        String path = "";
        HashSet<Node> allNodes = new HashSet<>();

        Integer nodeCompared = 0;
        Queue<Node> frontier = new LinkedList<>();
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
            addToFrontier(frontier, exploredNodes, currentNode.upNode, allNodes );
            addToFrontier(frontier, exploredNodes, currentNode.leftNode, allNodes);
            addToFrontier(frontier, exploredNodes, currentNode.downNode, allNodes);
            addToFrontier(frontier, exploredNodes, currentNode.rightNode, allNodes);
        }

        System.out.println("could not find solution");
        return allNodes;
    }

    private static void addToFrontier(Queue<Node> frontier, Set<Node> exploredNodes, Node node, HashSet<Node> allNodes) {
        if (node != null) {
            allNodes.add(node);
            if (!exploredNodes.contains(node) && !frontier.contains(node)) {
                frontier.add(node);
            }
        }
    }

    private static void addToFrontier(Stack<Node> frontier, Set<Node> exploredNodes, Node node, HashSet<Node> allNodes) {
        if (node != null) {
            allNodes.add(node);
            if (!exploredNodes.contains(node) && !frontier.contains(node)) {
                frontier.add(node);
            }
        }
    }

    public static Set<Node> dfsSearch(Node initialNode, Node goalNode) {
        HashSet<Node> allNodes = new HashSet<>();
        String path = "";

        Integer nodeCompared = 0;
        Stack<Node> frontier = new Stack<>();
        allNodes.add(initialNode);
        frontier.push(initialNode);

        Set<Node> exploredNodes = new HashSet<>();

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
                return allNodes;
            }

            Node.getSuccesorNodes(currentNode);
            addToFrontier(frontier, exploredNodes, currentNode.rightNode, allNodes);
            addToFrontier(frontier, exploredNodes, currentNode.downNode, allNodes);
            addToFrontier(frontier, exploredNodes, currentNode.leftNode, allNodes);
            addToFrontier(frontier, exploredNodes, currentNode.upNode, allNodes);
        }

        System.out.println("could not find solution");
        return allNodes;
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
            addToFrontier(frontier, exploredNodes, currentNode.upNode, allNodes );
            addToFrontier(frontier, exploredNodes, currentNode.leftNode, allNodes);
            addToFrontier(frontier, exploredNodes, currentNode.downNode, allNodes);
            addToFrontier(frontier, exploredNodes, currentNode.rightNode, allNodes);
        }

        System.out.println("could not find solution");
        return allNodes;
    }
}
