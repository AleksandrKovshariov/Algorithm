public class OthersAlg {

    public static int Evklid(int p, int q) {
        if (q == 0)
            return q;
        int r = p % q;
        return Evklid(q, r);
    }

    public static String reverseString(String str) {
        char[] strChar = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char t = strChar[i];
            strChar[i] = strChar[j];
            strChar[j] = t;
        }
        return new String(strChar);
    }
}
