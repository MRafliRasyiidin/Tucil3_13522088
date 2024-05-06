import src.Algorithm.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Word Ladder Solver");
        System.out.println("Pilih metode pencarian solusi:");
        System.out.println("1. UCS");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        System.out.print(">> ");
        Scanner methodInput = new Scanner(System.in);
		String start = methodInput.nextLine();
        if (start.equals("1")) {
            UCS x = new UCS();
	        String[] input = x.input();
            x.ucs(input[0], input[1]);
        }
        else if (start.equals("2")) {
            GBFS x = new GBFS();
	        String[] input = x.input();
            x.gbfs(input[0], input[1]);
        }
        else if (start.equals("3")) {
            AStar x = new AStar();
	        String[] input = x.input();
            x.aStar(input[0], input[1]);
        }
        else {
            System.out.println("Input tidak valid!");
        }
        methodInput.close();
    }
}
