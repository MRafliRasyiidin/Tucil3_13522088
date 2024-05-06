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
		if (start.length() != target.length()) {
			System.out.println("Gabisa bro kalo panjangnya beda");
		}
		else {
            Node startParent = new Node(null, start, 0);
			this.listPath.add(startParent);
			this.costList.put(startParent, 0);
			long begin = System.currentTimeMillis();
			Node targetNode = new Node();
			while (!this.found) {
				Node temp = listPath.poll();
                if (temp.getWord().equals(target)) {
                    found = true;
					targetNode.copy(temp);
                }
                else {
                    ucsLoop(temp, target);
					expandedNode.put(temp.getWord(), true);
                }
			}
			long end = System.currentTimeMillis();
			System.out.println(this.found);
			System.out.println(targetNode.getWord());
            List<String> pathList = new ArrayList<String>(targetNode.getPath());
            Collections.reverse(pathList);
			System.out.println(pathList);
			System.out.printf("Time elapsed: %d\n", end-begin);	
			return new ReturnElement(pathList, end-begin);
		}
		return null;
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
