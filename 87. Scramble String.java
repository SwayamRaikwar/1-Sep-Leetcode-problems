import java.util.HashMap;
import java.util.Map;

class Solution {
    // Memoization map to store results of (s1, s2) pairs
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base Cases
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() != s2.length()) {
            return false;
        }

        // Unique key for the memoization map
        String key = s1 + "_" + s2;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int n = s1.length();

        // Pruning: Check if both strings have the same character counts
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                memo.put(key, false);
                return false;
            }
        }

        // Try splitting at every possible index i
        for (int i = 1; i < n; i++) {
            // Case 1: Without swap
            // s1[0..i] matches s2[0..i] AND s1[i..n] matches s2[i..n]
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            // Case 2: With swap
            // s1[0..i] matches s2[n-i..n] AND s1[i..n] matches s2[0..n-i]
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }
}
