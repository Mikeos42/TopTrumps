import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("Hello");
        list.add(null);
        if(list.contains(null))
            System.out.println("Heyoo");
    }
}
