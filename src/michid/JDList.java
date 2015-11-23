package michid;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

/**
 * Alternative approach at the DList in Java. Not very useful though...
 */
public abstract class JDList<T> {
    public abstract List<T> toList(List<T> acc);

    public static <T> JDList<T> fromList(final List<T> xs) {
        return new JDList<T>() {
            @Override
            public List<T> toList(List<T> acc) {
                acc.addAll(xs);
                return acc;
            }
        };
    }

    public JDList<T> append(final JDList<T> ys) {
        return new JDList<T>() {
            @Override
            public List<T> toList(List<T> acc) {
                JDList.this.toList(acc);
                ys.toList(acc);
                return acc;
            }
        };
    }

    public static void main(String[] args) {
        JDList<Integer> xs = fromList(asList(1, 2, 3));
        JDList<Integer> ys = fromList(asList(4, 5, 6));
        JDList<Integer> zs = fromList(asList(7, 8, 9));

        JDList<Integer> all = xs.append(ys).append(zs);
        List<Integer> list = all.toList(new ArrayList<Integer>());
        System.out.println(list);
    }
}
