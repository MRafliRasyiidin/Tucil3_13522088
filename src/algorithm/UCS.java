package algorithm;
import java.util.*;

public class UCS extends Controller{
	private PriorityQueue<Node> listPath;
	private boolean found;
	private Map<String, Boolean> expandedNode;	
	private Map<Node, Integer> costList;
	
    public UCS() {
		super();
		this.listPath = new PriorityQueue<Node>(10, new CostComparator());
		this.costList = new HashMap<Node, Integer>();
		this.expandedNode = new HashMap<String, Boolean>();
		this.found = false;
    }

	public ReturnElement ucs(String start, String target) {
        Node startParent = new Node(null, start, 0);
		this.listPath.add(startParent);
		this.costList.put(startParent, 0);
		long begin = System.currentTimeMillis();
		Node targetNode = new Node();
        int nodeCount = 0;
		while (!this.found) {
			Node temp = listPath.poll();
			if (temp == null) {
                return null;
            }
            if (temp.getWord().equals(target)) {
                found = true;
				targetNode.copy(temp);
            }
            else {
                ucsLoop(temp, target);
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

	public void ucsLoop(Node el, String end) {
		for (int i = 0; i < el.getWord().length() && !this.found; i++) {
			char charAt = el.getWord().charAt(i);
			for (char c = 'a'; c <= 'z' && !this.found;c++) {
				if (c != charAt) {
					String word = el.getWord().substring(0, i) + c + el.getWord().substring(i + 1);
					if (this.listWord.get(word) != null && expandedNode.get(word) == null) {
                        Node current = new Node(el, word, this.costList.get(el));
						this.costList.put(current, current.getCost()+1);
						this.listPath.add(current);
					}
				}
			}
		}
	}
}
