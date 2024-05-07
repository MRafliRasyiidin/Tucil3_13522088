package algorithm;
import java.util.*;

public class AStar extends GBFS {
    private PriorityQueue<Node> queue;
    private Map<String, Boolean> expandedNode;
    private Map<Node, Integer> costList;
    private boolean found;

    public AStar() {
        this.queue = new PriorityQueue<Node>(10, new CostComparator());
        this.costList = new HashMap<Node, Integer>();
        this.expandedNode = new HashMap<String, Boolean>();
        this.found = false;
    }

    public int getCost(String word, String target) {
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public ReturnElement aStar(String start, String target) {
        Node startParent = new Node(null, start, 0);
        this.queue.add(startParent);
		this.costList.put(startParent, getCost(start, target));
		long begin = System.currentTimeMillis();
        Node targetNode = new Node();
        int nodeCount = 0;
        while (!this.found) {
            Node temp = queue.poll();
            if (temp == null) {
                return null;
            }
            if (temp.getWord().equals(target)) {
                found = true;
                targetNode.copy(temp);
            }
            else {
                aStarLoop(temp, target);
				expandedNode.put(temp.getWord(), true);
                nodeCount += 1;
            }
		}
		long end = System.currentTimeMillis();
        List<String> pathList = new ArrayList<String>(targetNode.getPath());
        Collections.reverse(pathList);
        System.out.println("\nResult:");
		for (int i = 0; i < pathList.size(); i++) {
            System.out.println((i+1) + ". " + pathList.get(i));
        }
		System.out.printf("Elapsed time: %d ms\n", end-begin);
		System.out.println("Memory used: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1024 + " KB");
		return new ReturnElement(pathList, end-begin, nodeCount);
    }

    public void aStarLoop(Node el, String end) {
        for (int i = 0; i < el.getWord().length() && !this.found; i++) {
			char charAt = el.getWord().charAt(i);
			for (char c = 'a'; c <= 'z' && !this.found;c++) {
				if (c != charAt) {
					String word = el.getWord().substring(0, i) + c + el.getWord().substring(i + 1);
					if (this.listWord.get(word) != null && expandedNode.get(word) == null) {
                        int g_n_cost = this.costList.get(el);
                        Node current = new Node(el, word, g_n_cost + getCost(word, end));
                        this.costList.put(current, g_n_cost + 1);
						this.queue.add(current);
					}
				}
			}
		}
    }
}