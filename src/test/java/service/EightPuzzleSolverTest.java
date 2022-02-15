package service;

import domain.Node;
import domain.NodeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static service.EightPuzzleSolver.*;

class EightPuzzleSolverTest {

    // Breadth First Test Cases

    @Test
    @DisplayName("Checking the number of comparisons when the goal node is same as initialized node")
    public void testBFSAtRootNode(){
        Integer [][] initState = {{2,8,3}, {1,6,4}, {7,0,5}};
        Integer [][] goalState = {{2,8,3}, {1,6,4}, {7,0,5}};

        Node initializedNode = new Node(initState, NodeType.ROOT);
        initializedNode.depth=0;

        Node goalNode =  new Node(goalState, NodeType.ROOT);
        printNode(initializedNode);
        printNode(goalNode);

        bfsSearch(initializedNode, goalNode);
    }

    @Test
    @DisplayName("Checking the number of comparisons when the goal node is random initialized node")
    public void testBFSAtRandomInitializedNode(){
        Integer [][] initState = {{2,8,3}, {1,6,4}, {7,0,5}};
        Integer [][] goalState = {{1,2,3}, {8,0,4}, {7,6,5}};

        Node initializedNode = new Node(initState, NodeType.ROOT);
        initializedNode.depth=0;

        Node goalNode =  new Node(goalState, NodeType.GOAL);
        printNode(initializedNode);
        printNode(goalNode);

        bfsSearch(initializedNode, goalNode);
    }


    // Depth First Test Cases

    @Test
    @DisplayName("Checking the number of comparisons when the goal node is same as initialized node")
    public void testDFSAtRootNode(){
        Integer [][] initState = {{2,8,3}, {1,6,4}, {7,0,5}};
        Integer [][] goalState = {{2,8,3}, {1,6,4}, {7,0,5}};

        Node initializedNode = new Node(initState, NodeType.ROOT);
        initializedNode.depth=0;

        Node goalNode =  new Node(goalState, NodeType.GOAL);
        printNode(initializedNode);
        printNode(goalNode);

        dfsSearch(initializedNode, goalNode);
    }

    @Test
    @DisplayName("Checking the number of comparisons when the goal node is random initialized node")
    public void testDFSAtRandomInitializedNode(){
        Integer [][] initState = {{1,4,2}, {3,0,5}, {6,7,8}};
        Integer [][] goalState = {{0,1,2}, {3,4,5}, {6,7,8}};

        Node initializedNode = new Node(initState, NodeType.ROOT);
        initializedNode.depth=0;

        Node goalNode =  new Node(goalState, NodeType.GOAL);
        printNode(initializedNode);
        printNode(goalNode);

        dfsSearch(initializedNode, goalNode);
    }

    // Uniform Cost Search Test Cases

    @Test
    @DisplayName("Checking the number of comparisons when the goal node is same as initialized node")
    public void testUCSAtRootNode(){
        Integer [][] initState = {{2,8,3}, {1,6,4}, {7,0,5}};
        Integer [][] goalState = {{2,8,3}, {1,6,4}, {7,0,5}};

        Node initializedNode = new Node(initState, NodeType.ROOT);
        initializedNode.depth=0;

        Node goalNode =  new Node(goalState, NodeType.GOAL);
        printNode(initializedNode);
        printNode(goalNode);

        ucsSearch(initializedNode, goalNode);
    }

    @Test
    @DisplayName("Checking the number of comparisons when the goal node is random initialized node")
    public void testucsRandomInitializedNode(){
        Integer [][] initState = {{0,1,2}, {3,4,5}, {6,7,8}};
        Integer [][] goalState = {{3,1,2}, {6,4,5}, {0,7,8}};

        Node initializedNode = new Node(initState, NodeType.ROOT);
        initializedNode.depth=0;

        Node goalNode =  new Node(goalState, NodeType.GOAL);
        printNode(initializedNode);
        printNode(goalNode);

        ucsSearch(initializedNode, goalNode);
    }

}