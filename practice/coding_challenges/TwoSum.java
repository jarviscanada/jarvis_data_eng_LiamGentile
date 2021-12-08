/*
Time Complexity: O(n) (only one for loop, map methods are O(1))
Space Complexity: O(n) - to store map elements

Iterate through the nums array, if the complement number is a key 
in the hashmap then return a new array with the key's corresponding value
and i... otherwise add nums[i], i to the hashmap as a key value pair and 
go to the next iteration
*/

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
                                                                                                   
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            } 
            else {
                map.put(nums[i], i);
        } 
        throw IllegalArgumentException("There is no solution for those inputs.");
    }
}
