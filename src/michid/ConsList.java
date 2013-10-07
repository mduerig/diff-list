package michid;

public abstract class ConsList<T> {
    public abstract T head();
    public abstract ConsList<T> tail();

    public static <T> ConsList<T> nil() {
        return new ConsList<T>() {
            @Override
            public String toString() {
                return "nil";
            }

            @Override
            public T head() {
                throw new IllegalStateException("head of nil");
            }

            @Override
            public ConsList<T> tail() {
                return this;
            }
        };
    }

    public ConsList<T> cons(final T x) {
        return new ConsList<T>() {
            @Override
            public String toString() {
                return head() + ":" + tail();
            }

            @Override
            public T head() {
                return x;
            }

            @Override
            public ConsList<T> tail() {
                return ConsList.this;
            }
        };
    }

}
