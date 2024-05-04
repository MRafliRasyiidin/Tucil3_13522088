import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * searchElement
 */

/**
 * Node
 */
class Node {
    private Node prev;
    private String word;
    

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
}

public class UCS {
	private Map<String, Boolean> listWord;
	private List<Node> listPath;
	private boolean found;
	private List<String> expandedNode;
    //private StringBuilder proccessedString;
	
	
    public UCS() {
        this.listWord = new HashMap<String, Boolean>();
		this.listPath = new ArrayList<Node>();
		this.expandedNode = new ArrayList<String>();
        //this.proccessedString = new StringBuilder();
		this.found = false;
    }

    public void readEnglishWord() {
      try {
          File myObj = new File("dictionary.txt");
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            listWord.put(data.toLowerCase(), true);
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    } 

	public void ucs() {
		readEnglishWord();
		Scanner input = new Scanner(System.in);
		System.out.print("Start: ");
		String start = input.nextLine();
		System.out.print("Target: ");
		String target = input.nextLine();
		if (start.length() != target.length()) {
			System.out.println("Gabisa bro kalo panjangnya beda");
		}
		else {
			//StringBuilder start = new StringBuilder(startInput);
			//StringBuilder end = new StringBuilder(endInput);
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
                    ucsLoop(listPath.get(0), target, i);
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

		input.close();
	}


	public void ucsLoop(Node el, String end, int idx) {
		//List<String> temp = new ArrayList<String>(); 
		
		for (int i = 0; i < el.getWord().length() && !this.found; i++) {
			char charAt = el.getWord().charAt(i);
			for (char c = 'a'; c <= 'z' && !this.found;c++) {
				if (c != charAt) {
					String word = el.getWord().substring(0, i) + c + el.getWord().substring(i + 1);
					if (this.listWord.get(word) != null && !expandedNode.contains(word)) {
                        Node current = new Node(el, word);
                        System.out.println(el.getWord() + " " + word);
						//System.out.println(el.getList());
						//System.out.println(idx);
						//System.out.println(word);
						//System.out.println(temp);
						if (word.equals(end)) {
							this.found = true;
						}
						//el2.setAll(temp, word, el.getCost()+1);
						this.listPath.add(current);
						//el2.clearAll();
						//System.out.println("-----------");
						//System.out.println(el2.getList());
						//System.out.println(listPath.get(listPath.size()-1).getList());
						//System.out.println("ini current size");
						//System.out.println(listPath.size()-1);
						//
						//System.out.println("-----------");
						
					}
				}
			}
		}
	}
    public static void main(String[] args) {
      UCS x = new UCS();
      x.ucs();
    }
}
