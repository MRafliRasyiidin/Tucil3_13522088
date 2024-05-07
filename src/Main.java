import algorithm.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();
        System.out.println("Word Ladder Solver");
        System.out.println("Pilih metode pencarian solusi:");
        System.out.println("1. UCS");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        System.out.println("4. Exit");
        System.out.print(">> ");
        Scanner methodInput = new Scanner(System.in);
        String start = methodInput.nextLine();
        String[] input = c.input();
        if (input[0].length() != input[1].length()) {
            System.out.println("Panjang kata berbeda!");
        }
        else if (!c.isWord(input[0]) || !c.isWord(input[1])) {
            System.out.println("Kata tidak terdapat di dalam kamus!");
        }
        else {
            if (start.equals("1")) {
                UCS x = new UCS();
                x.ucs(input[0], input[1]);
            }
            else if (start.equals("2")) {
                GBFS x = new GBFS();
                x.gbfs(input[0], input[1]);
            }
            else if (start.equals("3")) {
                AStar x = new AStar();
                x.aStar(input[0], input[1]);
            }
            else if (start.equals("4")) {
                System.exit(0);
            }
            else {
                System.out.println("Input tidak valid!");
            }
        }
        methodInput.close();
    }
}
