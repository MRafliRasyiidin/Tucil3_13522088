import java.util.*;

public class Node {
    protected Node prev;
    protected String word;
    
	public Node() {
		this.prev = null;
		//this.lastWord = "";
	}

    public Node(Node n) {
        this.prev = n.prev;
        this.word = n.word;
    }

	public Node(Node parent, String word) {
        this.prev = parent;
        this.word = word;
	}

	public void setAll(Node parent, String word) {
        this.prev = parent;
        this.word = word;
	}

	//public String getLastWord() {
	//	return this.lastWord;
	//}

    public Node getParent() {
        return this.prev;
    }

	public String getWord() {
		return this.word;
	}

    public List<String> getPath() {
        List<String> path = new ArrayList<String>();
        Node temp = new Node(this);
        while (temp.prev != null) {
            path.add(temp.getWord());
            temp = temp.prev;
        }
        path.add(temp.word);
        return path;
    }

    public int getCostFromParentUCS() {
        int count = 0;
        Node temp = new Node(this);
        while (temp.prev != null) {
            count++;
            temp = temp.prev;
        }
        return count;
    }
}

class GreedyNode extends Node {
    private int cost;

    public GreedyNode() {
        super();
        this.cost = 0;
    }

    public GreedyNode(GreedyNode n) {
        super(n);
        this.cost = n.cost;
    }

	public GreedyNode(GreedyNode parent, String word, int cost) {
        super(parent, word);
        this.cost = cost;
	}

    public int getCost() {
        return this.cost;
    }

    public int countCost(String word, String target) {
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}