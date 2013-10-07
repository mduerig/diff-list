package michid;

/**
 * A pseudo difference list with constant time prepend (cons) and append (snoc).
 * Translated to Java from
 * http://blog.tmorris.net/posts/list-with-o1-cons-and-snoc-in-scala/index.html
 */
public abstract class DiffList<T> {

    protected abstract ConsList<T> endo(ConsList<T> xs);

    public static <T> DiffList<T> empty() {
        return new DiffList<T>() {
            @Override
            protected ConsList<T> endo(ConsList<T> xs) {
                return xs;
            }
        };
    }

    public DiffList<T> cons(final T x) {
        return new DiffList<T>() {
            @Override
            protected ConsList<T> endo(ConsList<T> xs) {
                return DiffList.this.endo(xs).cons(x);
            }
        };
    }

    public DiffList<T> snoc(final T x) {
        return new DiffList<T>() {
            @Override
            protected ConsList<T> endo(ConsList<T> xs) {
                return DiffList.this.endo(xs.cons(x));
            }
        };
    }

    public DiffList<T> append(final DiffList<T> ys) {
        return new DiffList<T>() {
            @Override
            protected ConsList<T> endo(ConsList<T> xs) {
                return DiffList.this.endo(ys.endo(xs));
            }
        };
    }

    public ConsList<T> toList() {
        return endo(ConsList.<T>nil());
    }

    @Override
    public String toString() {
        return String.valueOf(toList());
    }

    public static void main(String[] args) {
        DiffList<Integer> empty = empty();
        DiffList<Integer> one = empty.cons(1);
        DiffList<Integer> two_one = one.cons(2);
        DiffList<Integer> three_two_one = two_one.cons(3);
        System.out.println(one);
        System.out.println(two_one);
        System.out.println(three_two_one);

        one = empty.snoc(1);
        DiffList<Integer> one_two = one.snoc(2);
        DiffList<Integer> one_two_three = one_two.snoc(3);
        System.out.println(one);
        System.out.println(one_two);
        System.out.println(one_two_three);

        DiffList<Integer> one_two_three_three_two_one = one_two_three.append(three_two_one);
        System.out.println(one_two_three_three_two_one);
    }

}
