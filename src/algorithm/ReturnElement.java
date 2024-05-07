package algorithm;
import java.util.*;

public class ReturnElement {
    private List<String> list;
    private long time;
    private int visited_node;

    public ReturnElement() {
        this.list = new ArrayList<String>();
        this.time = 0;
    }

    public ReturnElement(List<String> list, long time, int count) {
        this.list = new ArrayList<String>(list);
        this.time = time;
        this.visited_node = count;
    }

    public List<String> getList() {
        return this.list;
    }

    public long getTime() {
        return this.time;
    }

    public int getVisitedNode() {
        return this.visited_node;
    }
}
