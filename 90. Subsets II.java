import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the array to ensure duplicates are adjacent
        Arrays.sort(nums);
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Add current subset to result
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // Skip duplicate elements at the same recursion depth
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Include current element
            current.add(nums[i]);

            // Recurse with next index
            backtrack(i + 1, nums, current, result);

            // Backtrack (remove last added element)
            current.remove(current.size() - 1);
        }
    }
}
