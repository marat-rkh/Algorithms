import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Permutations generator. All permutations are generated in
 * lexicographical (ascending) order
 */
public final class Permutator {
    /**
     * Generates all permutations for given numbers {@code nums}
     * Time complexity = O(n!)
     * {@code nums} array is considered as multiset so it may contain duplicates
     * @param nums array of numbers
     * @return permutations
     */
    public List<List<Integer>> allPermutationsFor(int[] nums) {
        Arrays.sort(nums);
        return allPermutationsBeginningFrom(nums);
    }

    /**
     * Generates all permutations of the set {1, 2, .., n}.
     * Time complexity = O(n!)
     * @param n size of the set
     * @return permutations
     */
    public List<List<Integer>> allPermutations(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = i + 1;
        }
        return allPermutationsBeginningFrom(nums);
    }

    /**
     * Generates all permutations that are greater of or equal to the given
     * permutation represented by {@code nums} array.
     * {@code nums} array is considered as multiset so it may contain duplicates
     * @param nums permutation to start from
     * @return permutations
     */
    public List<List<Integer>> allPermutationsBeginningFrom(int[] nums) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        do {
            result.add(fromArray(nums));
        }
        while (updateToNextPermutationIfPossible(nums));
        return result;
    }

    /**
     * For a given permutation X generates permutation Y so that
     * X < Y and there is no permutation Z so that X < Z < Y.
     * If X is the greatest permutation for a given set of numbers,
     * method generates the lowest permutation for a given set of numbers.
     * Permutation Y is generated in place.
     * Time complexity = O(n)
     * @param nums permutation X
     */
    public void updateToNextPermutation(int[] nums) {
        boolean updated = updateToNextPermutationIfPossible(nums);
        if (!updated) {
            reversePostfix(nums, 0);
        }
    }

    /**
     * For a given permutation X generates permutation Y so that
     * X < Y and there is no permutation Z so that X < Z < Y.
     * If X is the greatest permutation for a given set of numbers,
     * method does nothing.
     * Permutation Y is generated in place.
     * Time complexity = O(n)
     * @param nums permutation X
     * @return {@code true} if next permutation is generated
     */
    public boolean updateToNextPermutationIfPossible(int[] nums) {
        if (nums.length > 1) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1])
                --i;
            if (i < 0) {
                return false;
            } else {
                int j = nums.length - 1;
                while (nums[i] >= nums[j])
                    --j;
                swap(nums, i, j);
                reversePostfix(nums, i + 1);
                return true;
            }
        } else {
            return false;
        }
    }

    private void reversePostfix(int[] arr, int start) {
        int end = arr.length - 1;
        while (start < end) {
            swap (arr, start, end);
            ++start;
            --end;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int buff = arr[i];
        arr[i] = arr[j];
        arr[j] = buff;
    }

    private List<Integer> fromArray(int[] arr) {
        List<Integer> res = new ArrayList<Integer>(arr.length);
        for (Integer e : arr) {
            res.add(e);
        }
        return res;
    }
}
