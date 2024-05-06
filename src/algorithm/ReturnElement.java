package algorithm;
import java.util.*;

public class ReturnElement {
    private List<String> list;
    private long time;

    public ReturnElement() {
        this.list = new ArrayList<String>();
        this.time = 0;
    }

    public ReturnElement(List<String> list, long time) {
        this.list = new ArrayList<String>(list);
        this.time = time;
    }

    public List<String> getList() {
        return this.list;
    }

    public long getTime() {
        return this.time;
    }
}
