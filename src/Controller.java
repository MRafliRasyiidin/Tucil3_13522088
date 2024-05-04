import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class Controller {
	protected Map<String, Boolean> listWord;
    
    public Controller() {
        this.listWord = new HashMap<String, Boolean>();
        readEnglishWord();
    }

    public void readEnglishWord() {
        try {
            File myObj = new File("words.txt");
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

    public String[] input() {
        Scanner input = new Scanner(System.in);
		System.out.print("Start: ");
		String start = input.nextLine();
		System.out.print("Target: ");
		String target = input.nextLine();
		input.close();
        String[] res = {start, target};
        return res;
    }
}
