package domain;

import java.util.Arrays;

public class Node {
    public Integer nodeID;
    public Integer [] [] state = new Integer[3][3];
    public Integer xBlankPosition;
    public Integer yBlankPosition;
    public Integer depth;
    public Node upNode;
    public Node leftNode;
    public Node downNode;
    public Node rightNode;
    public NodeType nodeType;
    public Integer parentNodeID;

    public Integer getDepth(){
        return this.depth;
    }

    public Node(Integer[][] state, NodeType nodeType) {
        this.state = state;
        this.nodeType = nodeType;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(state[i][j]==0){
                    xBlankPosition=i;
                    yBlankPosition=j;
                }
            }
        }
    }

    public static void getSuccesorNodes(Node node){

       getLeftNode(node);
       getRightNode(node);
       getDownNode(node);
       getUpNode(node);

    }

    public static void getLeftNode(Node node){
        if(node.yBlankPosition == 0){
            node.leftNode = null;
            return;
        }

        Integer [][] newState = new Integer[3][3];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                newState[i][j]=node.state[i][j];
            }
        }

        newState[node.xBlankPosition][node.yBlankPosition] = newState[node.xBlankPosition][node.yBlankPosition - 1];
        newState[node.xBlankPosition][node.yBlankPosition -1 ] = 0;

        node.leftNode = new Node(newState,NodeType.LEFT);
        node.leftNode.depth = node.depth + 1;
    }

    public static void getRightNode(Node node){
        if(node.yBlankPosition == 2){
            node.rightNode = null;
            return;
        }

        Integer [][] newState = new Integer[3][3];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                newState[i][j]=node.state[i][j];
            }
        }

        newState[node.xBlankPosition][node.yBlankPosition] = newState[node.xBlankPosition][node.yBlankPosition + 1];
        newState[node.xBlankPosition][node.yBlankPosition + 1 ] = 0;

        node.rightNode = new Node(newState, NodeType.RIGHT);
        node.rightNode.depth = node.depth + 1;

    }

    public static void getUpNode(Node node){
        if(node.xBlankPosition == 0){
            node.upNode = null;
            return;
        }

        Integer [][] newState = new Integer[3][3];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                newState[i][j]=node.state[i][j];
            }
        }

        newState[node.xBlankPosition][node.yBlankPosition] = newState[node.xBlankPosition -1][node.yBlankPosition];
        newState[node.xBlankPosition -1][node.yBlankPosition] = 0;

        node.upNode = new Node(newState, NodeType.UP);
        node.upNode.depth = node.depth + 1;
    }

    public static void getDownNode(Node node){
        if(node.xBlankPosition == 2){
            node.upNode = null;
            return;
        }

        Integer [][] newState = new Integer[3][3];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                newState[i][j]=node.state[i][j];
            }
        }

        newState[node.xBlankPosition][node.yBlankPosition] = newState[node.xBlankPosition + 1][node.yBlankPosition];
        newState[node.xBlankPosition + 1][node.yBlankPosition] = 0;

        node.downNode = new Node(newState, NodeType.DOWN);
        node.downNode.depth = node.depth + 1;
    }

    public Integer getCost(Node goalnode){
        Integer cost=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.state[i][j]==goalnode.state[i][j]){
                    cost++;
                }
            }
        }
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        boolean equalNode = true;
        for(int i=0,k=0;i<3;i++){
            for(int j=0;j<3;j++){
                equalNode =  state[i][j] == node.state[i][j];
                if(!equalNode){
                    return equalNode;
                }
            }
        }
        return equalNode;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
