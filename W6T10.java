import java.io.*;
import java.util.*;

class Result {

    public static List<Integer> circularPalindromes(String s) {

        int n = s.length();

        // Duplicate the string so circular substrings become normal substrings
        String doubled = s + s;

        // Transform:
        // "abc" -> #a#b#c#
        int m = doubled.length() * 2 + 1;
        int[] transformed = new int[m];

        for (int i = 0; i < doubled.length(); i++) {
            transformed[2 * i] = 26; // separator
            transformed[2 * i + 1] = doubled.charAt(i) - 'a';
        }
        transformed[m - 1] = 26;

        // Manacher's algorithm
        int[] radius = new int[m];

        int center = 0;
        int right = 0;

        for (int i = 0; i < m; i++) {

            if (i < right) {
                int mirror = 2 * center - i;
                radius[i] = Math.min(radius[mirror], right - i);
            }

            while (i - radius[i] - 1 >= 0 &&
                   i + radius[i] + 1 < m &&
                   transformed[i - radius[i] - 1]
                       == transformed[i + radius[i] + 1]) {

                radius[i]++;
            }

            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }
        }

        // Build Sparse Table for Range Maximum Query
        int log = 1;

        while ((1 << log) <= m) {
            log++;
        }

        int[][] sparse = new int[log][m];

        System.arraycopy(radius, 0, sparse[0], 0, m);

        for (int j = 1; j < log; j++) {

            int len = 1 << j;
            int half = len >> 1;

            for (int i = 0; i + len <= m; i++) {
                sparse[j][i] = Math.max(
                    sparse[j - 1][i],
                    sparse[j - 1][i + half]
                );
            }
        }

        List<Integer> result = new ArrayList<>(n);

        // Find answer for every rotation
        for (int start = 0; start < n; start++) {

            int left = 2 * start + 1;
            int rightEnd = 2 * (start + n - 1) + 1;

            int low = 1;
            int high = n;
            int answer = 1;

            while (low <= high) {

                int length = (low + high) >>> 1;

                int queryLeft = left + length - 1;
                int queryRight = rightEnd - length + 1;

                if (queryLeft <= queryRight &&
                    rangeMax(sparse, queryLeft, queryRight) >= length) {

                    answer = length;
                    low = length + 1;

                } else {
                    high = length - 1;
                }
            }

            result.add(answer);
        }

        return result;
    }

    private static int rangeMax(
            int[][] sparse,
            int left,
            int right) {

        int length = right - left + 1;

        int j = 31 - Integer.numberOfLeadingZeros(length);

        return Math.max(
            sparse[j][left],
            sparse[j][right - (1 << j) + 1]
        );
    }
}

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        String s = br.readLine().trim();

        List<Integer> result =
            Result.circularPalindromes(s);

        StringBuilder out = new StringBuilder();

        for (int value : result) {
            out.append(value).append('\n');
        }

        System.out.print(out);
    }
}