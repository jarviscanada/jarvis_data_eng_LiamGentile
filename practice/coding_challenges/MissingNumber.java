import java.util.*;

/*
Time Complexity of solution: O(n)
*/

class MissingNumber {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        
        // pulling out min and max values for future iteration
        int min = nums[0];
        int max = nums[nums.length-1];
        
        // empty ArrayList to append range(min, max) to
        List<Integer> fullList = new ArrayList<>();
        
        
        int i = 0;
        int x = 0;
        
        for (i=min; i < max; i++) {
            fullList.add(i);
        }
        
        for (int n : fullList) {
            if (!Arrays.asList(nums).contains(n)) {
                x = n;
            }
        }
        return x;
    }
}
