package domain;

/**
 * @Author: Vaibhav Kale
 *
 * NodeType class denotes what action on the parent node generated this Node
 */
public enum NodeType {
    UP("U"),
    LEFT("L"),
    DOWN("D"),
    RIGHT("R"),
    ROOT("ROOT"),
    GOAL("G");

    String nodeType;

    NodeType(String nodeType){
        this.nodeType = nodeType;
    }

    public String getNodeType(){
        return this.nodeType;
    }
}
