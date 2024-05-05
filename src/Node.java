import java.util.*;

public class Node {
    protected Node prev;
    protected String word;
    private int cost;
    
	public Node() {
		this.prev = null;
        this.cost = 0;
	}

    public Node(Node n) {
        this.prev = n.prev;
        this.word = n.word;
        this.cost = n.cost;
    }

	public Node(Node parent, String word, int cost) {
        this.prev = parent;
        this.word = word;
        this.cost = cost;
	}

    public void copy(Node el) {
        this.cost = el.cost;
        this.prev = el.prev;
        this.word = el.word;
    }

	public void setAll(Node parent, String word, int cost) {
        this.prev = parent;
        this.word = word;
        this.cost = cost;
	}

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

class CostComparator implements Comparator<Node> {
    public int compare(Node c1, Node c2) {
        if (c1.getCost() < c2.getCost())
            return -1;
        else if (c1.getCost() > c2.getCost())
            return 1;
        return 0;
    }
}