import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class practice {

    public static void main(String[] args) {
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> a = new ArrayList<>(){{
            add(1);
            add(2);
        }};
        List<Integer> b = new ArrayList<>() {{
            add(1);
            add(2);
        }};

        set.add(a);
        set.add(b);

        System.out.println(set.size());
    }
}
