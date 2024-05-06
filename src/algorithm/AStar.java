package algorithm;
import java.util.*;

public class AStar extends GBFS {
    private PriorityQueue<Node> queue;
    private Map<String, Boolean> expandedNode;
    private Map<Node, Integer> costList;
    //private List<String> expandedNode;
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
        if (start.length() != target.length()) {
			System.out.println("Gabisa bro kalo panjangnya beda");
		}
		else {
            Node startParent = new Node(null, start, 0);
            this.queue.add(startParent);
			this.costList.put(startParent, getCost(start, target));
			long begin = System.currentTimeMillis();
			//for (int i = 0; i < start.length() && !this.found; i++) {
			//	char charAt = start.charAt(i);
			//	for (char c = 'a'; c <= 'z' && !this.found;c++) {
			//		if (c != charAt) {
			//			String word = start.substring(0, i) + c + start.substring(i + 1);
			//			if (this.listWord.get(word) != null) {
			//				Node current = new Node(startParent, word, getCost(word, target) + 1);
            //                this.costList.put(current, 1);
			//				this.queue.add(current);
			//			}
			//		}
			//	}
			//}
            Node targetNode = new Node();
			while (!this.found) {
                Node temp = queue.poll();
                if (temp.getWord().equals(target)) {
                    found = true;
                    targetNode.copy(temp);
                }
                else {
                    aStarLoop(temp, target);
					expandedNode.put(temp.getWord(), true);
                }
			}
			long end = System.currentTimeMillis();
			System.out.println(this.found);
			System.out.println(targetNode.getWord());
            List<String> pathList = new ArrayList<String>(targetNode.getPath());
            Collections.reverse(pathList);
			System.out.println(pathList);
			System.out.printf("Time elapsed: %d ms\n", end-begin);	
			return new ReturnElement(pathList, end-begin);
		}
        return null;
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