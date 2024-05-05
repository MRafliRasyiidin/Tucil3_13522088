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

    public void gbfs(String start, String target) {
        if (start.length() != target.length()) {
			System.out.println("Gabisa bro kalo panjangnya beda");
		}
		else {
            Node startParent = new Node(null, start, 0);
			long begin = System.currentTimeMillis();
			for (int i = 0; i < start.length() && !this.found; i++) {
				char charAt = start.charAt(i);
				for (char c = 'a'; c <= 'z' && !this.found;c++) {
					if (c != charAt) {
						String word = start.substring(0, i) + c + start.substring(i + 1);
						if (this.listWord.get(word) != null) {
							Node current = new Node(startParent, word, getCost(word, target));
							this.queue.add(current);
						}
					}
				}
			}
            //int idx = 0;
            Node targetEl = new Node();
			while (!this.found) {
                Node temp = queue.poll();
                if (temp.getWord().equals(target)) {
                    found = true;
                    targetEl.copy(temp);
                }
                else {
                    gbfsLoop(temp, target);
					expandedNode.add(temp.getWord());
                }
			}
			long end = System.currentTimeMillis();
			System.out.println(this.found);
			System.out.println(targetEl.getWord());
            List<String> pathList = new ArrayList<String>(targetEl.getPath());
            Collections.reverse(pathList);
			System.out.println(pathList);
			System.out.printf("Time elapsed: %d ms\n", end-begin);	
		}
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

    public static void main(String[] args) {
        GBFS x = new GBFS();
	    String[] input = x.input();
        x.gbfs(input[0], input[1]);
    }
    
}