import java.util.*;

public class Main {

    static ArrayList<Integer> search(String pat, String txt) {

        int m = pat.length();
        int n = txt.length();

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i <= n - m; i++) {

            int j;

            for (j = 0; j < m; j++) {

                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }

            if (j == m) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        String txt = "aabaacaadaabaaba";
        String pat = "aaba";

        ArrayList<Integer> result = search(pat, txt);

        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}