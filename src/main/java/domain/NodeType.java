package domain;

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
