package algorithm;
import java.util.*;

public class GBFS extends Controller {
    private PriorityQueue<Node> queue;
    private List<String> expandedNode;
    private boolean found;

    public GBFS() {
        this.queue = new PriorityQueue<Node>(10, new CostComparator());
        this.expandedNode = new ArrayList<String>();
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

    public ReturnElement gbfs(String start, String target) {
        if (start.length() != target.length()) {
			System.out.println("Gabisa bro kalo panjangnya beda");
		}
		else {
            Node startParent = new Node(null, start, getCost(start, target));
            this.queue.add(startParent);
			long begin = System.currentTimeMillis();
            Node targetEl = new Node();
            int nodeCount = 0;
			while (!this.found) {
                Node temp = queue.poll();
                if (temp.getWord().equals(target)) {
                    found = true;
                    targetEl.copy(temp);
                }
                else {
                    gbfsLoop(temp, target);
					expandedNode.add(temp.getWord());
                    nodeCount += 1;
                }
			}
			long end = System.currentTimeMillis();
			System.out.println(this.found);
			System.out.println(targetEl.getWord());
            List<String> pathList = new ArrayList<String>(targetEl.getPath());
            Collections.reverse(pathList);
			System.out.println(pathList);
			System.out.printf("Time elapsed: %d ms\n", end-begin);	
			System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1024);
            System.out.println(pathList.size()-1);
			return new ReturnElement(pathList, end-begin, nodeCount);
		}
        return null;
    }

    public void gbfsLoop(Node el, String end) {
        for (int i = 0; i < el.getWord().length() && !this.found; i++) {
			char charAt = el.getWord().charAt(i);
			for (char c = 'a'; c <= 'z' && !this.found;c++) {
				if (c != charAt) {
					String word = el.getWord().substring(0, i) + c + el.getWord().substring(i + 1);
					if (this.listWord.get(word) != null && !expandedNode.contains(word)) {
                        Node current = new Node(el, word, getCost(word, end));
						this.queue.add(current);
					}
				}
			}
		}
    }
}