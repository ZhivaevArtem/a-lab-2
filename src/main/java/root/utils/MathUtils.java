package root.utils;

public class MathUtils {
    public static int positiveMod(int n, int d) {
        return ((n % d) + d) % d;
    }
}
