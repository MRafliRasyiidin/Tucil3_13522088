import java.util.*;

/**
 * searchElement
 */

/**
 * Node
 */
public class UCS extends Controller{
	private List<Node> listPath;
	private boolean found;
	private List<String> expandedNode;
    //private StringBuilder proccessedString;
	
	
    public UCS() {
		super();
		this.listPath = new ArrayList<Node>();
		this.expandedNode = new ArrayList<String>();
		this.found = false;
    }

	public void ucs(String start, String target) {
		if (start.length() != target.length()) {
			System.out.println("Gabisa bro kalo panjangnya beda");
		}
		else {
            Node startParent = new Node(null, start);
			long begin = System.currentTimeMillis();
			for (int i = 0; i < start.length() && !this.found; i++) {
				char charAt = start.charAt(i);
				for (char c = 'a'; c <= 'z' && !this.found;c++) {
					if (c != charAt) {
						String word = start.substring(0, i) + c + start.substring(i + 1);
						if (this.listWord.get(word) != null) {
							Node current = new Node(startParent, word);
							this.listPath.add(current);
						}
						//if (word.equals(target)) {
						//	this.found = true;
						//}
					}
				}
			}
            //int idx = 0;
			for (int i = 0; !this.found; i++) {
				//System.out.print("Processed list: ");
				//System.out.println(listPath.get(i).getList());
                if (listPath.get(0).getWord().equals(target)) {
                    found = true;
                }
                else {
                    ucsLoop(listPath.get(0), target);
					expandedNode.add(listPath.get(0).getWord());
                    listPath.remove(0);
                }
                
				//System.out.println(i);
				
				//System.out.println(listPath.get(5).getList());
			}
			long end = System.currentTimeMillis();
			System.out.println(this.found);
			System.out.println(this.listPath.get(listPath.size()-1).getWord());
            List<String> pathList = new ArrayList<String>(this.listPath.get(0).getPath());
			pathList.add(0, target);
            Collections.reverse(pathList);
			System.out.println(pathList);
			//System.out.println(this.listPath.get(listPath.size()-1).getList());
			//System.out.println(this.listPath.get(listPath.size()-1).getCost());
			System.out.printf("Time elapsed: %d\n", end-begin);	

		}

	}


	public void ucsLoop(Node el, String end) {
		for (int i = 0; i < el.getWord().length() && !this.found; i++) {
			char charAt = el.getWord().charAt(i);
			for (char c = 'a'; c <= 'z' && !this.found;c++) {
				if (c != charAt) {
					String word = el.getWord().substring(0, i) + c + el.getWord().substring(i + 1);
					if (this.listWord.get(word) != null && !expandedNode.contains(word)) {
                        Node current = new Node(el, word);
                        //System.out.println(el.getWord() + " " + word);

						if (word.equals(end)) {
							this.found = true;
						}
						this.listPath.add(current);
					}
				}
			}
		}
	}
    public static void main(String[] args) {
      UCS x = new UCS();
	  String[] input = x.input();
      x.ucs(input[0], input[1]);
    }
}
