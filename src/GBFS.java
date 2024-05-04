import java.util.*;



class CostComparator implements Comparator<GreedyNode> {
    public int compare(GreedyNode c1, GreedyNode c2) {
        if (c1.getCost() < c2.getCost())
            return -1;
        else if (c1.getCost() > c2.getCost())
            return 1;
        return 0;
    }
}

public class GBFS extends Controller {
    private PriorityQueue<GreedyNode> queue;
    private List<String> expandedNode;
    private boolean found;

    public GBFS() {
        this.queue = new PriorityQueue<GreedyNode>(10, new CostComparator());
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
            GreedyNode startParent = new GreedyNode(null, start, 0);
			long begin = System.currentTimeMillis();
			for (int i = 0; i < start.length() && !this.found; i++) {
				char charAt = start.charAt(i);
				for (char c = 'a'; c <= 'z' && !this.found;c++) {
					if (c != charAt) {
						String word = start.substring(0, i) + c + start.substring(i + 1);
						if (this.listWord.get(word) != null) {
							GreedyNode current = new GreedyNode(startParent, word, getCost(word, target));
							this.queue.add(current);
						}
					}
				}
			}
            for (int i = 0; i < queue.size(); i++) {
                GreedyNode a = queue.poll();
                //System.out.println("ini:" + a.getWord() + " " + a.getCost());
            }
            //int idx = 0;
			for (int i = 0; !this.found; i++) {
                if (queue.peek().getWord().equals(target)) {
                    found = true;
                }
                else {
                    GreedyNode temp = queue.poll();
                    gbfsLoop(temp, target);
					expandedNode.add(temp.getWord());
                    //System.out.println(temp.getWord() + " " + temp.getCost());
                }
			}
			long end = System.currentTimeMillis();
			System.out.println(this.found);
			System.out.println(this.queue.peek().getWord());
            List<String> pathList = new ArrayList<String>(this.queue.peek().getPath());
			pathList.add(0, target);
            Collections.reverse(pathList);
			System.out.println(pathList);
			System.out.printf("Time elapsed: %d ms\n", end-begin);	
		}
    }

    public void gbfsLoop(GreedyNode el, String end) {
        for (int i = 0; i < el.getWord().length() && !this.found; i++) {
			char charAt = el.getWord().charAt(i);
			for (char c = 'a'; c <= 'z' && !this.found;c++) {
				if (c != charAt) {
					String word = el.getWord().substring(0, i) + c + el.getWord().substring(i + 1);
					if (this.listWord.get(word) != null && !expandedNode.contains(word)) {
                        GreedyNode current = new GreedyNode(el, word, getCost(word, end));
                        //System.out.println(el.getWord() + " " + word);
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